package fpt.thanhluan.quanlynhahang.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import fpt.thanhluan.quanlynhahang.Adapter.KhachHangAdapter;
import fpt.thanhluan.quanlynhahang.Adapter.LoaiDoUongAdapter;
import fpt.thanhluan.quanlynhahang.DAO.KhachHangDAO;
import fpt.thanhluan.quanlynhahang.DAO.LoaiDoUongDAO;
import fpt.thanhluan.quanlynhahang.DTO.KhachHang;
import fpt.thanhluan.quanlynhahang.DTO.LoaiDoUong;
import fpt.thanhluan.quanlynhahang.R;

public class LoaiDoUongFragment extends Fragment {


    ListView lvLoaiDoUong;
    FloatingActionButton btnAdd;
    LoaiDoUongDAO loaiDoUongDAO;
    LoaiDoUongAdapter loaiDoUongAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_loai_do_uong,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        lvLoaiDoUong = view.findViewById(R.id.lvLoaiDoUong);

        btnAdd = view.findViewById(R.id.fab);

        loaiDoUongDAO = new LoaiDoUongDAO(getContext());
        loaiDoUongDAO.open();
        loaiDoUongAdapter = new LoaiDoUongAdapter(getContext(), (ArrayList<LoaiDoUong>) loaiDoUongDAO.getAll(),loaiDoUongDAO);
        lvLoaiDoUong.setAdapter(loaiDoUongAdapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loaiDoUongAdapter.showDiaLogAdd();
            }
        });
    }
}
