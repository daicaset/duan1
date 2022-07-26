package fpt.thanhluan.quanlynhahang.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import fpt.thanhluan.quanlynhahang.DTO.Ban;
import fpt.thanhluan.quanlynhahang.database.DbHelper;

public class BanDAO {
    SQLiteDatabase db;
    DbHelper dbHelper;
    public BanDAO(Context context){
        dbHelper = new DbHelper(context);
    }
    public void open(){
        db = dbHelper.getWritableDatabase();
    }
    public void close(){
        dbHelper.close();
    }
    //=================them======================//
    public long insertRow(Ban objBan){
        ContentValues values = new ContentValues();
        values.put(Ban.COL_SOBAN,objBan.getSoBan());
        values.put(Ban.COL_TRANGTHAI,objBan.getTrangThai());
        values.put(Ban.COL_MAPHONG,objBan.getMaPhong());
        long res = db.insert(Ban.TB_NAME,null,values);
        return res;
    }
    //=================sua======================//
    public int updateRow(Ban objBan){
        ContentValues values = new ContentValues();
        values.put(Ban.COL_SOBAN,objBan.getSoBan());
        values.put(Ban.COL_TRANGTHAI,objBan.getTrangThai());
        values.put(Ban.COL_MAPHONG,objBan.getMaPhong());
        String[] arr = new String[]{objBan.getMaBan()+""};
        int res = db.update(Ban.TB_NAME,values,"MaBan = ?",arr);
        return  res;
    }
    //=================xoa======================//
    public int deleteRow(Ban objBan){
        String[] arr = new String[]{objBan.getMaBan()+""};
        int res = db.delete(Ban.TB_NAME,"MaBan = ?",arr);
        return res;
    }

    public ArrayList<Ban> getAll(){
        ArrayList<Ban>  dsBan = new ArrayList<>();

        String str_sql = "SELECT MaBan,SoBan,TrangThai,Ban.MaPhong,Phong.SoPhong" +
                " FROM Ban INNER JOIN Phong ON Ban.MaPhong = Phong.MaPhong";

        Cursor cursor = db.rawQuery(str_sql,null);

        if (cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                Ban objBan = new Ban();
                objBan.setMaBan(cursor.getInt(0));
                objBan.setSoBan(cursor.getInt(1));
                objBan.setTrangThai(cursor.getInt(2));
                objBan.setMaPhong(cursor.getInt(3));
                objBan.setSoPhong(cursor.getString(4));
                dsBan.add(objBan);
                cursor.moveToNext();
            }
        }
        return dsBan;
    }


}