package fpt.thanhluan.quanlynhahang.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import fpt.thanhluan.quanlynhahang.DTO.LoaiDoAn;
import fpt.thanhluan.quanlynhahang.database.DbHelper;

public class LoaiDoAnDAO {

    DbHelper dbHelper;
    SQLiteDatabase db;

    public LoaiDoAnDAO(Context context) {
        dbHelper = new DbHelper(context);
    }

    public void open() {
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public long insertRow(LoaiDoAn objLoaiDoAn) {
        ContentValues values = new ContentValues();
        values.put(LoaiDoAn.COL_TENLOAIDA, objLoaiDoAn.getTenLoaiDA());

        long res = db.insert(LoaiDoAn.TB_NAME, null, values);
        return res;
    }

    public int updateRow(LoaiDoAn objLoaiDoAn) {
        ContentValues values = new ContentValues();
        values.put(LoaiDoAn.COL_TENLOAIDA, objLoaiDoAn.getTenLoaiDA());
        String[] arr = new String[]{objLoaiDoAn.getMaLoaiDA() + ""};

        int res = db.update(LoaiDoAn.TB_NAME, values, "MaLoaiDA = ?", arr);
        return res;
    }

    public int deleteRow(LoaiDoAn objLoaiDoAn) {
        String[] arr = new String[]{objLoaiDoAn.getMaLoaiDA() + ""};

        int res = db.delete(LoaiDoAn.TB_NAME, "MaLoaiDA = ?", arr);
        return res;
    }

    public ArrayList<LoaiDoAn> getAll() {
        ArrayList<LoaiDoAn> dsDoAn = new ArrayList<>();

        String[] ds_cot = new String[]{"*"};

        Cursor cursor = db.query(LoaiDoAn.TB_NAME, ds_cot, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                LoaiDoAn objLoaiDoAn = new LoaiDoAn();

                objLoaiDoAn.setMaLoaiDA(cursor.getInt(0));
                objLoaiDoAn.setTenLoaiDA(cursor.getString(1));
                dsDoAn.add(objLoaiDoAn);
                cursor.moveToNext();
            }

        }

        return dsDoAn;
    }

}