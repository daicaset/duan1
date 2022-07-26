package fpt.thanhluan.quanlynhahang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import fpt.thanhluan.quanlynhahang.Fragment.AddUserFragment;
import fpt.thanhluan.quanlynhahang.Fragment.BanFragment;
import fpt.thanhluan.quanlynhahang.Fragment.ChangePassFragment;
import fpt.thanhluan.quanlynhahang.Fragment.DanhSachChamCongFragment;
import fpt.thanhluan.quanlynhahang.Fragment.DanhSachNhanVienFragment;
import fpt.thanhluan.quanlynhahang.Fragment.DoAnFragment;
import fpt.thanhluan.quanlynhahang.Fragment.DoUongFragment;
import fpt.thanhluan.quanlynhahang.Fragment.DoanhThuFragment;
import fpt.thanhluan.quanlynhahang.Fragment.HoaDonFragment;
import fpt.thanhluan.quanlynhahang.Fragment.KhachHangFragment;
import fpt.thanhluan.quanlynhahang.Fragment.LoaiDoAnFragment;
import fpt.thanhluan.quanlynhahang.Fragment.LoaiDoUongFragment;
import fpt.thanhluan.quanlynhahang.Fragment.PhongFragment;
import fpt.thanhluan.quanlynhahang.Fragment.ThucDonFragment;
import fpt.thanhluan.quanlynhahang.Fragment.TopFragment;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawer;
    Toolbar toolbar;
    View mHeaderView;
    TextView tvUser;

    FragmentManager manager;
    AddUserFragment addUserFragment;
    ChangePassFragment changePassFragment;
    HoaDonFragment hoaDonFragment;
    PhongFragment phongFragment;
    BanFragment banFragment;
    LoaiDoUongFragment loaiDoUongFragment;
    DoUongFragment doUongFragment;
    LoaiDoAnFragment loaiDoAnFragment;
    DoAnFragment doAnFragment;
    KhachHangFragment khachHangFragment;
    TopFragment topFragment;
    DoanhThuFragment doanhThuFragment;
    DanhSachNhanVienFragment danhSachNhanVienFragment;
    ThucDonFragment thucDonFragment;
    DanhSachChamCongFragment danhSachChamCongFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addUserFragment = new AddUserFragment();
        changePassFragment = new ChangePassFragment();
        hoaDonFragment = new HoaDonFragment();
        phongFragment = new PhongFragment();
        banFragment = new BanFragment();
        loaiDoUongFragment = new LoaiDoUongFragment();
        doUongFragment = new DoUongFragment();
        loaiDoAnFragment = new LoaiDoAnFragment();
        doAnFragment = new DoAnFragment();
        khachHangFragment = new KhachHangFragment();
        topFragment = new TopFragment();
        doanhThuFragment = new DoanhThuFragment();
        danhSachNhanVienFragment = new DanhSachNhanVienFragment();
        thucDonFragment = new ThucDonFragment();
        danhSachChamCongFragment = new DanhSachChamCongFragment();


        drawer = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar);

        //set toolbar thay thế actionbar
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.menu);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //dùng fragment phiếu mượn làm home

        manager = getSupportFragmentManager();

        manager.beginTransaction().add(R.id.frg_container_view,hoaDonFragment).commit();

        NavigationView nvView = findViewById(R.id.nvView);

        //show user in header
        mHeaderView = nvView.getHeaderView(0);
        tvUser = mHeaderView.findViewById(R.id.tvUser);

        Intent i = getIntent();
        String user = i.getStringExtra("user");

        //admin có quyền add user
        if(user.equalsIgnoreCase("admin")){
            nvView.getMenu().findItem(R.id.sub_AddNhanVien).setVisible(true);
            nvView.getMenu().findItem(R.id.sub_DsNhanVien).setVisible(true);
        }else{
            nvView.getMenu().findItem(R.id.sub_AddNhanVien).setVisible(false);
            nvView.getMenu().findItem(R.id.sub_DsNhanVien).setVisible(false);
            nvView.getMenu().findItem(R.id.sub_DoanhThu).setVisible(false);
            nvView.getMenu().findItem(R.id.sub_DS_ChamCong).setVisible(false);

        }
        //Điều hướng navigation

        nvView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.nav_ThucDon:
                        toolbar.setTitle("Quản lý thực đơn");
                        manager.beginTransaction().replace(R.id.frg_container_view,thucDonFragment).commit();
                        break;
                    case R.id.nav_HoaDon:
                        toolbar.setTitle("Quản lý hóa đơn");
                        manager.beginTransaction().replace(R.id.frg_container_view,hoaDonFragment).commit();
                        break;
                    case R.id.nav_Phong:
                        toolbar.setTitle("Quản lý phòng");
                        manager.beginTransaction().replace(R.id.frg_container_view,phongFragment).commit();
                        break;
                    case R.id.nav_Ban:
                        toolbar.setTitle("Quản lý bàn");
                        manager.beginTransaction().replace(R.id.frg_container_view,banFragment).commit();
                        break;
                    case R.id.nav_LoaiDoUong:
                        toolbar.setTitle("Quản lý loại đồ uống");
                        manager.beginTransaction().replace(R.id.frg_container_view,loaiDoUongFragment).commit();
                        break;
                    case R.id.nav_DoUong:
                        toolbar.setTitle("Quản lý đồ uống");
                        manager.beginTransaction().replace(R.id.frg_container_view,doUongFragment).commit();
                        break;
                    case R.id.nav_LoaiDoAn:
                        toolbar.setTitle("Quản lý loại đồ ăn");
                        manager.beginTransaction().replace(R.id.frg_container_view,loaiDoAnFragment).commit();
                        break;
                    case R.id.nav_DoAn:
                        toolbar.setTitle("Quản lý đồ ăn");
                        manager.beginTransaction().replace(R.id.frg_container_view,doAnFragment).commit();
                        break;
                    case R.id.nav_KhachHang:
                        toolbar.setTitle("Quản lý khách hàng");
                        manager.beginTransaction().replace(R.id.frg_container_view,khachHangFragment).commit();
                        break;

                    case R.id.sub_Top:
                        toolbar.setTitle("Quản lý sản phẩm bán chạy");
                        manager.beginTransaction().replace(R.id.frg_container_view,topFragment).commit();
                        break;

                    case R.id.sub_DoanhThu:
                        toolbar.setTitle("Quản lý doanh thu");
                        manager.beginTransaction().replace(R.id.frg_container_view,doanhThuFragment).commit();
                        break;

                    case R.id.sub_DS_ChamCong:
                        toolbar.setTitle("Danh sách chấm công");
                        manager.beginTransaction().replace(R.id.frg_container_view,danhSachChamCongFragment).commit();
                        break;

                    case R.id.sub_DsNhanVien:
                        toolbar.setTitle("Danh sách nhân viên");
                        manager.beginTransaction().replace(R.id.frg_container_view,danhSachNhanVienFragment).commit();
                        break;

                    case R.id.sub_AddNhanVien:
                        toolbar.setTitle("Quản lý thêm nhân viên");
                        manager.beginTransaction().replace(R.id.frg_container_view,addUserFragment).commit();
                        break;

                    case R.id.sub_DoiMatKhau:
                        toolbar.setTitle("Quản lý đổi mật khẩu");
                        manager.beginTransaction().replace(R.id.frg_container_view,changePassFragment).commit();
                        break;

                    case R.id.sub_DangXuat:
                        Toast.makeText(getApplicationContext(), "Đã đăng xuất", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                        finish();
                        break;
                }

                drawer.closeDrawer(nvView);
                return false;
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id==android.R.id.home){
            drawer.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }
}