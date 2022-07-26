package fpt.thanhluan.quanlynhahang.DTO;

public class KhachHang {

    public int MaKH;
    public String TenKH;
    public String SDT;

    public static final String TB_NAME = "KhachHang";
    public static final String COL_MAKH = "MaKH";
    public static final String COL_TENKH = "TenKH";
    public static final String COL_SDT = "SDT";

    public KhachHang() {
    }

    public int getMaKH() {
        return MaKH;
    }

    public void setMaKH(int maKH) {
        MaKH = maKH;
    }

    public String getTenKH() {
        return TenKH;
    }

    public void setTenKH(String tenKH) {
        TenKH = tenKH;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }
}
