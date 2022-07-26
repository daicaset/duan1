package fpt.thanhluan.quanlynhahang.DTO;

public class NhanVien {
    public String MaNV;
    public String HoTen;
    public String MatKhau;

    public static final String TB_NAME = "NhanVien";
    public static final String COL_MANV = "MaNV";
    public static final String COL_HOTEN = "HoTen";
    public static final String COL_MATKHAU = "MatKhau";

    public NhanVien() {
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

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String matKhau) {
        MatKhau = matKhau;
    }
}
