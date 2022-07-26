package fpt.thanhluan.quanlynhahang.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    public static final String DB_NAME  = "QuanLyCuaHang";
    public static final int DB_VERSION = 1;

    public DbHelper(Context context) { super(context, DB_NAME, null, DB_VERSION); }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //Tạo bảng Loại đồ uống
        String createTableLoaiDoUong =
                "CREATE TABLE  LoaiDoUong  (\n" +
                        "\t MaLoaiDU \tINTEGER NOT NULL,\n" +
                        "\t TenLoaiDU \tTEXT NOT NULL UNIQUE,\n" +
                        "\tPRIMARY KEY( MaLoaiDU  AUTOINCREMENT)\n" +
                        ");";
        db.execSQL(createTableLoaiDoUong);

        //Tạo bảng đồ uống
        String createTableDoUong =
               "CREATE TABLE  DoUong  (\n" +
                       "\t MaDU \tINTEGER NOT NULL,\n" +
                       "\t TenDU \tTEXT NOT NULL UNIQUE,\n" +
                       "\t GiaDU \tINTEGER NOT NULL,\n" +
                       "\t SoLuongDU \tINTEGER NOT NULL,\n" +
                       "\t SizeDU \tTEXT NOT NULL,\n" +
                       "\t MaLoaiDU \tINTEGER REFERENCES LoaiDoUong(MaLoaiDU),\n" +
                       "\tPRIMARY KEY( MaDU  AUTOINCREMENT)\n" +
                       ");";
        db.execSQL(createTableDoUong);

        //Tạo bảng Phòng
        String createTablePhong =
                "CREATE TABLE  Phong  (\n" +
                        "\t MaPhong \tINTEGER NOT NULL,\n" +
                        "\t SoPhong \tTEXT NOT NULL UNIQUE,\n" +
                        "\tPRIMARY KEY( MaPhong  AUTOINCREMENT)\n" +
                        ");";
        db.execSQL(createTablePhong);

        //Tạo bảng Loại đồ ăn
        String createTableLoaiDoAn =
                "CREATE TABLE  LoaiDoAn  (\n" +
                        "\t MaLoaiDA \tINTEGER NOT NULL,\n" +
                        "\t TenLoaiDA \tTEXT NOT NULL UNIQUE,\n" +
                        "\t MaPhong \tINTEGER REFERENCES Phong(MaPhong),\n" +
                        "\tPRIMARY KEY( MaLoaiDA  AUTOINCREMENT)\n" +
                        ");";
        db.execSQL(createTableLoaiDoAn);

        //Tạo bảng đồ ăn
        String createTableDoAn =
                "CREATE TABLE  DoAn  (\n" +
                        "\t MaDA \tINTEGER NOT NULL,\n" +
                        "\t TenDA \tTEXT NOT NULL UNIQUE,\n" +
                        "\t GiaDA \tINTEGER NOT NULL,\n" +
                        "\t SoLuongDA \tINTEGER NOT NULL,\n" +
                        "\t MaLoaiDA \tINTEGER REFERENCES LoaiDoAn(MaLoaiDA),\n" +
                        "\tPRIMARY KEY( MaDA  AUTOINCREMENT)\n" +
                        ");";
        db.execSQL(createTableDoAn);

        //Tạo bảng bàn
        String createTableBan =

                "CREATE TABLE  Ban  (\n" +
                        "\t MaBan \tINTEGER NOT NULL,\n" +
                        "\t SoBan \tINTEGER NOT NULL,\n" +
                        "\t TrangThai \tINTEGER NOT NULL,\n" +
                        "\t MaPhong \tINTEGER REFERENCES Phong(MaPhong),\n" +
                        "\tPRIMARY KEY( MaBan  AUTOINCREMENT)\n" +
                        ");";
        db.execSQL(createTableBan);

        //Tạo bảng nhân viên
        String createTableNhanVien =

                "CREATE TABLE  NhanVien  (\n" +
                        "\t MaNV \tTEXT NOT NULL UNIQUE ,\n" +
                        "\t HoTen \tTEXT NOT NULL,\n" +
                        "\t MatKhau \tTEXT NOT NULL,\n" +
                        "\tPRIMARY KEY( MaNV )\n" +
                        ");";
        db.execSQL(createTableNhanVien);

        //Tạo bảng chấm công
        String createTableChamCong =

                "CREATE TABLE  ChamCong  (\n" +
                        "\t MaChamCong \tINTEGER NOT NULL UNIQUE ,\n" +
                        "\t NgayCong \tTEXT NOT NULL,\n" +
                        "\t CaLam \tTEXT NOT NULL,\n" +
                        "\t GioBatDau \tTEXT NOT NULL,\n" +
                        "\t GioKetThuc \tTEXT NOT NULL,\n" +
                        "\t MaNV \tTEXT REFERENCES NhanVien(MaNV),\n" +
                        "\tPRIMARY KEY( maChamCong  AUTOINCREMENT)\n" +
                        ");";
        db.execSQL(createTableChamCong);

        String setAdmin = "INSERT INTO NhanVien VALUES('1 ','admin','admin')";
        db.execSQL(setAdmin);

        //Tạo bảng khách hàng
        String createTableKhachHang =

                "CREATE TABLE  KhachHang  (\n" +
                        "\t MaKH \tINTEGER NOT NULL,\n" +
                        "\t TenKH \tTEXT NOT NULL,\n" +
                        "\t SDT \tTEXT NOT NULL UNIQUE,\n" +
                        "\tPRIMARY KEY( MaKH  AUTOINCREMENT)\n" +
                        ");";
        db.execSQL(createTableKhachHang);

        //Tạo bảng hóa đơn
        String createTableHoaDon =
                "CREATE TABLE  HoaDon  (\n" +
                        "\t MaHD \tINTEGER NOT NULL,\n" +
                        "\t Ngay \tDATE NOT NULL,\n" +
                        "\t TongTien \tINTEGER NOT NULL,\n" +
                        "\t MaPhong \tINTEGER REFERENCES Phong(MaPhong),\n" +
                        "\t MaDU \tINTEGER REFERENCES DoUong(MaDU),\n" +
                        "\t MaDA \tINTEGER REFERENCES DoAn(MaDA),\n" +
                        "\t MaBan \tINTEGER REFERENCES Ban(MaBan),\n" +
                        "\t MaKH \tINTEGER REFERENCES KhachHang(MaKH),\n" +
                        "\tPRIMARY KEY( MaHD  AUTOINCREMENT)\n" +
                        ");";
        db.execSQL(createTableHoaDon);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String dropTableLoaiDoUong = "drop table if exists LoaiDoUong";
        db.execSQL(dropTableLoaiDoUong);
        String dropTableDoUong = "drop table if exists DoUong";
        db.execSQL(dropTableDoUong);
        String dropTablePhong = "drop table if exists Phong";
        db.execSQL(dropTablePhong);
        String dropTableLoaiDoAn= "drop table if exists LoaiDoAn";
        db.execSQL(dropTableLoaiDoAn);
        String dropTableDoAn= "drop table if exists DoAn";
        db.execSQL(dropTableDoAn);
        String dropTableBan = "drop table if exists Ban";
        db.execSQL(dropTableBan);
        String dropTableNhanVien = "drop table if exists NhanVien";
        db.execSQL(dropTableNhanVien);
        String dropTableLoaiKhachHang= "drop table if exists KhachHang";
        db.execSQL(dropTableLoaiKhachHang);
        String dropTableHoaDon= "drop table if exists HoaDon";
        db.execSQL(dropTableHoaDon);

        onCreate(db);

    }
}
