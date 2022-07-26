package fpt.thanhluan.quanlynhahang.AdapterSpinner;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import fpt.thanhluan.quanlynhahang.DTO.Phong;
import fpt.thanhluan.quanlynhahang.R;

public class PhongAdapterSpinner extends BaseAdapter {

    public ArrayList<Phong> listPhong;

    public PhongAdapterSpinner(ArrayList<Phong> listPhong) {
        this.listPhong = listPhong;
    }

    @Override
    public int getCount() {
        return listPhong.size();
    }

    @Override
    public Object getItem(int position) {
        return listPhong.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listPhong.get(position).getMaPhong();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View itemView ;

        if (view==null){
            itemView = View.inflate(viewGroup.getContext(), R.layout.spinner_item_phong,null);
        }else{
            itemView = view;
        }

        Phong objPhong = listPhong.get(position);

        TextView tvSoPhong = itemView.findViewById(R.id.tvSoPhong);
        tvSoPhong.setText(objPhong.getSoPhong()+"");

        return itemView;
    }
}
