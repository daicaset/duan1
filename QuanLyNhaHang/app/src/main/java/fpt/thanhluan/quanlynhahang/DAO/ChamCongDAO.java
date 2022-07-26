package fpt.thanhluan.quanlynhahang.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import fpt.thanhluan.quanlynhahang.DTO.Ban;
import fpt.thanhluan.quanlynhahang.DTO.ChamCong;
import fpt.thanhluan.quanlynhahang.DTO.LoaiDoAn;
import fpt.thanhluan.quanlynhahang.database.DbHelper;

public class ChamCongDAO {
    SQLiteDatabase database;
    DbHelper dbHelper;

    public ChamCongDAO(Context context) {

        dbHelper = new DbHelper(context);
    }

    public void open(){
        database = dbHelper.getWritableDatabase();
    }
    public void close(){
        dbHelper.close();
    }


    //=================them======================//
    public long insertRow(ChamCong objChamCong){
        ContentValues values = new ContentValues();
        values.put(ChamCong.COL_NGAYCONG,objChamCong.getNgayCong());
        values.put(ChamCong.COL_CALAM,objChamCong.getCaLam());
        values.put(ChamCong.COL_GIOBATDAU,objChamCong.getGioBatDau());
        values.put(ChamCong.COL_GIOKETTHUC,objChamCong.getGioKetThuc());
        values.put(ChamCong.COL_MANV,objChamCong.getMaNV());

        long res = database.insert(ChamCong.TB_NAME,null,values);
        return res;
    }

    public ArrayList<ChamCong> getAll(){
        ArrayList<ChamCong> dsChamCong = new ArrayList<>();

        String str_sql = "SELECT MaChamCong,NgayCong,CaLam,GioBatDau,GioKetThuc,ChamCong.MaNV,NhanVien.HoTen" +
                " FROM ChamCong INNER JOIN NhanVien ON ChamCong.MaNV = NhanVien.MaNV";

        Cursor cursor = database.rawQuery(str_sql,null);

        if (cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                ChamCong objChamCong = new ChamCong();
                objChamCong.setMaChamCong(cursor.getInt(0));
                objChamCong.setNgayCong(cursor.getString(1));
                objChamCong.setCaLam(cursor.getString(2));
                objChamCong.setGioBatDau(cursor.getString(3));
                objChamCong.setGioKetThuc(cursor.getString(4));
                objChamCong.setMaNV(cursor.getString(5));
                objChamCong.setHoTen(cursor.getString(6));
                dsChamCong.add(objChamCong);
                cursor.moveToNext();
            }
        }
        return dsChamCong;
    }
}
