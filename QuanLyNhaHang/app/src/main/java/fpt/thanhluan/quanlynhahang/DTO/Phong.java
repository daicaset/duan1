package fpt.thanhluan.quanlynhahang.DTO;

public class Phong {

  public int MaPhong;
  public String SoPhong;

    public static final String TB_NAME = "Phong";
    public static final String COL_MAPHONG = "MaPhong";
    public static final String COL_SOPHONG = "SoPhong";

    public Phong() {
    }

    public int getMaPhong() {
        return MaPhong;
    }

    public void setMaPhong(int maPhong) {
        MaPhong = maPhong;
    }

    public String getSoPhong() {
        return SoPhong;
    }

    public void setSoPhong(String soPhong) {
        SoPhong = soPhong;
    }
}
