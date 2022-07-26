package fpt.thanhluan.quanlynhahang.Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import fpt.thanhluan.quanlynhahang.DTO.DoAn;
import fpt.thanhluan.quanlynhahang.DTO.DoUong;
import fpt.thanhluan.quanlynhahang.R;

public class DoUong_LV_Adapter extends BaseAdapter {


    ArrayList<DoUong> listDoUong;

    public DoUong_LV_Adapter(ArrayList<DoUong> listDoUong) {
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


        View itemView;

        if (view == null) {
            itemView = View.inflate(viewGroup.getContext(), R.layout.item_lv_do_uong_hoadon, null);

        } else {
            itemView = view;

        }

        DoUong objDoUong = listDoUong.get(position);

        TextView tvTenDouong = itemView.findViewById(R.id.tvTenDouong);
        TextView tvSizedouong = itemView.findViewById(R.id.tvSizedouong);
        TextView tvhienthitrangthai = itemView.findViewById(R.id.tvhienthitrangthai);
        ImageButton btnthemdouong = itemView.findViewById(R.id.btnthemdouong);

        tvTenDouong.setText("Tên Đồ Ăn:" + objDoUong.getTenDU());
        tvSizedouong.setText("Size:" + objDoUong.getSizeDU());
        btnthemdouong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvhienthitrangthai.setText("Đã Chọn: "+objDoUong.getTenDU());
            }
        });


        return itemView;
    }


}
