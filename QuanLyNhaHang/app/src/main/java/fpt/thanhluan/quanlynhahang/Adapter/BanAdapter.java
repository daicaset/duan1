package fpt.thanhluan.quanlynhahang.Adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import fpt.thanhluan.quanlynhahang.AdapterSpinner.PhongAdapterSpinner;
import fpt.thanhluan.quanlynhahang.DAO.BanDAO;
import fpt.thanhluan.quanlynhahang.DAO.PhongDAO;
import fpt.thanhluan.quanlynhahang.DTO.Ban;
import fpt.thanhluan.quanlynhahang.DTO.Phong;
import fpt.thanhluan.quanlynhahang.R;

public class BanAdapter extends BaseAdapter {

    Context context;
    ArrayList<Ban> listBan;
    BanDAO banDAO;

    public BanAdapter(Context context, ArrayList<Ban> listBan, BanDAO banDAO) {
        this.context = context;
        this.listBan = listBan;
        this.banDAO = banDAO;
    }

    @Override
    public int getCount() {
        return listBan.size();
    }

    @Override
    public Object getItem(int position) {
        Ban objBan = listBan.get(position);
        return objBan;
    }

    @Override
    public long getItemId(int position) {
        Ban objBan = listBan.get(position);
        return objBan.getMaBan();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View itemview;
        //khởi tạo cho item
        if (view == null) {
            itemview = View.inflate(viewGroup.getContext(), R.layout.item_lv_ban, null);

        } else itemview = view;
        //lấy thôgn tin bản ghi dữ liệu
        final Ban objBan = listBan.get(position);
        final int _index = position;


       //ánh xạ các biến
        TextView tvMaBan = itemview.findViewById(R.id.tvMaBan);
        TextView tvSoBan = itemview.findViewById(R.id.tvSoBan);
        TextView tvTrangThai = itemview.findViewById(R.id.tvTrangThai);
        TextView tvSoPhong = itemview.findViewById(R.id.tvSoPhong);
        ImageView imgXoa = itemview.findViewById(R.id.imgXoa);
        ImageView imgSua = itemview.findViewById(R.id.imgSua);

        //set text
        tvMaBan.setText(objBan.getMaBan() + "");
        tvSoBan.setText(objBan.getSoBan() + "");

        if(objBan.getTrangThai()==0){
            tvTrangThai.setText("chưa sử dụng");
            tvTrangThai.setTypeface(Typeface.DEFAULT_BOLD);
        }else{
            tvTrangThai.setText("đã sử dụng");
            tvTrangThai.setTypeface(Typeface.DEFAULT_BOLD);
        }
        tvSoPhong.setText(objBan.getSoPhong() + "");



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
                        int kq = banDAO.deleteRow(objBan);
                        if(kq>0){
                            listBan.remove(position);
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

                showDialogEdit(position,objBan);

            }
        });

        return itemview;
    }

    public void showDialogAdd(){

        Dialog dialog = new Dialog(context,R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setContentView(R.layout.dialog_ban);

        TextInputEditText edSoBan = dialog.findViewById(R.id.edSoBan);
        RadioGroup rdg = dialog.findViewById(R.id.rdg_ban);

        RadioButton rdb_0 = dialog.findViewById(R.id.rdb_chuasudung);
        RadioButton rdb_1 = dialog.findViewById(R.id.rdb_dasudung);
        Spinner spinner = dialog.findViewById(R.id.spinnerPhong);

        PhongDAO phongDAO = new PhongDAO(context);
        phongDAO.open();
        PhongAdapterSpinner phongAdapterSpinner = new PhongAdapterSpinner(phongDAO.getAll());
        spinner.setAdapter(phongAdapterSpinner);

        Button btnLuu = dialog.findViewById(R.id.btnSaveBan);
        Button btnHuy = dialog.findViewById(R.id.btnCancelBan);

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Ban objBan = new Ban();

                if (edSoBan.getText().toString().isEmpty()){
                    edSoBan.setError("Không để trống số bàn");
                }

                else{

                    objBan.setSoBan(Integer.parseInt(edSoBan.getText().toString()));

                    int id_rd_check = rdg.getCheckedRadioButtonId();

                    switch (id_rd_check){
                        case R.id.rdb_chuasudung:
                            objBan.setTrangThai(0);
                            break;
                        case R.id.rdb_dasudung:
                            objBan.setTrangThai(1);
                            break;
                    }

                    Phong objPhong = (Phong) spinner.getSelectedItem();
                    objBan.setMaPhong(objPhong.getMaPhong());
                    objBan.setSoPhong(objPhong.getSoPhong());

                    long kq = banDAO.insertRow(objBan);

                    if(kq>0){
                        listBan.clear();
                        listBan.addAll(banDAO.getAll());
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

    public void showDialogEdit(int vitri,Ban objBan){
        Dialog dialog = new Dialog(context,R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setContentView(R.layout.dialog_ban);

        TextInputEditText edSoBan = dialog.findViewById(R.id.edSoBan);

        RadioGroup rdg = dialog.findViewById(R.id.rdg_ban);

        RadioButton rdb_0 = dialog.findViewById(R.id.rdb_chuasudung);
        RadioButton rdb_1 = dialog.findViewById(R.id.rdb_dasudung);

        Spinner spinner = dialog.findViewById(R.id.spinnerPhong);

        PhongDAO phongDAO = new PhongDAO(context);
        phongDAO.open();
        PhongAdapterSpinner phongAdapterSpinner = new PhongAdapterSpinner((ArrayList<Phong>) phongDAO.getAll());
        spinner.setAdapter(phongAdapterSpinner);

        Button btnLuu = dialog.findViewById(R.id.btnSaveBan);
        Button btnHuy = dialog.findViewById(R.id.btnCancelBan);

        edSoBan.setText(objBan.getSoBan()+"");

        if(objBan.getTrangThai()==0){
            rdb_0.setSelected(false);
        }else {
            rdb_1.setSelected(false);
        }


        ArrayList<Phong> listPhong = (ArrayList<Phong>) phongDAO.getAll();

        for (int j = 0; j<listPhong.size();j++){
            Phong objPhong = listPhong.get(j);
            if(objPhong.getMaPhong()==objBan.getMaPhong()){
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

                if (edSoBan.getText().toString().isEmpty()){
                    edSoBan.setError("Không để trống số bàn");
                }


                else {

                    objBan.setSoBan(Integer.parseInt(edSoBan.getText().toString()));

                    int id_rd_check = rdg.getCheckedRadioButtonId();

                    switch (id_rd_check){
                        case R.id.rdb_chuasudung:
                            objBan.setTrangThai(0);
                            break;
                        case R.id.rdb_dasudung:
                            objBan.setTrangThai(1);
                            break;
                    }

                    Phong objPhong = (Phong) spinner.getSelectedItem();
                    objBan.setMaPhong(objPhong.getMaPhong());
                    objBan.setSoPhong(objPhong.getSoPhong());

                    int kq = banDAO.updateRow(objBan);

                    if(kq>0){
                        listBan.set(vitri,objBan);
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
