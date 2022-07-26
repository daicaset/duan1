package fpt.thanhluan.quanlynhahang.DTO;

public class LoaiDoAn {
    public int MaLoaiDA;
    public String TenLoaiDA;

    public static final String TB_NAME = "LoaiDoAn";
    public static final String COL_MALOAIDA = "MaLoaiDA";
    public static final String COL_TENLOAIDA = "TenLoaiDA";

    public LoaiDoAn() {

    }


    public int getMaLoaiDA() {
        return MaLoaiDA;
    }

    public void setMaLoaiDA(int maLoaiDA) {
        MaLoaiDA = maLoaiDA;
    }

    public String getTenLoaiDA() {
        return TenLoaiDA;
    }

    public void setTenLoaiDA(String tenLoaiDA) {
        TenLoaiDA = tenLoaiDA;
    }
}
