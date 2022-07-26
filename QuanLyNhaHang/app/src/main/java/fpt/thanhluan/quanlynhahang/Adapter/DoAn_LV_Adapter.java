package fpt.thanhluan.quanlynhahang.Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.chip.Chip;

import java.util.ArrayList;

import fpt.thanhluan.quanlynhahang.DTO.DoAn;
import fpt.thanhluan.quanlynhahang.R;

public class DoAn_LV_Adapter extends BaseAdapter {
    ArrayList<DoAn> listDoAn;

    public DoAn_LV_Adapter(ArrayList<DoAn> listDoAn) {
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


        View itemView;

        if (view == null) {
            itemView = View.inflate(viewGroup.getContext(), R.layout.item_lv_do_an_hoadon, null);

        } else {
            itemView = view;

        }

        DoAn objDoAn = listDoAn.get(position);

        TextView tvTenDoan = itemView.findViewById(R.id.tvTenDoan);

        TextView tvhienthitrangthai = itemView.findViewById(R.id.tvhienthitrangthai);
        ImageButton btnthemdoan = itemView.findViewById(R.id.btnthemdoan);

        tvTenDoan.setText("Tên Đồ Ăn:" + objDoAn.getTenDA());

        btnthemdoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvhienthitrangthai.setText("Đã Chọn: "+objDoAn.getTenDA());
            }
        });


        return itemView;
    }


}
