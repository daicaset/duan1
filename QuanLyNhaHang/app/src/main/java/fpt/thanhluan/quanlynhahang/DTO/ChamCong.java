package fpt.thanhluan.quanlynhahang.DTO;

public class ChamCong {

    public int MaChamCong;
    public String NgayCong;
    public String CaLam;
    public String GioBatDau;
    public String GioKetThuc;
    public String MaNV;
    public String HoTen;



    public static final String TB_NAME = "ChamCong";
    public static final String COL_MACHAMCONG= "MaChamCong";
    public static final String COL_NGAYCONG= "NgayCong";
    public static final String COL_CALAM= "CaLam";
    public static final String COL_GIOBATDAU= "GioBatDau";
    public static final String COL_GIOKETTHUC= "GioKetThuc";
    public static final String COL_MANV= "MaNV";
    public static final String COL_HOTEN= "HoTen";

    public ChamCong() {
    }

    public int getMaChamCong() {
        return MaChamCong;
    }

    public void setMaChamCong(int maChamCong) {
        MaChamCong = maChamCong;
    }

    public String getNgayCong() {
        return NgayCong;
    }

    public void setNgayCong(String ngayCong) {
        NgayCong = ngayCong;
    }

    public String getCaLam() {
        return CaLam;
    }

    public void setCaLam(String caLam) {
        CaLam = caLam;
    }

    public String getGioBatDau() {
        return GioBatDau;
    }

    public void setGioBatDau(String gioBatDau) {
        GioBatDau = gioBatDau;
    }

    public String getGioKetThuc() {
        return GioKetThuc;
    }

    public void setGioKetThuc(String gioKetThuc) {
        GioKetThuc = gioKetThuc;
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String maNV) {
        MaNV = maNV;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String hoTen) {
        HoTen = hoTen;
    }
}
