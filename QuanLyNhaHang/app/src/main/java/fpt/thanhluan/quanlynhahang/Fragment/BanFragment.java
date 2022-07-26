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

import fpt.thanhluan.quanlynhahang.Adapter.BanAdapter;
import fpt.thanhluan.quanlynhahang.DAO.BanDAO;
import fpt.thanhluan.quanlynhahang.DTO.Ban;
import fpt.thanhluan.quanlynhahang.R;

public class BanFragment extends Fragment {

    ListView lvBan;
    FloatingActionButton btnAdd;
    BanDAO banDAO;
    BanAdapter banAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ban,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        lvBan = view.findViewById(R.id.lvBan);

        btnAdd = view.findViewById(R.id.fab);

        banDAO = new BanDAO(getContext());
        banDAO.open();
        banAdapter = new BanAdapter(getContext(), banDAO.getAll(),banDAO);
        lvBan.setAdapter(banAdapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                banAdapter.showDialogAdd();
            }
        });
    }
}
