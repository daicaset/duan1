package fpt.thanhluan.quanlynhahang.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;

import fpt.thanhluan.quanlynhahang.DAO.NhanVienDAO;
import fpt.thanhluan.quanlynhahang.DTO.NhanVien;
import fpt.thanhluan.quanlynhahang.R;

public class AddUserFragment extends Fragment {

    TextInputEditText edUser,edHoTen,edPass,edRePass;
    Button btnSave,btnCancel;
    NhanVienDAO nhanVienDAO;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_user,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        edUser = view.findViewById(R.id.edUser);
        edHoTen = view.findViewById(R.id.edHoTen);
        edPass = view.findViewById(R.id.edPass);
        edRePass = view.findViewById(R.id.edRePass);
        btnSave = view.findViewById(R.id.btnSaveUser);
        btnCancel = view.findViewById(R.id.btnCancelUser);

        nhanVienDAO = new NhanVienDAO(getActivity());
        nhanVienDAO.open();

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edUser.setText("");
                edHoTen.setText("");
                edPass.setText("");
                edRePass.setText("");
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //doc user,pass trong SharedPreferences

                NhanVien nhanVien = new NhanVien();

                if(edUser.getText().toString().length()<6||edUser.getText().toString().length()>15)
                {
                    edUser.setError("Tên người dùng tối thiểu là 6 tối đa là 15");
                }else{
                    nhanVien.setMaNV(edUser.getText().toString());
                    nhanVien.setHoTen(edHoTen.getText().toString());
                    nhanVien.setMatKhau(edPass.getText().toString());
                }


                if(validate()>0){
                    if(nhanVienDAO.insertRow(nhanVien)>0){
                        Toast.makeText(getActivity(), "Lưu thành công", Toast.LENGTH_SHORT).show();
                        edUser.setText("");
                        edHoTen.setText("");
                        edPass.setText("");
                        edRePass.setText("");
                    }else {
                        Toast.makeText(getActivity(), "Lưu thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public int validate(){
        int check = 1;
        if(edUser.getText().length()==0||edHoTen.getText().length()==0||edPass.getText().length()==0||edRePass.getText().length()==0){
            Toast.makeText(getContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }else if(edPass.getText().length()<6){
            Toast.makeText(getContext(),"Mật khẩu không được nhỏ hơn 6 ký tự",Toast.LENGTH_SHORT).show();
            check = -1;
        }
        else {
            String pass = edPass.getText().toString();
            String rePass = edRePass.getText().toString();

            if(!pass.equals(rePass)){
                Toast.makeText(getContext(), "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                check = -1;
            }

        }
        return check;
    }
}
