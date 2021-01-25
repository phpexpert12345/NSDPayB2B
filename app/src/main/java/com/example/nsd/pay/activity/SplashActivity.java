package com.example.nsd.pay.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.multidex.MultiDex;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.nsd.pay.R;

public class SplashActivity extends AppCompatActivity {

    public Handler handler;

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        MultiDex.install(this);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        handler = new Handler();
        handler.postDelayed(() -> {

            startActivity(new Intent(SplashActivity.this, AddBeneficiaryActivity.class));
            finish();

        }, 2000);
    }
}