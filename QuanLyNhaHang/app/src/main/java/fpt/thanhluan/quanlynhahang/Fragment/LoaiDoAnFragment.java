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
import fpt.thanhluan.quanlynhahang.Adapter.LoaiDoAnAdapter;
import fpt.thanhluan.quanlynhahang.DAO.KhachHangDAO;
import fpt.thanhluan.quanlynhahang.DAO.LoaiDoAnDAO;
import fpt.thanhluan.quanlynhahang.DTO.KhachHang;
import fpt.thanhluan.quanlynhahang.DTO.LoaiDoAn;
import fpt.thanhluan.quanlynhahang.R;

public class LoaiDoAnFragment extends Fragment {


    ListView lvLoaiDoAn;
    FloatingActionButton btnAdd;
    LoaiDoAnDAO loaiDoAnDAO;
    LoaiDoAnAdapter loaiDoAnAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_loai_do_an,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        lvLoaiDoAn = view.findViewById(R.id.lvLoaiDoAn);

        btnAdd = view.findViewById(R.id.fab);

        loaiDoAnDAO = new LoaiDoAnDAO(getContext());
        loaiDoAnDAO.open();
        loaiDoAnAdapter = new LoaiDoAnAdapter(getContext(), (ArrayList<LoaiDoAn>) loaiDoAnDAO.getAll(),loaiDoAnDAO);
        lvLoaiDoAn.setAdapter(loaiDoAnAdapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loaiDoAnAdapter.showDiaLogAdd();
            }
        });
    }
}
