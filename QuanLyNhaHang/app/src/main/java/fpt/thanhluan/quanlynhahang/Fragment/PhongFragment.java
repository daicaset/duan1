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

import fpt.thanhluan.quanlynhahang.Adapter.LoaiDoUongAdapter;
import fpt.thanhluan.quanlynhahang.Adapter.PhongAdapter;
import fpt.thanhluan.quanlynhahang.DAO.LoaiDoUongDAO;
import fpt.thanhluan.quanlynhahang.DAO.PhongDAO;
import fpt.thanhluan.quanlynhahang.DTO.LoaiDoUong;
import fpt.thanhluan.quanlynhahang.DTO.Phong;
import fpt.thanhluan.quanlynhahang.R;

public class PhongFragment extends Fragment {



    ListView lvPhong;
    FloatingActionButton btnAdd;
    PhongDAO phongDAO;
    PhongAdapter phongAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_phong,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        lvPhong = view.findViewById(R.id.lvPhong);

        btnAdd = view.findViewById(R.id.fab);

        phongDAO = new PhongDAO(getContext());
        phongDAO.open();
        phongAdapter = new PhongAdapter(getContext(), (ArrayList<Phong>) phongDAO.getAll(),phongDAO);
        lvPhong.setAdapter(phongAdapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phongAdapter.showDiaLogAdd();
            }
        });
    }
}
