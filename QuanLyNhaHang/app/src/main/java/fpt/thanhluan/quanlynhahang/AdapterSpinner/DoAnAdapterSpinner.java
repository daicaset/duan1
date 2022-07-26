package fpt.thanhluan.quanlynhahang.AdapterSpinner;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import fpt.thanhluan.quanlynhahang.DTO.DoAn;
import fpt.thanhluan.quanlynhahang.R;

public class DoAnAdapterSpinner extends BaseAdapter {
    ArrayList<DoAn> listDoAn;

    public DoAnAdapterSpinner(ArrayList<DoAn> listDoAn) {
        this.listDoAn = listDoAn;
    }

    @Override
    public int getCount() {
        return listDoAn.size();
    }

    @Override
    public Object getItem(int position) {
        return listDoAn.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listDoAn.get(position).getMaDA();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {


        View itemView ;

        if (view==null){
            itemView = View.inflate(viewGroup.getContext(), R.layout.spinner_item_do_an,null);
        }else{
            itemView = view;
        }

        DoAn objDoAn = listDoAn.get(position);

        TextView tvMaDA = itemView.findViewById(R.id.tvMaDA);
        tvMaDA.setText(objDoAn.getMaDA()+"");
        TextView tvTenDA = itemView.findViewById(R.id.tvTenDA);
        tvTenDA.setText(objDoAn.getTenDA()+"");
        TextView tvGiaDA = itemView.findViewById(R.id.tvGiaDA);
        tvGiaDA.setText(objDoAn.getGiaDA()+"");
        TextView tvSoLuongDA = itemView.findViewById(R.id.tvSoLuongDA);
        tvSoLuongDA.setText(objDoAn.getSoLuongDA()+"");

        return itemView;
    }
}
