package fpt.thanhluan.quanlynhahang.DTO;

public class Ban {

    public int MaBan;
    public int SoBan;
    public int TrangThai;
    public int MaPhong;
    public String SoPhong;

    public static final String TB_NAME = "Ban";
    public static final String COL_MABAN = "MaBan";
    public static final String COL_SOBAN = "SoBan";
    public static final String COL_TRANGTHAI = "TrangThai";
    public static final String COL_MAPHONG = "MaPhong";

    public Ban() {
    }

    public int getMaBan() {
        return MaBan;
    }

    public void setMaBan(int maBan) {
        MaBan = maBan;
    }

    public int getSoBan() {
        return SoBan;
    }

    public void setSoBan(int soBan) {
        SoBan = soBan;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int trangThai) {
        TrangThai = trangThai;
    }

    public int getMaPhong() { return MaPhong; }

    public void setMaPhong(int maPhong) { MaPhong = maPhong;}

    public String getSoPhong() {
        return SoPhong;
    }

    public void setSoPhong(String soPhong) {
        SoPhong = soPhong;
    }
}
