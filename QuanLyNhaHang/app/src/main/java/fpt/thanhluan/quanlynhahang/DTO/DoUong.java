package fpt.thanhluan.quanlynhahang.DTO;

public class DoUong {

    public int MaDU;
    public String TenDU;
    public int GiaDU;
    public int SoLuongDU;
    public String SizeDU;
    public int MaLoaiDU;

    public String TenLoaiDU;

    public String getTenLoaiDU() {
        return TenLoaiDU;
    }

    public void setTenLoaiDU(String tenLoaiDU) {
        TenLoaiDU = tenLoaiDU;
    }

    public static final String TB_NAME = "DoUong";
    public static final String COL_MADU = "MaDU";
    public static final String COL_TENDU = "TenDU";
    public static final String COL_GIADU = "GiaDU";
    public static final String COL_SOLUONGDU = "SoLuongDU";
    public static final String COL_SIZEDU= "SizeDU";
    public static final String COL_MALOAIDU = "MaLoaiDU";

    public DoUong() {
    }

    public int getMaDU() {
        return MaDU;
    }

    public void setMaDU(int maDU) {
        MaDU = maDU;
    }

    public String getTenDU() {
        return TenDU;
    }

    public void setTenDU(String tenDU) {
        TenDU = tenDU;
    }

    public int getGiaDU() {
        return GiaDU;
    }

    public void setGiaDU(int giaDU) {
        GiaDU = giaDU;
    }

    public int getSoLuongDU() {
        return SoLuongDU;
    }

    public void setSoLuongDU(int soLuongDU) {
        SoLuongDU = soLuongDU;
    }

    public String getSizeDU() {
        return SizeDU;
    }

    public void setSizeDU(String sizeDU) {
        SizeDU = sizeDU;
    }

    public int getMaLoaiDU() {
        return MaLoaiDU;
    }

    public void setMaLoaiDU(int maLoaiDU) {
        MaLoaiDU = maLoaiDU;
    }
}
