package fpt.thanhluan.quanlynhahang.AdapterSpinner;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import fpt.thanhluan.quanlynhahang.DTO.KhachHang;
import fpt.thanhluan.quanlynhahang.R;

public class KhachHangAdapterSpinner extends BaseAdapter {

    public ArrayList<KhachHang> listKhachHang;

    public KhachHangAdapterSpinner(ArrayList<KhachHang> listKhachHang) {
        this.listKhachHang = listKhachHang;
    }

    @Override
    public int getCount() {
        return listKhachHang.size();
    }

    @Override
    public Object getItem(int position) {
        return listKhachHang.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listKhachHang.get(position).getMaKH();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View itemView ;

        if (view==null){
            itemView = View.inflate(viewGroup.getContext(), R.layout.spinner_item_khach_hang,null);
        }else{
            itemView = view;
        }

        KhachHang objKhachHang = listKhachHang.get(position);

        TextView tvMaKH = itemView.findViewById(R.id.tvMaKH);
        tvMaKH.setText(objKhachHang.getMaKH()+"");
        TextView tvTenKH = itemView.findViewById(R.id.tvTenKH);
        tvTenKH.setText(objKhachHang.getTenKH()+"");
        TextView tvSDT = itemView.findViewById(R.id.tvSDT);
        tvSDT.setText(objKhachHang.getSDT()+"");

        return itemView;
    }
}
