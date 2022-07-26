package fpt.thanhluan.quanlynhahang.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import fpt.thanhluan.quanlynhahang.DTO.NhanVien;
import fpt.thanhluan.quanlynhahang.database.DbHelper;

public class NhanVienDAO {
    SQLiteDatabase db;
    DbHelper dbHelper;

    public NhanVienDAO(Context context) {

        dbHelper = new DbHelper(context);
    }

    public void open(){
        db = dbHelper.getWritableDatabase();
    }

    public void close(){
        dbHelper.close();
    }

    public long insertRow(NhanVien objNhanVien){
        ContentValues values = new ContentValues();
        values.put(NhanVien.COL_MANV,objNhanVien.getMaNV());
        values.put(NhanVien.COL_HOTEN,objNhanVien.getHoTen());
        values.put(NhanVien.COL_MATKHAU,objNhanVien.getMatKhau());

        long res = db.insert(NhanVien.TB_NAME,null,values);

        return res;
    }

    public int deleteRow(NhanVien objNhanVien){
        String [] arr = new String[]{objNhanVien.getMaNV()};

        int res = db.delete(NhanVien.TB_NAME,"MaNV = ?",arr);
        return res;
    }

    public int updateRow(NhanVien objNhanVien){
        ContentValues values = new ContentValues();
        values.put(NhanVien.COL_MANV,objNhanVien.getMaNV());
        values.put(NhanVien.COL_HOTEN,objNhanVien.getHoTen());
        values.put(NhanVien.COL_MATKHAU,objNhanVien.getMatKhau());

        String [] arr = new String[]{objNhanVien.getMaNV()};

        int res = db.update(NhanVien.TB_NAME,values,"MaNV = ?",arr);

        return res;
    }

//    public ArrayList<NhanVien> getAll(){
//        ArrayList<NhanVien> dsNhanVien = new ArrayList<>();
//
//        String[] ds_nhanvien = new String[]{"*"};
//
//        Cursor cursor = db.query(NhanVien.TB_NAME,ds_nhanvien,null,null,null,null,null);
//
//        if(cursor.moveToFirst()){
//            while (!cursor.isAfterLast()){
//                NhanVien objNhanVien = new NhanVien();
//
//                objNhanVien.setMaNV(cursor.getString(0));
//                objNhanVien.setHoTen(cursor.getString(1));
//                objNhanVien.setMatKhau(cursor.getString(2));
//
//                dsNhanVien.add(objNhanVien);
//                cursor.moveToNext();
//            }
//        }
//        return dsNhanVien;
//    }

    //get tất cả data
    public List<NhanVien> getAll(){
        String sql = "SELECT * FROM NhanVien";

        return getData(sql);
    }

    //get data theo id

    public NhanVien getID(String id){
        String sql = "SELECT * FROM NhanVien WHERE MaNV=?";
        List<NhanVien> list = getData(sql,id);
        return list.get(0);
    }




    public List<NhanVien> dataByID(String id){
        String sql = "SELECT * FROM NhanVien WHERE MaNV= '"+ id + "'";
        List<NhanVien> list = new ArrayList<>();

        Cursor cursor = db.rawQuery(sql,null);

        if(cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                NhanVien objNV = new NhanVien();
                objNV.setMaNV(cursor.getString(0));
                objNV.setHoTen(cursor.getString(1));
                objNV.setMatKhau(cursor.getString(2));
                list.add(objNV);
                cursor.moveToNext();
            }
        }
        return list;
    }




    private ArrayList<NhanVien> getData(String sql,String...selectionArgs){
        ArrayList<NhanVien> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql,selectionArgs);
        if(cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                NhanVien objNV = new NhanVien();
                objNV.setMaNV(cursor.getString(0));
                objNV.setHoTen(cursor.getString(1));
                objNV.setMatKhau(cursor.getString(2));
                list.add(objNV);
                cursor.moveToNext();
            }
        }
        return list;
    }

    //check login

    public int checkLogin(String id , String password){
        String sql = "SELECT * FROM NhanVien WHERE MaNV=? AND MatKhau=?";
        List<NhanVien> list = getData(sql,id,password);
        if(list.size()==0){
            return -1;
        }
        return 1;
    }
}
