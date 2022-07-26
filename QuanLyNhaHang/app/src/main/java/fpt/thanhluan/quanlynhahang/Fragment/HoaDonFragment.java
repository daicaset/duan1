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
import fpt.thanhluan.quanlynhahang.Adapter.HoaDonAdapter;
import fpt.thanhluan.quanlynhahang.DAO.DoUongDAO;
import fpt.thanhluan.quanlynhahang.DAO.HoaDonDAO;
import fpt.thanhluan.quanlynhahang.DTO.DoUong;
import fpt.thanhluan.quanlynhahang.DTO.HoaDon;
import fpt.thanhluan.quanlynhahang.R;

public class HoaDonFragment extends Fragment {

    ListView lvHoaDon;
    FloatingActionButton btnAdd;
    HoaDonDAO hoaDonDAO;
    HoaDonAdapter hoaDonAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_hoa_don,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        lvHoaDon = view.findViewById(R.id.lvHoaDon);

        btnAdd = view.findViewById(R.id.fab);

        hoaDonDAO = new HoaDonDAO(getContext());
        hoaDonDAO.open();
        hoaDonAdapter = new HoaDonAdapter(getContext(), (ArrayList<HoaDon>) hoaDonDAO.getAll(),hoaDonDAO);
        lvHoaDon.setAdapter(hoaDonAdapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hoaDonAdapter.showDialogAdd();
            }
        });
    }
}
