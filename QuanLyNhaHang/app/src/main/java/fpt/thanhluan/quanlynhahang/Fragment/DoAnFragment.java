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
import fpt.thanhluan.quanlynhahang.DAO.DoAnDAO;
import fpt.thanhluan.quanlynhahang.DTO.DoAn;
import fpt.thanhluan.quanlynhahang.R;

public class DoAnFragment extends Fragment {

    ListView lvDoAn;
    FloatingActionButton btnAdd;
    DoAnDAO doAnDAO;
    DoAnAdapter doAnAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_do_an,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        lvDoAn = view.findViewById(R.id.lvDoAn);

        btnAdd = view.findViewById(R.id.fab);

        doAnDAO = new DoAnDAO(getContext());
        doAnDAO.open();

        doAnAdapter = new DoAnAdapter(getContext(), doAnDAO.getAll(),doAnDAO);
        lvDoAn.setAdapter(doAnAdapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doAnAdapter.showDialogAdd();
            }
        });
    }
}
