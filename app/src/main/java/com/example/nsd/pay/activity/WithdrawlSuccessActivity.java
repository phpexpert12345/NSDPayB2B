package com.example.nsd.pay.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.nsd.pay.R;

public class WithdrawlSuccessActivity extends AppCompatActivity implements View.OnClickListener {

    public ImageView img_back;
    public TextView tv_toolbar_name;
    public Button bt_goto_home;
    public ImageView img_background, img_right;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdrawl_success);

        img_back = findViewById(R.id.img_back);
        tv_toolbar_name = findViewById(R.id.tv_toolbar_name);
        tv_toolbar_name.setText("Withdrawal");
        bt_goto_home = findViewById(R.id.bt_goto_home);
        img_background = findViewById(R.id.img_background);
        img_right = findViewById(R.id.img_right);

        img_back.setOnClickListener(this);
        bt_goto_home.setOnClickListener(this);

        YoYo.with(Techniques.Landing)//Landing FadeIn
                .duration(3600)
                .repeat(0)
                .playOn(img_background);

        YoYo.with(Techniques.Landing)
                .duration(3600)
                .repeat(0)
                .playOn(img_right);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;

            case R.id.bt_goto_home:
                startActivity(new Intent(WithdrawlSuccessActivity.this, MainActivity.class).
                        addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
                finish();
                break;
        }
    }
}