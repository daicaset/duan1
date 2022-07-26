package fpt.thanhluan.quanlynhahang.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import fpt.thanhluan.quanlynhahang.DTO.Ban;
import fpt.thanhluan.quanlynhahang.DTO.HoaDon;
import fpt.thanhluan.quanlynhahang.DTO.KhachHang;
import fpt.thanhluan.quanlynhahang.DTO.NhanVien;
import fpt.thanhluan.quanlynhahang.database.DbHelper;

public class HoaDonDAO {

    SQLiteDatabase db;
    DbHelper dbHelper;

    public HoaDonDAO(Context context) {
        dbHelper = new DbHelper(context);
    }

    public void open() {
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public long insertRow(HoaDon objHoaDon) {
        ContentValues values = new ContentValues();
        values.put(HoaDon.COL_NGAY, objHoaDon.getNgay());
        values.put(HoaDon.COL_TRANGTHAI, objHoaDon.getTrangThai());
        values.put(HoaDon.COL_TONGTIEN, objHoaDon.getTongTien());
        values.put(HoaDon.COL_MAPHONG, objHoaDon.getMaPhong());
        values.put(HoaDon.COL_MABAN, objHoaDon.getMaBan());
        values.put(HoaDon.COL_MAKH, objHoaDon.getMaKH());
        values.put(HoaDon.COL_MADU, objHoaDon.getMaDU());
        values.put(HoaDon.COL_MADA, objHoaDon.getMaDA());

        long res = db.insert(HoaDon.TB_NAME, null, values);

        return res;
    }

    public int deleteRow(HoaDon objHoaDon) {
        String[] arr = new String[]{objHoaDon.getMaHD() + ""};

        int res = db.delete(HoaDon.TB_NAME, "MaHD = ?", arr);
        return res;
    }

    public int updateRow(HoaDon objHoaDon) {
        ContentValues values = new ContentValues();
        values.put(HoaDon.COL_NGAY, objHoaDon.getNgay());
        values.put(HoaDon.COL_TRANGTHAI, objHoaDon.getTrangThai());
        values.put(HoaDon.COL_TONGTIEN, objHoaDon.getTongTien());
        values.put(HoaDon.COL_MAPHONG, objHoaDon.getMaPhong());
        values.put(HoaDon.COL_MABAN, objHoaDon.getMaBan());
        values.put(HoaDon.COL_MAKH, objHoaDon.getMaKH());
        values.put(HoaDon.COL_MADU, objHoaDon.getMaDU());
        values.put(HoaDon.COL_MADA, objHoaDon.getMaDA());

        String[] arr = new String[]{objHoaDon.getMaHD() + ""};

        int res = db.update(HoaDon.TB_NAME, values, "MaHD = ?", arr);

        return res;
    }

    public ArrayList<HoaDon> getAll() {
        ArrayList<HoaDon> dsHoaDon = new ArrayList<>();

        String[] ds_hoadon = new String[]{"*"};

        String str_sql_phong = "SELECT MaHD,Ngay,TrangThai,TongTien,HoaDon.MaPhong,Phong.SoPhong" +
                " FROM HoaDon INNER JOIN Phong ON HoaDon.MaPhong = Phong.MaPhong";

        String str_sql_ban = "SELECT MaHD,Ngay,HoaDon.TrangThai,TongTien,HoaDon.MaBan,Ban.SoBan" +
                " FROM HoaDon INNER JOIN Ban ON HoaDon.MaBan = Ban.MaBan";

        String str_sql_khach_hang = "SELECT MaHD,Ngay,TrangThai,TongTien,HoaDon.MaKH,KhachHang.TenKH" +
                " FROM HoaDon INNER JOIN KhachHang ON HoaDon.MaKH = KhachHang.MaKH";

        String str_sql_do_an = "SELECT MaHD,Ngay,TrangThai,TongTien,HoaDon.MaDA,DoAn.TenDA,DoAn.GiaDA,DoAn.SoLuongDA" +
                " FROM HoaDon INNER JOIN DoAn ON HoaDon.MaDA=DoAn.MaDA";

        String str_sql_do_uong = "SELECT MaHD,Ngay,TrangThai,TongTien,HoaDon.MaDU,DoUong.TenDU,DoUong.GiaDU,DoUong.SoLuongDU,DoUong.SizeDU" +
                " FROM HoaDon INNER JOIN DoUong ON HoaDon.MaDU=DoUong.MaDU";


//        Cursor cursor = db.rawQuery(str_sql_phong, str_sql_ban,str_sql_khach_hang,str_sql_do_an,str_sql_do_uong,ds_hoadon);
        Cursor cursor = db.query(HoaDon.TB_NAME, new String[]{"*"}, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                HoaDon objHoaDon = new HoaDon();
                objHoaDon.setMaHD(cursor.getInt(0));
                objHoaDon.setNgay(cursor.getString(1));
                objHoaDon.setTrangThai(cursor.getInt(2));
                objHoaDon.setTongTien(cursor.getInt(3));
                objHoaDon.setMaPhong(cursor.getInt(4));
//                objHoaDon.setSoPhong(cursor.getString(5));
                objHoaDon.setMaBan(cursor.getInt(7));
//                objHoaDon.setSoBan(cursor.getInt(7));
                objHoaDon.setMaKH(cursor.getInt(8));
//                objHoaDon.setTenKH(cursor.getString(9));
                objHoaDon.setMaDU(cursor.getInt(5));
//                objHoaDon.setTenDU(cursor.getString(11));
//                objHoaDon.setGiaDU(cursor.getInt(12));
//                objHoaDon.setSoLuongDU(cursor.getInt(13));
//                objHoaDon.setSizeDU(cursor.getString(14));
                objHoaDon.setMaDA(cursor.getInt(6));
//                objHoaDon.setTenDA(cursor.getString(16));
//                objHoaDon.setGiaDA(cursor.getInt(17));
//                objHoaDon.setSoLuongDA(cursor.getInt(18));
                dsHoaDon.add(objHoaDon);
                cursor.moveToNext();
            }
        }

        return dsHoaDon;
    }


    public HoaDon getOneWithKhachHang(int id) {
        String[] args = new String[]{id + ""};

        HoaDon objHoaDon = new HoaDon();

        String str_sql_phong = "SELECT MaHD,Ngay,TrangThai,TongTien,HoaDon.MaPhong,Phong.SoPhong" +
                " FROM HoaDon INNER JOIN Phong ON HoaDon.MaPhong = Phong.MaPhong";


        Cursor cursor = db.rawQuery(str_sql_phong, args);

        if (cursor.moveToFirst()) {
            objHoaDon.setMaHD(cursor.getInt(0));
            objHoaDon.setNgay(cursor.getString(1));
            objHoaDon.setTrangThai(cursor.getInt(2));
            objHoaDon.setTongTien(cursor.getInt(3));
            objHoaDon.setMaPhong(cursor.getInt(4));
            objHoaDon.setMaDU(cursor.getInt(5));
            objHoaDon.setMaDA(cursor.getInt(6));
            objHoaDon.setMaBan(cursor.getInt(7));
            objHoaDon.setMaKH(cursor.getInt(8));
        }
        return objHoaDon;
    }


    public HoaDon getOneWithPhong(int id) {
        String[] args = new String[]{id + ""};

        HoaDon objHoaDon = new HoaDon();

        String str_sql = "SELECT MaHD,Ngay,TrangThai,TongTien,MaPhong,MaDU,MaDA,MaBan,MaKH,HoaDon.MaPhong, Phong.SoPhong" +
                " FROM HoaDon INNER JOIN Phong ON HoaDon.MaPhong=Phong.MaPhong";

        Cursor cursor = db.rawQuery(str_sql, args);

        if (cursor.moveToFirst()) {
            objHoaDon.setMaHD(cursor.getInt(0));
            objHoaDon.setNgay(cursor.getString(1));
            objHoaDon.setTrangThai(cursor.getInt(2));
            objHoaDon.setTongTien(cursor.getInt(3));
            objHoaDon.setMaPhong(cursor.getInt(4));
            objHoaDon.setMaDU(cursor.getInt(5));
            objHoaDon.setMaDA(cursor.getInt(6));
            objHoaDon.setMaBan(cursor.getInt(7));
            objHoaDon.setMaKH(cursor.getInt(8));
        }
        return objHoaDon;
    }

    public HoaDon getOneWithBan(int id) {
        String[] args = new String[]{id + ""};

        HoaDon objHoaDon = new HoaDon();

        String str_sql_ban = "SELECT MaHD,Ngay,TrangThai,TongTien,HoaDon.MaBan,Ban.SoBan" +
                " FROM HoaDon INNER JOIN Ban ON HoaDon.MaBan = Ban.MaBan";

        Cursor cursor = db.rawQuery(str_sql_ban, args);

        if (cursor.moveToFirst()) {
            objHoaDon.setMaHD(cursor.getInt(0));
            objHoaDon.setNgay(cursor.getString(1));
            objHoaDon.setTrangThai(cursor.getInt(2));
            objHoaDon.setTongTien(cursor.getInt(3));
            objHoaDon.setMaPhong(cursor.getInt(4));
            objHoaDon.setMaDU(cursor.getInt(5));
            objHoaDon.setMaDA(cursor.getInt(6));
            objHoaDon.setMaBan(cursor.getInt(7));
            objHoaDon.setMaKH(cursor.getInt(8));
        }
        return objHoaDon;
    }

    public HoaDon getOneWithDoAn(int id) {
        String[] args = new String[]{id + ""};

        HoaDon objHoaDon = new HoaDon();

        String str_sql = "SELECT MaHD,Ngay,TrangThai,TongTien,HoaDon.MaDA,DoAn.TenDA,DoAn.GiaDA,DoAn.SoLuongDA" +
                " FROM HoaDon INNER JOIN DoAn ON HoaDon.MaDA=DoAn.MaDA";


        Cursor cursor = db.rawQuery(str_sql, args);

        if (cursor.moveToFirst()) {
            objHoaDon.setMaHD(cursor.getInt(0));
            objHoaDon.setNgay(cursor.getString(1));
            objHoaDon.setTrangThai(cursor.getInt(2));
            objHoaDon.setTongTien(cursor.getInt(3));
            objHoaDon.setMaPhong(cursor.getInt(4));
            objHoaDon.setMaDU(cursor.getInt(5));
            objHoaDon.setMaDA(cursor.getInt(6));
            objHoaDon.setMaBan(cursor.getInt(7));
            objHoaDon.setMaKH(cursor.getInt(8));
        }
        return objHoaDon;
    }

    public HoaDon getOneWithDoUong(int id) {
        String[] args = new String[]{id + ""};

        HoaDon objHoaDon = new HoaDon();

        String str_sql = "SELECT MaHD,Ngay,TrangThai,TongTien,HoaDon.MaDU,DoUong.TenDU,DoUong.GiaDU,DoUong.SoLuongDU,DoUong.SizeDU" +
                " FROM HoaDon INNER JOIN DoUong ON HoaDon.MaDU=DoUong.MaDU";


        Cursor cursor = db.rawQuery(str_sql, args);

        if (cursor.moveToFirst()) {
            objHoaDon.setMaHD(cursor.getInt(0));
            objHoaDon.setNgay(cursor.getString(1));
            objHoaDon.setTrangThai(cursor.getInt(2));
            objHoaDon.setTongTien(cursor.getInt(3));
            objHoaDon.setMaPhong(cursor.getInt(4));
            objHoaDon.setMaDU(cursor.getInt(5));
            objHoaDon.setMaDA(cursor.getInt(6));
            objHoaDon.setMaBan(cursor.getInt(7));
            objHoaDon.setMaKH(cursor.getInt(8));
        }
        return objHoaDon;
    }


}
