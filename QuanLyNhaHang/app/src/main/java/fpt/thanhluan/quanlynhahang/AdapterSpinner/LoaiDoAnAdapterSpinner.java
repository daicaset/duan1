package fpt.thanhluan.quanlynhahang.AdapterSpinner;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import fpt.thanhluan.quanlynhahang.DTO.LoaiDoAn;
import fpt.thanhluan.quanlynhahang.R;

public class LoaiDoAnAdapterSpinner extends BaseAdapter {

    public ArrayList<LoaiDoAn> listLoaiDoAn;

    public LoaiDoAnAdapterSpinner(ArrayList<LoaiDoAn> listLoaiDoAn) {
        this.listLoaiDoAn = listLoaiDoAn;
    }

    @Override
    public int getCount() {
        return listLoaiDoAn.size();
    }

    @Override
    public Object getItem(int position) {
        return listLoaiDoAn.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listLoaiDoAn.get(position).getMaLoaiDA();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View itemView ;

        if (view==null){
            itemView = View.inflate(viewGroup.getContext(), R.layout.spinner_item_loai_do_an,null);
        }else{
            itemView = view;
        }

        LoaiDoAn objLoaiDoAn = listLoaiDoAn.get(position);

        TextView tvTenLoaiDA = itemView.findViewById(R.id.tvTenLoaiDA);
        tvTenLoaiDA.setText(objLoaiDoAn.getTenLoaiDA()+"");

        return itemView;
    }
}
