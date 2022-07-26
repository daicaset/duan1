package fpt.thanhluan.quanlynhahang.DTO;

public class DoAn {

    public int MaDA;
    public String TenDA;
    public int GiaDA;
    public int SoLuongDA;
    public int MaLoaiDA;
    public String TenLoaiDA;

    public static final String TB_NAME = "DoAn";
    public static final String COL_MADA = "MaDA";
    public static final String COL_TENDA = "TenDA";
    public static final String COL_GIADA = "GiaDA";
    public static final String COL_SOLUONGDA = "SoLuongDA";
    public static final String COL_MALOAIDA = "MaLoaiDA";


    public DoAn() {

    }


    public int getMaDA() {
        return MaDA;
    }

    public void setMaDA(int maDA) {
        MaDA = maDA;
    }

    public String getTenDA() {
        return TenDA;
    }

    public void setTenDA(String tenDA) {
        TenDA = tenDA;
    }

    public String getTenLoaiDA() {
        return TenLoaiDA;
    }

    public void setTenLoaiDA(String tenLoaiDA) {
        TenLoaiDA = tenLoaiDA;
    }

    public int getGiaDA() {
        return GiaDA;
    }

    public void setGiaDA(int giaDA) {
        GiaDA = giaDA;
    }

    public int getSoLuongDA() {
        return SoLuongDA;
    }

    public void setSoLuongDA(int soLuongDA) {
        SoLuongDA = soLuongDA;
    }

    public int getMaLoaiDA() {
        return MaLoaiDA;
    }

    public void setMaLoaiDA(int maLoaiDA) {
        MaLoaiDA = maLoaiDA;
    }


}
