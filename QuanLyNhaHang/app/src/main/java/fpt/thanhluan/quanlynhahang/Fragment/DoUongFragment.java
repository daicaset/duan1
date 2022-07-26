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

import fpt.thanhluan.quanlynhahang.Adapter.DoAnAdapter;
import fpt.thanhluan.quanlynhahang.Adapter.DoUongAdapter;
import fpt.thanhluan.quanlynhahang.DAO.DoAnDAO;
import fpt.thanhluan.quanlynhahang.DAO.DoUongDAO;
import fpt.thanhluan.quanlynhahang.DTO.DoAn;
import fpt.thanhluan.quanlynhahang.DTO.DoUong;
import fpt.thanhluan.quanlynhahang.R;

public class DoUongFragment extends Fragment {

    ListView lvDoUong;
    FloatingActionButton btnAdd;
    DoUongDAO doUongDAO;
    DoUongAdapter doUongAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_do_uong,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        lvDoUong = view.findViewById(R.id.lvDoUong);

        btnAdd = view.findViewById(R.id.fab);

        doUongDAO = new DoUongDAO(getContext());
        doUongDAO.open();
        doUongAdapter = new DoUongAdapter(getContext(), (ArrayList<DoUong>) doUongDAO.getAll(),doUongDAO);
        lvDoUong.setAdapter(doUongAdapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doUongAdapter.showDialogAdd();
            }
        });
    }
}
