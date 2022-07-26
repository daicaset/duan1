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

import fpt.thanhluan.quanlynhahang.Adapter.DoUongAdapter;
import fpt.thanhluan.quanlynhahang.Adapter.KhachHangAdapter;
import fpt.thanhluan.quanlynhahang.DAO.DoUongDAO;
import fpt.thanhluan.quanlynhahang.DAO.KhachHangDAO;
import fpt.thanhluan.quanlynhahang.DTO.DoUong;
import fpt.thanhluan.quanlynhahang.DTO.KhachHang;
import fpt.thanhluan.quanlynhahang.R;

public class KhachHangFragment extends Fragment {

    ListView lvKhachHang;
    FloatingActionButton btnAdd;
    KhachHangDAO khachHangDAO;
    KhachHangAdapter khachHangAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_khach_hang,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        lvKhachHang = view.findViewById(R.id.lvKhachHang);

        btnAdd = view.findViewById(R.id.fab);

        khachHangDAO = new KhachHangDAO(getContext());
        khachHangDAO.open();
        khachHangAdapter = new KhachHangAdapter(getContext(), (ArrayList<KhachHang>) khachHangDAO.getAll(),khachHangDAO);
        lvKhachHang.setAdapter(khachHangAdapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                khachHangAdapter.showDiaLogAdd();
            }
        });
    }
}
