package fpt.thanhluan.quanlynhahang.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import fpt.thanhluan.quanlynhahang.DTO.KhachHang;
import fpt.thanhluan.quanlynhahang.database.DbHelper;

public class KhachHangDAO {
    SQLiteDatabase db;
    DbHelper dbHelper;

    public KhachHangDAO(Context context) {
        dbHelper = new DbHelper(context);
    }

    public void open(){
        db = dbHelper.getWritableDatabase();
    }

    public void close(){
        dbHelper.close();
    }

    public long insertRow(KhachHang objKhachHang){
        ContentValues values = new ContentValues();
        values.put(KhachHang.COL_TENKH,objKhachHang.getTenKH());
        values.put(KhachHang.COL_SDT,objKhachHang.getSDT());

        long res = db.insert(KhachHang.TB_NAME,null,values);

        return res;
    }

    public int deleteRow(KhachHang objKhachHang){
        String [] arr = new String[]{objKhachHang.getMaKH()+""};

        int res = db.delete(KhachHang.TB_NAME,"MaKH = ?",arr);
        return res;
    }

    public int updateRow(KhachHang objKhachHang){
        ContentValues values = new ContentValues();
        values.put(KhachHang.COL_TENKH,objKhachHang.getTenKH());
        values.put(KhachHang.COL_SDT,objKhachHang.getSDT());

        String [] arr = new String[]{objKhachHang.getMaKH()+""};

        int res = db.update(KhachHang.TB_NAME,values,"MaKH = ?",arr);

        return res;
    }

    public ArrayList<KhachHang> getAll(){
        ArrayList<KhachHang> dsKhachHang = new ArrayList<>();

        String[] ds_khachhang = new String[]{"*"};

        Cursor cursor = db.query(KhachHang.TB_NAME,ds_khachhang,null,null,null,null,null);

        if(cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                KhachHang objKhachHang = new KhachHang();

                objKhachHang.setMaKH(cursor.getInt(0));
                objKhachHang.setTenKH(cursor.getString(1));
                objKhachHang.setSDT(cursor.getString(2));

                dsKhachHang.add(objKhachHang);
                cursor.moveToNext();
            }
        }

        return dsKhachHang;
    }
}
