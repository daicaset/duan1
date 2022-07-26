package fpt.thanhluan.quanlynhahang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;

public class ManHinhChaoActivity extends AppCompatActivity {
    ImageView appname,splash;
    LottieAnimationView lottieAnimationView,lottieAnimationView1 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_man_hinh_chao);

        appname = findViewById(R.id.appname);
        lottieAnimationView = findViewById(R.id.lottie);
        lottieAnimationView1 = findViewById(R.id.lottie3);
        splash = findViewById(R.id.splash);


        appname.animate().translationY(16000).setDuration(2000).setStartDelay(5600);
        lottieAnimationView.animate().translationY(16000).setDuration(2000).setStartDelay(5600);
        lottieAnimationView1.animate().translationY(16000).setDuration(2000).setStartDelay(5600);
//        splash.animate().translationY(-16000).setDuration(2000).setStartDelay(5600);




        splash.animate().translationY(-10600).setDuration(1000).setStartDelay(8600);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(ManHinhChaoActivity.this,LoginActivity.class);
                startActivity(i);
                finish();
            }
        },5500);
    }
}