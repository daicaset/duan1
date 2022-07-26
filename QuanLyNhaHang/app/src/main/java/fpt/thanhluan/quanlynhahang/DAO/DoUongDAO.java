package fpt.thanhluan.quanlynhahang.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import fpt.thanhluan.quanlynhahang.DTO.DoAn;
import fpt.thanhluan.quanlynhahang.DTO.DoUong;
import fpt.thanhluan.quanlynhahang.database.DbHelper;

public class DoUongDAO {
    SQLiteDatabase db;
    DbHelper dbHelper;
    public DoUongDAO(Context context){
        dbHelper = new DbHelper(context);
    }
    public void open(){
        db = dbHelper.getWritableDatabase();
    }
    public void close(){
        dbHelper.close();
    }
    //=================them======================//
    public long insertRow(DoUong objDoUong){
        ContentValues values = new ContentValues();
        values.put(DoUong.COL_TENDU,objDoUong.getTenDU());
        values.put(DoUong.COL_GIADU,objDoUong.getGiaDU());
        values.put(DoUong.COL_SOLUONGDU,objDoUong.getSoLuongDU());
        values.put(DoUong.COL_SIZEDU,objDoUong.getSizeDU());
        values.put(DoUong.COL_MALOAIDU,objDoUong.getMaLoaiDU());
        long res = db.insert(DoUong.TB_NAME,null,values);
        return res;
    }
    //=================sua======================//
    public int updateRow(DoUong objDoUong){
        ContentValues values = new ContentValues();
        values.put(DoUong.COL_TENDU,objDoUong.getTenDU());
        values.put(DoUong.COL_GIADU,objDoUong.getGiaDU());
        values.put(DoUong.COL_SOLUONGDU,objDoUong.getSoLuongDU());
        values.put(DoUong.COL_SIZEDU,objDoUong.getSizeDU());
        values.put(DoUong.COL_MALOAIDU,objDoUong.getMaLoaiDU());
        String[] arr = new String[]{objDoUong.getMaDU()+""};
        int res = db.update(DoUong.TB_NAME,values,"MaDU = ?",arr);
        return  res;
    }
    //=================xoa======================//
    public int deleteRow(DoUong objDoUong){
        String[] arr = new String[]{objDoUong.getMaDU()+""};
        int res = db.delete(DoUong.TB_NAME,"MaDU = ?",arr);
        return res;
    }

    public ArrayList<DoUong> getAll(){
        ArrayList<DoUong>  dsDoUong = new ArrayList<>();
        String str_sql = "SELECT MaDU,TenDU,GiaDU,SoLuongDU,SizeDU,DoUong.MaLoaiDU,LoaiDoUong.TenLoaiDU" +
                " FROM DoUong INNER JOIN LoaiDoUong ON DoUong.MaLoaiDU = LoaiDoUong.MaLoaiDU";

        Cursor cursor = db.rawQuery(str_sql, null);
        if (cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                DoUong objDoUong = new DoUong();
                objDoUong.setMaDU(cursor.getInt(0));
                objDoUong.setTenDU(cursor.getString(1));
                objDoUong.setGiaDU(cursor.getInt(2));
                objDoUong.setSoLuongDU(cursor.getInt(3));
                objDoUong.setSizeDU(cursor.getString(4));
                objDoUong.setMaLoaiDU(cursor.getInt(5));
                objDoUong.setTenLoaiDU(cursor.getString(6));

                dsDoUong.add(objDoUong);
                cursor.moveToNext();

            }
        }
        return dsDoUong;
    }

}