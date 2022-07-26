package fpt.thanhluan.quanlynhahang.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import fpt.thanhluan.quanlynhahang.Adapter.NhanVienAdapter;
import fpt.thanhluan.quanlynhahang.DAO.NhanVienDAO;
import fpt.thanhluan.quanlynhahang.DTO.NhanVien;
import fpt.thanhluan.quanlynhahang.R;


public class DanhSachNhanVienFragment extends Fragment {

    ListView lv_nhanvien;
    NhanVienDAO nhanVienDAO;
    NhanVienAdapter nhanVienAdapter;
    FloatingActionButton btnSearch;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dsnhanvien,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        lv_nhanvien = view.findViewById(R.id.lv_nhanvien);
        nhanVienDAO = new NhanVienDAO(getContext());
        nhanVienDAO.open();
        nhanVienAdapter = new NhanVienAdapter(getContext(), (ArrayList<NhanVien>) nhanVienDAO.getAll(),nhanVienDAO);
        lv_nhanvien.setAdapter(nhanVienAdapter);

        btnSearch = view.findViewById(R.id.fab);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nhanVienAdapter.showDialogSearch();
            }
        });

    }
}
