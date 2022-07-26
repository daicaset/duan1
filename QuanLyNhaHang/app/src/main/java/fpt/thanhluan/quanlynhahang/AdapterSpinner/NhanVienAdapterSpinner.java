package fpt.thanhluan.quanlynhahang.AdapterSpinner;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fpt.thanhluan.quanlynhahang.DTO.NhanVien;
import fpt.thanhluan.quanlynhahang.DTO.Phong;
import fpt.thanhluan.quanlynhahang.R;

public class NhanVienAdapterSpinner extends BaseAdapter {

    public ArrayList<NhanVien> list;

    public NhanVienAdapterSpinner(ArrayList<NhanVien> list) {
        this.list = list;
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
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View itemView ;

        if (view==null){
            itemView = View.inflate(viewGroup.getContext(), R.layout.spinner_item_nhanvien,null);
        }else{
            itemView = view;
        }

        NhanVien objNhanVien = list.get(position);

        TextView tvTenNV = itemView.findViewById(R.id.tvTenNV);
        tvTenNV.setText(objNhanVien.getHoTen()+"");

        return itemView;
    }
}
