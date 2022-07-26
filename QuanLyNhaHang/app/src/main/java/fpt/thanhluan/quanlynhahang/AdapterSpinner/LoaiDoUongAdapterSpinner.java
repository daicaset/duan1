package fpt.thanhluan.quanlynhahang.AdapterSpinner;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import fpt.thanhluan.quanlynhahang.DTO.LoaiDoUong;
import fpt.thanhluan.quanlynhahang.R;

public class LoaiDoUongAdapterSpinner extends BaseAdapter {

    public ArrayList<LoaiDoUong> listLoaiDoUong;

    public LoaiDoUongAdapterSpinner(ArrayList<LoaiDoUong> listLoaiDoUong) {
        this.listLoaiDoUong = listLoaiDoUong;
    }

    @Override
    public int getCount() {
        return listLoaiDoUong.size();
    }

    @Override
    public Object getItem(int position) {
        return listLoaiDoUong.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listLoaiDoUong.get(position).getMaLoaiDU();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View itemView ;

        if (view==null){
            itemView = View.inflate(viewGroup.getContext(), R.layout.spinner_item_loai_do_uong,null);
        }else{
            itemView = view;
        }

        LoaiDoUong objLoaiDoUong = listLoaiDoUong.get(position);

        TextView tvTenLoaiDU = itemView.findViewById(R.id.tvTenLoaiDU);
        tvTenLoaiDU.setText(objLoaiDoUong.getTenLoaiDU()+"");

        return itemView;
    }
}
