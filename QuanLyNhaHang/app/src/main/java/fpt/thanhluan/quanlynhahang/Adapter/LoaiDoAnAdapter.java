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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import fpt.thanhluan.quanlynhahang.DAO.LoaiDoAnDAO;
import fpt.thanhluan.quanlynhahang.DAO.PhongDAO;
import fpt.thanhluan.quanlynhahang.DTO.LoaiDoAn;
import fpt.thanhluan.quanlynhahang.DTO.Phong;
import fpt.thanhluan.quanlynhahang.R;

public class LoaiDoAnAdapter extends BaseAdapter {
    Context context;
    ArrayList<LoaiDoAn> listLoaiDoAns;
    LoaiDoAnDAO loaiDoAnDAO;

    public LoaiDoAnAdapter(Context context, ArrayList<LoaiDoAn> listLoaiDoAns, LoaiDoAnDAO loaiDoAnDAO) {
        this.context = context;
        this.listLoaiDoAns = listLoaiDoAns;
        this.loaiDoAnDAO = loaiDoAnDAO;
    }

    @Override
    public int getCount() {
        return listLoaiDoAns.size();
    }

    @Override
    public Object getItem(int position) {

        LoaiDoAn objLoaiDoAn = listLoaiDoAns.get(position);
        return objLoaiDoAn;
    }

    @Override
    public long getItemId(int position) {
        LoaiDoAn objLoaiDoAn = listLoaiDoAns.get(position);
        return objLoaiDoAn.getMaLoaiDA();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View itemview;
        //khởi tạo cho item

        if (view == null) {
            itemview = View.inflate(viewGroup.getContext(), R.layout.item_lv_loaidoan, null);

        } else itemview = view;
        //lấy thôgn tin bản ghi dữ liệu
        final LoaiDoAn objLoaiDoAn = listLoaiDoAns.get(position);
        final int _index = position;

//ánh xạ các bviuến
        TextView tvMaLoaiDA = itemview.findViewById(R.id.tvMaLoaiDA);
        TextView tvTenLoaiDA = itemview.findViewById(R.id.tvTenLoaiDA);
        ImageView imgXoa = itemview.findViewById(R.id.imgXoa);

        //set text
        tvMaLoaiDA.setText(objLoaiDoAn.getMaLoaiDA() + "");
        tvTenLoaiDA.setText(objLoaiDoAn.getTenLoaiDA() + "");


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
                        int kq = loaiDoAnDAO.deleteRow(objLoaiDoAn);
                        if(kq>0){
                            listLoaiDoAns.remove(position);
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



    public void showDiaLogAdd() {

        Dialog dialog = new Dialog(context, R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setContentView(R.layout.dialog_loai_do_an);

        RadioGroup rdg_loai_do_an = dialog.findViewById(R.id.rdg_loai_do_an);
        RadioButton rdb_doan_nhanh = dialog.findViewById(R.id.rdb_doan_nhanh);
        RadioButton rdb_doan_chebien = dialog.findViewById(R.id.rdb_doan_chebien);
        RadioButton rdb_banh = dialog.findViewById(R.id.rdb_banh);

        Button btnLuu = dialog.findViewById(R.id.btnSaveLoaiDA);
        Button btnHuy = dialog.findViewById(R.id.btnCancelLoaiDA);

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoaiDoAn objLoaiDoAn = new LoaiDoAn();

                int id_rdb_check = rdg_loai_do_an.getCheckedRadioButtonId();

                switch (id_rdb_check){

                    case R.id.rdb_doan_nhanh :
                        objLoaiDoAn.setTenLoaiDA("Đồ ăn nhanh");
                        break;
                    case R.id.rdb_doan_chebien:
                        objLoaiDoAn.setTenLoaiDA("Đồ ăn chế biến");
                        break;
                    case R.id.rdb_banh:
                        objLoaiDoAn.setTenLoaiDA("Bánh");
                        break;
                }

                    long kq = loaiDoAnDAO.insertRow(objLoaiDoAn);

                    if (kq > 0) {
                        listLoaiDoAns.clear();
                        listLoaiDoAns.addAll(loaiDoAnDAO.getAll());
                        notifyDataSetChanged();
                        Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                    }
                    dialog.dismiss();
            }
        });

        dialog.show();
    }



}
