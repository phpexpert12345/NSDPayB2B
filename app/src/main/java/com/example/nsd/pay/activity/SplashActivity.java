package com.example.nsd.pay.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.multidex.MultiDex;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.nsd.pay.R;

public class SplashActivity extends AppCompatActivity {

    public Handler handler;
    public ImageView img_tint;

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        MultiDex.install(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        img_tint = findViewById(R.id.img_tint);

        Animation aniRotate = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.clockwise);
        img_tint.startAnimation(aniRotate);

        handler = new Handler();
        handler.postDelayed(() -> {

            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
            finish();

        }, 4000);
    }
}