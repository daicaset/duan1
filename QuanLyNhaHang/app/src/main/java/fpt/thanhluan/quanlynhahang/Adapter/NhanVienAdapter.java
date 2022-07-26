package fpt.thanhluan.quanlynhahang.Adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import fpt.thanhluan.quanlynhahang.AdapterSpinner.LoaiDoUongAdapterSpinner;
import fpt.thanhluan.quanlynhahang.DAO.LoaiDoUongDAO;
import fpt.thanhluan.quanlynhahang.DAO.NhanVienDAO;
import fpt.thanhluan.quanlynhahang.DAO.PhongDAO;
import fpt.thanhluan.quanlynhahang.DTO.DoUong;
import fpt.thanhluan.quanlynhahang.DTO.LoaiDoUong;
import fpt.thanhluan.quanlynhahang.DTO.NhanVien;
import fpt.thanhluan.quanlynhahang.DTO.Phong;
import fpt.thanhluan.quanlynhahang.R;

public class NhanVienAdapter extends BaseAdapter {

    Context context;
    ArrayList<NhanVien> listNhanViens;
    NhanVienDAO nhanVienDAO;


    EditText edtTimKiem;
    Button btnTimKiem;
    TextView tvMaNhanVien;
    TextView tvHoTen;
    TextView tvMatKhau;

    public NhanVienAdapter(Context context, ArrayList<NhanVien> listNhanViens, NhanVienDAO nhanVienDAO) {
        this.context = context;
        this.listNhanViens = listNhanViens;
        this.nhanVienDAO = nhanVienDAO;
    }

    @Override
    public int getCount() {
        return listNhanViens.size();
    }

    @Override
    public Object getItem(int position) {
        return listNhanViens.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View itemview;
        //khởi tạo cho item
        if (view == null) {
            itemview = View.inflate(viewGroup.getContext(), R.layout.item_lv_nhanvien, null);

        } else{
            itemview = view;
        }
        //lấy thôgn tin bản ghi dữ liệu
        final NhanVien objNhanVien = listNhanViens.get(position);

     //ánh xạ các biến
        TextView tvMaNV = itemview.findViewById(R.id.tvMaNV);
        TextView tvHoTen = itemview.findViewById(R.id.tvHoTen);
        TextView tvMatKhau = itemview.findViewById(R.id.tvMatKhau);
        ImageView imgXoa = itemview.findViewById(R.id.imgXoa);

        //set text
        tvMaNV.setText("Mã nhân viên: "+objNhanVien.getMaNV());
        tvHoTen.setText("Họ tên: "+objNhanVien.getHoTen());
        tvMatKhau.setText("Mật khẩu: "+objNhanVien.getMatKhau());

        imgXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Delete");
                builder.setIcon(android.R.drawable.ic_delete);
                builder.setMessage("Bạn có muốn xóa không ?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int kq = nhanVienDAO.deleteRow(objNhanVien);
                        if(kq>0){
                            listNhanViens.remove(position);
                            notifyDataSetChanged();
                            Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                        }
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                Dialog dialog = builder.create();
                dialog.show();
            }
        });


        return itemview;
    }

    public void showDialogSearch(){

        Dialog dialog = new Dialog(context,R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setContentView(R.layout.dialog_timkiem);

        edtTimKiem = dialog.findViewById(R.id.edtTimKiem);
        btnTimKiem = dialog.findViewById(R.id.btnTimKiem);
        tvMaNhanVien = dialog.findViewById(R.id.tvMaNhanVien);
        tvHoTen = dialog.findViewById(R.id.tvHoTen);
        tvMatKhau = dialog.findViewById(R.id.tvMatKhau);
        btnTimKiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = edtTimKiem.getText().toString();


                if (user.equals("")){
                }
                else {
                    List<NhanVien> list = nhanVienDAO.dataByID(user);
                    if (list.size()!=0){
                        tvHoTen.setText(list.get(0).getHoTen()+"");
                        tvMaNhanVien.setText(list.get(0).getMaNV()+"");
                        tvMatKhau.setText(list.get(0).getMatKhau()+"");
                    }else {
                        tvHoTen.setText("");
                        tvMaNhanVien.setText("Tài khoản không tồn tại");
                        tvMatKhau.setText("");
                    }
                }

            }
        });

        dialog.show();

    }
}
