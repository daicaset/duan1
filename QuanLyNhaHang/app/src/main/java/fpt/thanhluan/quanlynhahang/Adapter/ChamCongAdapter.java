package fpt.thanhluan.quanlynhahang.Adapter;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Calendar;

import fpt.thanhluan.quanlynhahang.AdapterSpinner.NhanVienAdapterSpinner;
import fpt.thanhluan.quanlynhahang.AdapterSpinner.PhongAdapterSpinner;
import fpt.thanhluan.quanlynhahang.DAO.ChamCongDAO;

import fpt.thanhluan.quanlynhahang.DAO.NhanVienDAO;
import fpt.thanhluan.quanlynhahang.DAO.PhongDAO;
import fpt.thanhluan.quanlynhahang.DTO.ChamCong;
import fpt.thanhluan.quanlynhahang.DTO.NhanVien;
import fpt.thanhluan.quanlynhahang.DTO.Phong;
import fpt.thanhluan.quanlynhahang.R;

public class ChamCongAdapter extends BaseAdapter {

    Context context;
    ArrayList<ChamCong> list;
    ChamCongDAO chamCongDAO;

    public ChamCongAdapter(Context context, ArrayList<ChamCong> list, ChamCongDAO chamCongDAO) {
        this.context = context;
        this.list = list;
        this.chamCongDAO = chamCongDAO;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return list.get(position).getMaChamCong();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View itemView;

        if(view==null){
            itemView = View.inflate(viewGroup.getContext(), R.layout.item_lv_chamcong, null);
        }else {
            itemView = view;
        }
        //lấy thông tin bản ghi dữ liệu
        final ChamCong objChamCong = list.get(position);
        final int _index = position;


        //ánh xạ các biến
        TextView tvNgayCong = itemView.findViewById(R.id.tvNgayCong);
        TextView tvTenNV = itemView.findViewById(R.id.tvTenNV);
        TextView tvCaLam = itemView.findViewById(R.id.tvCaLam);
        TextView tvGioBatDau = itemView.findViewById(R.id.tvGioBatDau);
        TextView tvGioKetThuc = itemView.findViewById(R.id.tvGioKetThuc);

        tvNgayCong.setText(objChamCong.getNgayCong()+"");
        tvTenNV.setText(objChamCong.getHoTen()+"");
        tvCaLam.setText(objChamCong.getCaLam()+"");
        tvGioBatDau.setText(objChamCong.getGioBatDau()+"");
        tvGioKetThuc.setText(objChamCong.getGioKetThuc()+"");


        return itemView;

    }


    public void showDialogAdd(){

        Dialog dialog = new Dialog(context,R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setContentView(R.layout.dialog_chamcong);

        EditText edNgay = dialog.findViewById(R.id.edNgay);


        RadioGroup rdg_ca_lam_viec = dialog.findViewById(R.id.rdg_ca_lam_viec);
        RadioButton rdb_sang = dialog.findViewById(R.id.rdb_sang);
        RadioButton rdb_chieu = dialog.findViewById(R.id.rdb_chieu);
        RadioButton rdb_toi = dialog.findViewById(R.id.rdb_toi);

        Spinner spinnerHoTen = dialog.findViewById(R.id.spinnerHoTen);

        NhanVienDAO nhanVienDAO = new NhanVienDAO(context);
        nhanVienDAO.open();

        NhanVienAdapterSpinner adapterSpinner = new NhanVienAdapterSpinner((ArrayList<NhanVien>) nhanVienDAO.getAll());
        spinnerHoTen.setAdapter(adapterSpinner);

        EditText ed_gio_batdau = dialog.findViewById(R.id.ed_gio_batdau);
        EditText ed_gio_ketthuc = dialog.findViewById(R.id.ed_gio_ketthuc);

        Button btnHuy = dialog.findViewById(R.id.btnHuy);
        Button btnLuu = dialog.findViewById(R.id.btnLuu);

        ChamCongDAO chamCongDAO = new ChamCongDAO(context);
        chamCongDAO.open();

        edNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis( System.currentTimeMillis() );

                DatePickerDialog datePickerDialog = new DatePickerDialog(context,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                                int ngay = i2;
                                int thang = i1;
                                int nam = i;
                             edNgay.setText( ngay + "/" + (thang + 1) + "/" + nam );
                            }
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DATE)
                );
                datePickerDialog.show();
            }
        });

        rdb_sang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ed_gio_batdau.setText("07:00");
                ed_gio_ketthuc.setText("11:00");
            }
        });
        rdb_chieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ed_gio_batdau.setText("13:00");
                ed_gio_ketthuc.setText("17:00");
            }
        });
        rdb_toi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ed_gio_batdau.setText("19:00");
                ed_gio_ketthuc.setText("22:00");
            }
        });

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
          dialog.dismiss();
            }
        });

        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ChamCong objChamCong = new ChamCong();

                if(edNgay.getText().toString().isEmpty()){
                    edNgay.setError("Không để trống ngày");
                }else if(ed_gio_batdau.getText().toString().isEmpty()){
                    ed_gio_batdau.setError("Không để trống giờ");
                }else if(ed_gio_ketthuc.getText().toString().isEmpty()){
                    ed_gio_ketthuc.setError("Không để trống giờ");
                }else
                {
                    objChamCong.setNgayCong(edNgay.getText().toString());

                    NhanVien objNhanVien = (NhanVien) spinnerHoTen.getSelectedItem();
                    objChamCong.setMaNV(objNhanVien.getMaNV()+"");
                    objChamCong.setHoTen(objNhanVien.getHoTen()+"");


                    int id_rdb_check = rdg_ca_lam_viec.getCheckedRadioButtonId();

                    switch (id_rdb_check){

                        case R.id.rdb_sang:
                            objChamCong.setCaLam("Sáng");
                            break;

                        case R.id.rdb_chieu:
                            objChamCong.setCaLam("Chiều");
                            break;
                        case R.id.rdb_toi:
                            objChamCong.setCaLam("Tối");
                            break;
                    }

                    objChamCong.setGioBatDau(ed_gio_batdau.getText().toString());
                    objChamCong.setGioKetThuc(ed_gio_ketthuc.getText().toString());


                    long kq = chamCongDAO.insertRow(objChamCong);

                    if(kq>0){
                        list.clear();
                        list.addAll(chamCongDAO.getAll());
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

}
