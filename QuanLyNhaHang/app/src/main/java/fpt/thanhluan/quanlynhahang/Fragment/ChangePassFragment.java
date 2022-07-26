package fpt.thanhluan.quanlynhahang.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
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

public class ChangePassFragment extends Fragment {

    TextInputEditText edPassOld,edPass,edRePass;
    Button btnSave,btnCancel;
    NhanVienDAO nhanVienDAO;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_change_pass,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        edPassOld = view.findViewById(R.id.edPassOld);
        edPass = view.findViewById(R.id.edPassChange);
        edRePass = view.findViewById(R.id.edRePassChange);
        btnSave = view.findViewById(R.id.btnSaveUserChange);
        btnCancel = view.findViewById(R.id.btnCancelUserChange);

        nhanVienDAO = new NhanVienDAO(getActivity());
        nhanVienDAO.open();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //doc user,pass trong SharedPreferences
                SharedPreferences pref = getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
                String user = pref.getString("USERNAME","");
                if(validate()>0){
                    NhanVien nhanVien = nhanVienDAO.getID(user);
                    nhanVien.setMatKhau(edPass.getText().toString());
                    if(nhanVienDAO.updateRow(nhanVien)>0){
                        Toast.makeText(getActivity(), "Thay đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                        edPassOld.setText("");
                        edPass.setText("");
                        edRePass.setText("");
                    }else {
                        Toast.makeText(getActivity(), "Thay đổi mật khẩu thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edPassOld.setText("");
                edPass.setText("");
                edRePass.setText("");
            }
        });

    }

    public int validate(){
        int check = 1;
        if(edPassOld.getText().length()==0||edPass.getText().length()==0||edRePass.getText().length()==0){
            Toast.makeText(getActivity(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }else if(edPass.getText().length()<6)
        {
            Toast.makeText(getActivity(), "Mật khẩu không được nhỏ hơn 6 ký tự", Toast.LENGTH_SHORT).show();
            check = -1;
        }
          else {
            //doc user,pass trong SharedPreferences
            SharedPreferences pref = getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
            String passOld = pref.getString("PASSWORD","");
            String pass = edPass.getText().toString();
            String rePass = edRePass.getText().toString();
            if(!passOld.equals(edPassOld.getText().toString())){
                Toast.makeText(getActivity(), "Mật khẩu cũ sai", Toast.LENGTH_SHORT).show();
                check = -1;
            }
            if(!pass.equals(rePass)){
                Toast.makeText(getActivity(), "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                check = -1;
            }

        }
        return check;
    }
}
