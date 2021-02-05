package com.example.nsd.pay.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.nsd.pay.BaseApp;
import com.example.nsd.pay.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

public class OtpVerificationActivity extends AppCompatActivity implements View.OnClickListener {

    public ImageView img_back;
    public TextView tv_toolbar_name;
    public Button bt_submit, bt_yes_login;
    public BottomSheetBehavior numberTakenSheet;
    public LinearLayout ll_number_taken_sheet;
    public LinearLayout bt_no_cancel;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verification);

        ll_number_taken_sheet = findViewById(R.id.ll_number_taken_sheet);
        numberTakenSheet = BottomSheetBehavior.from(ll_number_taken_sheet);

        img_back = findViewById(R.id.img_back);
        tv_toolbar_name = findViewById(R.id.tv_toolbar_name);
        tv_toolbar_name.setText("Otp Verification");
        bt_submit = findViewById(R.id.bt_submit);
        bt_no_cancel = findViewById(R.id.bt_no_cancel);
        bt_yes_login = findViewById(R.id.bt_yes_login);

        bt_no_cancel.setOnClickListener(this);
        bt_yes_login.setOnClickListener(this);

        img_back.setOnClickListener(this);
        bt_submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;

            case R.id.bt_submit:
                numberTakenSheet.setState(BottomSheetBehavior.STATE_EXPANDED);
                break;

            case R.id.bt_yes_login:
                numberTakenSheet.setState(BottomSheetBehavior.STATE_COLLAPSED);
                if (BaseApp.getInstance().sharedPref().getString(BaseApp.getInstance().sharedPref().COMMON_OTP_CHECK).
                        equalsIgnoreCase("login")) {
                    startActivity(new Intent(this, WelcomeActivity.class));
                } else {
                    startActivity(new Intent(this, CreatePasswordActivity.class));
                }
                break;

            case R.id.bt_no_cancel:
                numberTakenSheet.setState(BottomSheetBehavior.STATE_COLLAPSED);
                break;
        }
    }

    private void dialogOpen() {
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.layout_other_banks);
        Window window = dialog.getWindow();
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        //  Button btn_dialog_next = dialog.findViewById(R.id.btn_dialog_next);

        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.show();
    }
}

