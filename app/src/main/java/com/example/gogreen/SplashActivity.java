package com.example.gogreen;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {

    ImageView ivLogo;
    TextView tvTitle;
    Handler handler;
     Animation animtranslate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ivLogo=findViewById(R.id.ivSplashLogo);
        tvTitle=findViewById(R.id.tvSplashTitle);

        animtranslate= AnimationUtils.loadAnimation(SplashActivity.this,R.anim.toptobottomtranslate);
        ivLogo.startAnimation(animtranslate);

        handler= new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(i);

            }
        } ,4000);







    }
}
