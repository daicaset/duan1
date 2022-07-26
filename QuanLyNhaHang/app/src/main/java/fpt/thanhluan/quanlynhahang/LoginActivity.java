package fpt.thanhluan.quanlynhahang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import fpt.thanhluan.quanlynhahang.DAO.NhanVienDAO;

public class LoginActivity extends AppCompatActivity {

    EditText edUserName,edPassWord;
    Button btnLogin,btnCancel;
    CheckBox chkRememberPass;
    String strUser,strPass;
    NhanVienDAO nhanVienDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setTitle("Đăng nhập");

        edUserName = findViewById(R.id.edUserName);
        edPassWord = findViewById(R.id.edPassWord);
        btnLogin = findViewById(R.id.btnLogin);
        btnCancel = findViewById(R.id.btnCancel);
        chkRememberPass = findViewById(R.id.chkRememberPass);
        nhanVienDAO = new NhanVienDAO(this);
        nhanVienDAO.open();

        //đọc user,pass trong sharedPreferences

        SharedPreferences pref = getSharedPreferences("USER_FILE",MODE_PRIVATE);
        edUserName.setText(pref.getString("USERNAME",""));
        edPassWord.setText(pref.getString("PASSWORD",""));
        chkRememberPass.setChecked(pref.getBoolean("REMEMBER",false));

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edUserName.setText("");
                edPassWord.setText("");
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLogin();
            }
        });
    }

    public void checkLogin(){

        strUser = edUserName.getText().toString();
        strPass = edPassWord.getText().toString();

        if(strUser.isEmpty()||strPass.isEmpty()){
            Toast.makeText(getApplicationContext(), "Tên đăng nhập " +
                            "và mật khẩu không đúng",
                    Toast.LENGTH_SHORT).show();
        }else {
            if (nhanVienDAO.checkLogin(strUser,strPass)>0||(strUser.equals("admin")&&strPass.equals("admin"))){
                Toast.makeText(getApplicationContext(), "Đăng nhập thành công",
                        Toast.LENGTH_SHORT).show();
                rememberUser(strUser,strPass,chkRememberPass.isChecked());
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                i.putExtra("user",strUser);
                startActivity(i);
            }else {
                Toast.makeText(getApplicationContext(), "Tên đăng nhập " +
                                "và mật khẩu không đúng",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }


    public void rememberUser(String user,String pass,Boolean check){

        SharedPreferences pref = getSharedPreferences("USER_FILE",MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();

        if(!check){
            //không lưu dữ liệu
            edit.clear();
        }else {
            //lưu dữ liệu
            edit.putString("USERNAME",user);
            edit.putString("PASSWORD",pass);
            edit.putBoolean("REMEMBER",check);
        }

        edit.commit();//lưu lại toàn bộ
    }
}