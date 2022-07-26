package fpt.thanhluan.quanlynhahang.DTO;

public class LoaiDoUong {
    public int MaLoaiDU;
    public String TenLoaiDU;

    public static final String TB_NAME = "LoaiDoUong";
    public static final String COL_MALOAIDU = "MaLoaiDU";
    public static final String COL_TENLOAIDU = "TenLoaiDU";

    public LoaiDoUong() {
    }

    public int getMaLoaiDU() {
        return MaLoaiDU;
    }

    public void setMaLoaiDU(int maLoaiDU) {
        MaLoaiDU = maLoaiDU;
    }

    public String getTenLoaiDU() {
        return TenLoaiDU;
    }

    public void setTenLoaiDU(String tenLoaiDU) {
        TenLoaiDU = tenLoaiDU;
    }
}

