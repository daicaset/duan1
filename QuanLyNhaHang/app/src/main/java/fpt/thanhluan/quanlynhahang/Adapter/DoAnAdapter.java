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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import fpt.thanhluan.quanlynhahang.AdapterSpinner.LoaiDoAnAdapterSpinner;
import fpt.thanhluan.quanlynhahang.AdapterSpinner.PhongAdapterSpinner;
import fpt.thanhluan.quanlynhahang.DAO.DoAnDAO;
import fpt.thanhluan.quanlynhahang.DAO.LoaiDoAnDAO;
import fpt.thanhluan.quanlynhahang.DAO.PhongDAO;
import fpt.thanhluan.quanlynhahang.DTO.Ban;
import fpt.thanhluan.quanlynhahang.DTO.DoAn;
import fpt.thanhluan.quanlynhahang.DTO.LoaiDoAn;
import fpt.thanhluan.quanlynhahang.DTO.Phong;
import fpt.thanhluan.quanlynhahang.R;

public class DoAnAdapter extends BaseAdapter {

    Context context;
    ArrayList<DoAn> listDoAns;
    DoAnDAO doAnDAO;

    public DoAnAdapter(Context context, ArrayList<DoAn> listDoAns, DoAnDAO doAnDAO) {
        this.context = context;
        this.listDoAns = listDoAns;
        this.doAnDAO = doAnDAO;
    }

    @Override
    public int getCount() {
        return listDoAns.size();
    }

    @Override
    public Object getItem(int position) {
        DoAn objDoAn = listDoAns.get(position);
        return objDoAn;
    }

    @Override
    public long getItemId(int position) {
        DoAn objDoAn = listDoAns.get(position);
        return objDoAn.getMaDA();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View itemview;
        //khởi tạo cho item

        if (view == null) {
            itemview = View.inflate(viewGroup.getContext(), R.layout.item_lv_doan, null);

        } else itemview = view;
        //lấy thông tin bản ghi dữ liệu
        final DoAn objDoAn = listDoAns.get(position);
        final int _index = position;

        //ánh xạ các biến
        TextView tvMaDA = itemview.findViewById(R.id.tvMaDA);
        TextView tvTenDA = itemview.findViewById(R.id.tvTenDA);
        TextView tvGiaDA = itemview.findViewById(R.id.tvGiaDA);
        TextView tvSoLuongDA = itemview.findViewById(R.id.tvSoLuongDA);
        TextView tvMaLoaiDA = itemview.findViewById(R.id.tvMaLoaiDA);

        ImageView imgXoa = itemview.findViewById(R.id.imgXoa);
        ImageView imgSua = itemview.findViewById(R.id.imgSua);


        //set text
        tvMaDA.setText(objDoAn.getMaDA() + "");
        tvTenDA.setText(objDoAn.getTenDA() + "");
        tvGiaDA.setText(objDoAn.getGiaDA() + " đồng");
        tvSoLuongDA.setText(objDoAn.getSoLuongDA() + "");
        tvMaLoaiDA.setText(objDoAn.getTenLoaiDA() + "");

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
                        int kq = doAnDAO.deleteRow(objDoAn);
                        if(kq>0){
                            listDoAns.remove(position);
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
        showDialogEdit(position,objDoAn);
            }
        });
        return itemview;
    }

    public void showDialogAdd(){

        Dialog dialog = new Dialog(context,R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setContentView(R.layout.dialog_do_an);

        TextInputEditText edTenDA = dialog.findViewById(R.id.edTenDA);
        TextInputEditText edGiaDA = dialog.findViewById(R.id.edGiaDA);
        TextInputEditText edSoLuongDA = dialog.findViewById(R.id.edSoLuongDA);

        Spinner spinner = dialog.findViewById(R.id.spinnerLoaiDA);

        LoaiDoAnDAO loaiDoAnDAO = new LoaiDoAnDAO(context);
        loaiDoAnDAO.open();

        LoaiDoAnAdapterSpinner loaiDoAnAdapterSpinner = new LoaiDoAnAdapterSpinner((ArrayList<LoaiDoAn>) loaiDoAnDAO.getAll());
        spinner.setAdapter(loaiDoAnAdapterSpinner);

        Button btnLuu = dialog.findViewById(R.id.btnSaveDA);
        Button btnHuy = dialog.findViewById(R.id.btnCancelDA);

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DoAn objDoAn = new DoAn();

                if (edTenDA.getText().toString().isEmpty()){
                    edTenDA.setError("Không để trống số tên");
                }
                else if (edGiaDA.getText().toString().isEmpty()){
                    edGiaDA.setError("Không để trống giá");
                }
                else if (edSoLuongDA.getText().toString().isEmpty()){
                    edSoLuongDA.setError("Không để trống số lượng");
                }else {
                    objDoAn.setTenDA(edTenDA.getText().toString());
                    objDoAn.setGiaDA(Integer.parseInt(edGiaDA.getText().toString()));
                    objDoAn.setSoLuongDA(Integer.parseInt(edSoLuongDA.getText().toString()));

                    LoaiDoAn objLoaiDoAn = (LoaiDoAn) spinner.getSelectedItem();
                    objDoAn.setMaLoaiDA(objLoaiDoAn.getMaLoaiDA());
                    objDoAn.setTenLoaiDA(objLoaiDoAn.getTenLoaiDA());

                    long kq = doAnDAO.insertRow(objDoAn);

                    if(kq>0){
                        listDoAns.clear();
                        listDoAns.addAll(doAnDAO.getAll());
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

    public void showDialogEdit(int vitri,DoAn objDoAn){
        Dialog dialog = new Dialog(context,R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setContentView(R.layout.dialog_do_an);

        TextInputEditText edTenDA = dialog.findViewById(R.id.edTenDA);
        TextInputEditText edGiaDA = dialog.findViewById(R.id.edGiaDA);
        TextInputEditText edSoLuongDA = dialog.findViewById(R.id.edSoLuongDA);

        Spinner spinner = dialog.findViewById(R.id.spinnerLoaiDA);

        LoaiDoAnDAO loaiDoAnDAO = new LoaiDoAnDAO(context);
        loaiDoAnDAO.open();

        LoaiDoAnAdapterSpinner loaiDoAnAdapterSpinner = new LoaiDoAnAdapterSpinner((ArrayList<LoaiDoAn>) loaiDoAnDAO.getAll());
        spinner.setAdapter(loaiDoAnAdapterSpinner);

        Button btnLuu = dialog.findViewById(R.id.btnSaveDA);
        Button btnHuy = dialog.findViewById(R.id.btnCancelDA);

        edTenDA.setText(objDoAn.getTenDA()+"");
        edGiaDA.setText(objDoAn.getGiaDA()+"");
        edSoLuongDA.setText(objDoAn.getSoLuongDA()+"");

        ArrayList<LoaiDoAn> listLoaiDoAn = (ArrayList<LoaiDoAn>) loaiDoAnDAO.getAll();

        for (int j = 0; j<listLoaiDoAn.size();j++){
            LoaiDoAn objLoaiDoAn = listLoaiDoAn.get(j);
            if(objLoaiDoAn.getMaLoaiDA()==objDoAn.getMaLoaiDA()){
                spinner.setSelection(j);
                spinner.setSelected(true);
                break;
            }
        }

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (edTenDA.getText().toString().isEmpty()){
                    edTenDA.setError("Không để trống số tên");
                }
                else if (edGiaDA.getText().toString().isEmpty()){
                    edGiaDA.setError("Không để trống giá");
                }
                else if (edSoLuongDA.getText().toString().isEmpty()){
                    edSoLuongDA.setError("Không để trống số lượng");
                }else{

                    objDoAn.setTenDA(edTenDA.getText().toString());
                    objDoAn.setGiaDA(Integer.parseInt(edGiaDA.getText().toString()));
                    objDoAn.setSoLuongDA(Integer.parseInt(edSoLuongDA.getText().toString()));

                    LoaiDoAn objLoaiDoAn = (LoaiDoAn) spinner.getSelectedItem();
                    objDoAn.setMaLoaiDA(objLoaiDoAn.getMaLoaiDA());
                    objDoAn.setTenLoaiDA(objLoaiDoAn.getTenLoaiDA());

                    int kq = doAnDAO.updateRow(objDoAn);

                    if(kq>0){
                        listDoAns.set(vitri,objDoAn);
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