package com.example.nsd.pay.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.nsd.pay.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener {

    public EditText et_password;
    public Button bt_next;
    private boolean showPass = false;
    private ImageView ShowHidePasswordBtn;
    public LinearLayout ll_biometric, ll_face;
    public BottomSheetBehavior clickPaymentBySheetBehavior, clickFace, clickLogout;
    public Button bt_cancel, bt_cancel_face, bt_yes_logout;
    public LinearLayout ll_biometric_img, ll_face_lock, ll_logout;
    public LinearLayout ll_logout_sheet, bt_no_cancel;
    public ImageView img_biometric_check, img_face_check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        ll_face = findViewById(R.id.ll_face);
        clickFace = BottomSheetBehavior.from(ll_face);

        ll_biometric = findViewById(R.id.ll_biometric);
        clickPaymentBySheetBehavior = BottomSheetBehavior.from(ll_biometric);

        ll_logout_sheet = findViewById(R.id.ll_logout_sheet);
        clickLogout = BottomSheetBehavior.from(ll_logout_sheet);

        et_password = findViewById(R.id.et_password);
        ShowHidePasswordBtn = findViewById(R.id.show_hide_password_forgetPass);
        bt_next = findViewById(R.id.bt_next);
        bt_cancel = findViewById(R.id.bt_cancel);
        ll_biometric_img = findViewById(R.id.ll_biometric_img);
        ll_face_lock = findViewById(R.id.ll_face_lock);
        bt_cancel_face = findViewById(R.id.bt_cancel_face);
        ll_logout = findViewById(R.id.ll_logout);
        img_biometric_check = findViewById(R.id.img_biometric_check);
        img_face_check = findViewById(R.id.img_face_check);
        bt_no_cancel = findViewById(R.id.bt_no_cancel);
        bt_yes_logout = findViewById(R.id.bt_yes_logout);

        img_biometric_check.setVisibility(View.INVISIBLE);
        img_face_check.setVisibility(View.INVISIBLE);

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

        bt_no_cancel.setOnClickListener(this);
        bt_yes_logout.setOnClickListener(this);
        bt_cancel.setOnClickListener(this);
        ll_biometric_img.setOnClickListener(this);
        ll_face_lock.setOnClickListener(this);
        bt_next.setOnClickListener(this);
        bt_cancel_face.setOnClickListener(this);
        ll_logout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_cancel:
                img_biometric_check.setVisibility(View.INVISIBLE);
                img_face_check.setVisibility(View.INVISIBLE);
                clickPaymentBySheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                break;
            case R.id.bt_cancel_face:
                img_biometric_check.setVisibility(View.INVISIBLE);
                img_face_check.setVisibility(View.INVISIBLE);
                clickFace.setState(BottomSheetBehavior.STATE_COLLAPSED);
                break;
            case R.id.ll_biometric_img:

                img_biometric_check.setVisibility(View.VISIBLE);
                img_face_check.setVisibility(View.INVISIBLE);

                clickPaymentBySheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                //startActivity(new Intent(this, AddBeneficiaryActivity.class));
                break;
            case R.id.ll_face_lock:
                img_biometric_check.setVisibility(View.INVISIBLE);
                img_face_check.setVisibility(View.VISIBLE);
                clickFace.setState(BottomSheetBehavior.STATE_EXPANDED);
                // startActivity(new Intent(this, AddBeneficiaryActivity.class));
                break;
            case R.id.bt_next:
                startActivity(new Intent(this, AddBeneficiaryActivity.class));
                break;
            case R.id.ll_logout:
                clickLogout.setState(BottomSheetBehavior.STATE_EXPANDED);
                break;
            case R.id.bt_no_cancel:
                clickLogout.setState(BottomSheetBehavior.STATE_COLLAPSED);
                break;
            case R.id.bt_yes_logout:
                Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show();
                clickLogout.setState(BottomSheetBehavior.STATE_COLLAPSED);
                break;
        }
    }
}