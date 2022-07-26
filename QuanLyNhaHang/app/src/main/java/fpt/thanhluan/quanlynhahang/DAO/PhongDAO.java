package fpt.thanhluan.quanlynhahang.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import fpt.thanhluan.quanlynhahang.DTO.Phong;
import fpt.thanhluan.quanlynhahang.database.DbHelper;

public class PhongDAO {
    DbHelper dbHelper;
    SQLiteDatabase db;

    public PhongDAO(Context context) {
        dbHelper = new DbHelper(context);
    }

    public void open() {
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public long insertRow(Phong objPhong) {
        ContentValues values = new ContentValues();
        values.put(Phong.COL_SOPHONG, objPhong.getSoPhong());

        long res = db.insert(Phong.TB_NAME, null, values);
        return res;
    }

    public int updateRow(Phong objPhong) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Phong.COL_SOPHONG, objPhong.getSoPhong());
        String[] arr = new String[]{objPhong.getMaPhong() + ""};

        int res = db.update(Phong.TB_NAME, contentValues, "MaPhong = ?", arr);
        return res;
    }

    public int deleteRow(Phong objPhong) {
        String[] arr = new String[]{objPhong.getMaPhong() + ""};

        int res = db.delete(Phong.TB_NAME, "MaPhong = ?", arr);
        return res;
    }

    public ArrayList<Phong> getAll() {
        ArrayList<Phong> dsPhong = new ArrayList<>();

        String[] ds_cot = new String[]{"*"};

        Cursor cursor = db.query(Phong.TB_NAME, ds_cot, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                Phong objPhong = new Phong();
                objPhong.setMaPhong(cursor.getInt(0));
                objPhong.setSoPhong(cursor.getString(1));

                dsPhong.add(objPhong);
                cursor.moveToNext();
            }

        }

        return dsPhong;
    }
}