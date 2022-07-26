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

import fpt.thanhluan.quanlynhahang.Adapter.ChamCongAdapter;
import fpt.thanhluan.quanlynhahang.DAO.ChamCongDAO;
import fpt.thanhluan.quanlynhahang.R;

public class DanhSachChamCongFragment extends Fragment {

    ListView lv_ds_chamcong;
    FloatingActionButton btnAdd;
    ChamCongDAO chamCongDAO;
    ChamCongAdapter chamCongAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ds_chamcong,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        lv_ds_chamcong = view.findViewById(R.id.lv_ds_chamcong);
        btnAdd = view.findViewById(R.id.fab);


        chamCongDAO = new ChamCongDAO(getContext());
        chamCongDAO.open();

        chamCongAdapter = new ChamCongAdapter(getContext(), chamCongDAO.getAll(),chamCongDAO);
        lv_ds_chamcong.setAdapter(chamCongAdapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chamCongAdapter.showDialogAdd();
            }
        });

    }


}
