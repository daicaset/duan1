package fpt.thanhluan.quanlynhahang.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;


import fpt.thanhluan.quanlynhahang.DTO.LoaiDoUong;
import fpt.thanhluan.quanlynhahang.database.DbHelper;

public class LoaiDoUongDAO {
    SQLiteDatabase db;
    DbHelper dbHelper;
    public LoaiDoUongDAO(Context context){
        dbHelper = new DbHelper(context);
    }
    public void open(){
        db = dbHelper.getWritableDatabase();
    }
    public void close(){
        dbHelper.close();
    }
    //=================them======================//
    public long insertRow(LoaiDoUong objLoaiDoUong){
        ContentValues values = new ContentValues();
        values.put(LoaiDoUong.COL_TENLOAIDU,objLoaiDoUong.getTenLoaiDU());
        long res = db.insert(LoaiDoUong.TB_NAME,null,values);
        return res;
    }
    //=================sua======================//
    public int updateRow(LoaiDoUong objLoaiDoUong){
        ContentValues values = new ContentValues();
        values.put(LoaiDoUong.COL_TENLOAIDU,objLoaiDoUong.getTenLoaiDU());

        String[] arr = new String[]{objLoaiDoUong.getMaLoaiDU()+""};
        int res = db.update(LoaiDoUong.TB_NAME,values,"MaLoaiDU = ?",arr);
        return  res;
    }
    //=================xoa======================//
    public int deleteRow(LoaiDoUong objLoaiDOUong){
        String[] arr = new String[]{objLoaiDOUong.getMaLoaiDU()+""};
        int res = db.delete(LoaiDoUong.TB_NAME,"MaLoaiDU = ?",arr);
        return res;
    }

    public ArrayList<LoaiDoUong> getAll(){
        ArrayList<LoaiDoUong>  dsLoaiDoUong = new ArrayList<>();
        String[] ds_cot = new String[]{"*"};

        Cursor cursor = db.query(LoaiDoUong.TB_NAME,ds_cot,null,null
                ,null,null,null);
        if (cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                LoaiDoUong loaiDoUong = new LoaiDoUong();
                loaiDoUong.setMaLoaiDU(cursor.getInt(0));
                loaiDoUong.setTenLoaiDU(cursor.getString(1));
                dsLoaiDoUong.add(loaiDoUong);
                cursor.moveToNext();

            }
        }
        return dsLoaiDoUong;
    }
}