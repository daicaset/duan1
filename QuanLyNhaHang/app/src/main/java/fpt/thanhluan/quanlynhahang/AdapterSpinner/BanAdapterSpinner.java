package fpt.thanhluan.quanlynhahang.AdapterSpinner;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import fpt.thanhluan.quanlynhahang.DTO.Ban;
import fpt.thanhluan.quanlynhahang.R;

public class BanAdapterSpinner extends BaseAdapter {

    public ArrayList<Ban> listBan;

    public BanAdapterSpinner(ArrayList<Ban> listBan) {
        this.listBan = listBan;
    }

    @Override
    public int getCount() {
        return listBan.size();
    }

    @Override
    public Object getItem(int i) {


        return listBan.get(i);
    }

    @Override
    public long getItemId(int i) {
        return listBan.get(i).getMaBan();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {


        View itemView ;

        if (view==null){
            itemView = View.inflate(viewGroup.getContext(), R.layout.spinner_item_ban,null);
        }else{
            itemView = view;
        }

        Ban objBan = listBan.get(i);

        TextView tvMaBan = itemView.findViewById(R.id.tvMaBan);
        tvMaBan.setText("Mã"+objBan.getMaBan()+"");
        TextView tvSoban = itemView.findViewById(R.id.tvSoban);
        tvSoban.setText("Số Bàn"+objBan.getSoBan()+"");

        return itemView;
    }
}
