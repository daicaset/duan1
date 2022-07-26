package fpt.thanhluan.quanlynhahang.AdapterSpinner;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import fpt.thanhluan.quanlynhahang.DTO.DoAn;
import fpt.thanhluan.quanlynhahang.DTO.DoUong;
import fpt.thanhluan.quanlynhahang.R;

public class DoUongAdapterSpinner extends BaseAdapter {

    ArrayList<DoUong> listDoUong;

    public DoUongAdapterSpinner(ArrayList<DoUong> listDoUong) {
        this.listDoUong = listDoUong;
    }

    @Override
    public int getCount() {
        return listDoUong.size();
    }

    @Override
    public Object getItem(int position) {
        return listDoUong.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listDoUong.get(position).getMaDU();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {


        View itemView ;

        if (view==null){
            itemView = View.inflate(viewGroup.getContext(), R.layout.spinner_item_do_uong,null);
        }else{
            itemView = view;
        }

        DoUong objDoUong = listDoUong.get(position);

        TextView tvMaDU = itemView.findViewById(R.id.tvMaDU);
        tvMaDU.setText(objDoUong.getMaDU()+"");
        TextView tvTenDU = itemView.findViewById(R.id.tvTenDU);
        tvTenDU.setText(objDoUong.getTenDU()+"");
        TextView tvGiaDU = itemView.findViewById(R.id.tvGiaDU);
        tvGiaDU.setText(objDoUong.getGiaDU()+"");
        TextView tvSoLuongDU = itemView.findViewById(R.id.tvSoLuongDU);
        tvSoLuongDU.setText(objDoUong.getSoLuongDU()+"");
        TextView tvSizeDU = itemView.findViewById(R.id.tvSizeDU);
        tvSizeDU.setText(objDoUong.getSizeDU()+"");

        return itemView;
    }
}
