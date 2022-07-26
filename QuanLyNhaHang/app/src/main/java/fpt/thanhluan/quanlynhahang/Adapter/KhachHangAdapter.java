package fpt.thanhluan.quanlynhahang.Adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import fpt.thanhluan.quanlynhahang.DAO.KhachHangDAO;
import fpt.thanhluan.quanlynhahang.DAO.NhanVienDAO;
import fpt.thanhluan.quanlynhahang.DTO.KhachHang;
import fpt.thanhluan.quanlynhahang.DTO.NhanVien;
import fpt.thanhluan.quanlynhahang.DTO.Phong;
import fpt.thanhluan.quanlynhahang.R;

public class KhachHangAdapter extends BaseAdapter {

    Context context;
    ArrayList<KhachHang> listKhachHangs;
    KhachHangDAO khachHangDAO;

    public KhachHangAdapter(Context context, ArrayList<KhachHang> listKhachHangs, KhachHangDAO khachHangDAO) {
        this.context = context;
        this.listKhachHangs = listKhachHangs;
        this.khachHangDAO = khachHangDAO;
    }

    @Override
    public int getCount() {
        return listKhachHangs.size();
    }

    @Override
    public Object getItem(int position) {
        return listKhachHangs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listKhachHangs.get(position).getMaKH();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View itemview;
        //khởi tạo cho item
        if (view == null) {
            itemview = View.inflate(viewGroup.getContext(), R.layout.item_lv_khachhang, null);

        } else itemview = view;
        //lấy thôgn tin bản ghi dữ liệu
        final KhachHang objKhachHang = listKhachHangs.get(position);
        final int _index = position;


//ánh xạ các bviuến
        TextView tvMaKH = itemview.findViewById(R.id.tvMaKH);
        TextView tvTenKH = itemview.findViewById(R.id.tvTenKH);
        TextView tvSDT = itemview.findViewById(R.id.tvSDT);

        ImageView imgXoa = itemview.findViewById(R.id.imgXoa);
        ImageView imgSua = itemview.findViewById(R.id.imgSua);

        //set text
        tvMaKH.setText(objKhachHang.getMaKH()+"");
        tvTenKH.setText(objKhachHang.getTenKH()+"");
        tvSDT.setText(objKhachHang.getSDT()+"");


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
                        int kq = khachHangDAO.deleteRow(objKhachHang);
                        if(kq>0){
                            listKhachHangs.remove(position);
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

        imgSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               showDialogUpdate(position,objKhachHang);
            }
        });

        return itemview;
    }

    public void showDiaLogAdd(){

        Dialog dialog = new Dialog(context,R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setContentView(R.layout.dialog_khach_hang);

        TextInputEditText edTenKH = dialog.findViewById(R.id.edTenKH);
        TextInputEditText edSDT = dialog.findViewById(R.id.edSDT);
        Button btnLuu = dialog.findViewById(R.id.btnSaveKH);
        Button btnHuy = dialog.findViewById(R.id.btnCancelKH);

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                KhachHang objKhachHang = new KhachHang();

                if (edTenKH.getText().toString().isEmpty()){
                    edTenKH.setError("Không để trống tên khách hàng");
                }else if(edTenKH.getText().length()>15){
                    edTenKH.setError("Tên không được lớn hơn 15 ký tự");
                }
                else if (edSDT.getText().toString().isEmpty()){
                    edSDT.setError("Không để trống số điện thoại");
                }else if(edSDT.getText().length()!=10){
                    edSDT.setError("Số điện thoại phải là 10 số");
                }
                else
                {
                    objKhachHang.setTenKH(edTenKH.getText().toString());
                    objKhachHang.setSDT(edSDT.getText().toString());
                    long kq = khachHangDAO.insertRow(objKhachHang);

                    if(kq>0){
                        listKhachHangs.clear();
                        listKhachHangs.addAll(khachHangDAO.getAll());
                        notifyDataSetChanged();
                        Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                    }
                    dialog.dismiss();
                }

            }
        });

        dialog.show();
    }

    public void showDialogUpdate(int vitri,KhachHang objKhachHang){
        Dialog dialog = new Dialog(context,R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setContentView(R.layout.dialog_khach_hang);

        TextInputEditText edTenKH = dialog.findViewById(R.id.edTenKH);
        TextInputEditText edSDT = dialog.findViewById(R.id.edSDT);
        Button btnLuu = dialog.findViewById(R.id.btnSaveKH);
        Button btnHuy = dialog.findViewById(R.id.btnCancelKH);


        edTenKH.setText(objKhachHang.getTenKH()+"");
        edSDT.setText(objKhachHang.getSDT()+"");

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (edTenKH.getText().toString().isEmpty()){
                    edTenKH.setError("Không để trống tên khách hàng");
                }

                else if (edSDT.getText().toString().isEmpty()){
                    edSDT.setError("Không để trống số điện thoại");
                }
                else if(edSDT.getText().length()!=10){
                    edSDT.setError("Số điện thoại phải là 10 số");
                }

                else {
                    objKhachHang.setTenKH(edTenKH.getText().toString());
                    objKhachHang.setSDT(edSDT.getText().toString());

                    int kq = khachHangDAO.updateRow(objKhachHang);

                    if(kq>0){
                        listKhachHangs.set(vitri,objKhachHang);
                        notifyDataSetChanged();
                        Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                    }
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
    }
}
