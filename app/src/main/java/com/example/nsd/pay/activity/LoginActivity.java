package com.example.nsd.pay.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nsd.pay.BaseApp;
import com.example.nsd.pay.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.hbb20.CountryCodePicker;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    public ImageView img_back;
    public TextView tv_toolbar_name;
    public Button bt_submit;
    public LinearLayout ll_phone_verification;
    public BottomSheetBehavior clickPaymentBySheetBehavior;
    public Button bt_cancel, bt_next;
    public LinearLayout ll_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ll_phone_verification = findViewById(R.id.ll_phone_verification);
        clickPaymentBySheetBehavior = BottomSheetBehavior.from(ll_phone_verification);

        img_back = findViewById(R.id.img_back);
        tv_toolbar_name = findViewById(R.id.tv_toolbar_name);
        tv_toolbar_name.setText("Login");
        bt_submit = findViewById(R.id.bt_submit);
        bt_cancel = findViewById(R.id.bt_cancel);
        bt_next = findViewById(R.id.bt_next);
        ll_register = findViewById(R.id.ll_register);

        img_back.setOnClickListener(this);
        bt_submit.setOnClickListener(this);
        bt_cancel.setOnClickListener(this);
        bt_next.setOnClickListener(this);
        ll_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;

            case R.id.bt_submit:
                clickPaymentBySheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                break;

            case R.id.bt_next:
                clickPaymentBySheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                BaseApp.getInstance().sharedPref().setString(BaseApp.getInstance().sharedPref().COMMON_OTP_CHECK, "login");
                startActivity(new Intent(this, OtpVerificationActivity.class));
                break;

            case R.id.bt_cancel:
                clickPaymentBySheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                break;

            case R.id.ll_register:
                startActivity(new Intent(this, SignupActivity.class));
                break;
        }
    }
}