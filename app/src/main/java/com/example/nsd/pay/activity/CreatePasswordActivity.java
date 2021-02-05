package com.example.nsd.pay.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nsd.pay.R;

public class CreatePasswordActivity extends AppCompatActivity implements View.OnClickListener {

    public ImageView img_back;
    public TextView tv_toolbar_name;
    public EditText et_password, et_re_password;
    public Button bt_submit;
    private boolean showPass = false, reShowPass = false;
    private ImageView ShowHidePasswordBtn, show_hide_re_password_forgetPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_password);

        img_back = findViewById(R.id.img_back);
        tv_toolbar_name = findViewById(R.id.tv_toolbar_name);
        tv_toolbar_name.setText("Otp Verification");
        bt_submit = findViewById(R.id.bt_submit);
        et_password = findViewById(R.id.et_password);
        ShowHidePasswordBtn = findViewById(R.id.show_hide_password_forgetPass);
        ShowHidePasswordBtn.setOnClickListener(view -> {
            if (showPass) {
                showPass = false;
                ShowHidePasswordBtn.setImageDrawable(getResources().getDrawable(R.mipmap.show_password48));
                et_password.setTransformationMethod(new PasswordTransformationMethod());
                try {
                    et_password.setSelection(et_password.getText().toString().length());
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else {
                showPass = true;
                ShowHidePasswordBtn.setImageDrawable(getResources().getDrawable(R.drawable.hide_password48));
                et_password.setTransformationMethod(null);
                try {
                    et_password.setSelection(et_password.getText().toString().length());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        et_re_password = findViewById(R.id.et_re_password);
        show_hide_re_password_forgetPass = findViewById(R.id.show_hide_re_password_forgetPass);
        show_hide_re_password_forgetPass.setOnClickListener(view -> {
            if (reShowPass) {
                reShowPass = false;
                show_hide_re_password_forgetPass.setImageDrawable(getResources().getDrawable(R.mipmap.show_password48));
                et_re_password.setTransformationMethod(new PasswordTransformationMethod());
                try {
                    et_re_password.setSelection(et_re_password.getText().toString().length());
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else {
                reShowPass = true;
                show_hide_re_password_forgetPass.setImageDrawable(getResources().getDrawable(R.drawable.hide_password48));
                et_re_password.setTransformationMethod(null);
                try {
                    et_re_password.setSelection(et_re_password.getText().toString().length());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

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
                startActivity(new Intent(this, InformationActivity.class));
                break;
        }
    }
}