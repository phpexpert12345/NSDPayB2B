package com.example.nsd.pay.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.example.nsd.pay.BaseApp;
import com.example.nsd.pay.R;
import com.example.nsd.pay.api.ApiClient;
import com.example.nsd.pay.api.ApiService;
import com.example.nsd.pay.model.ResponseModel;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

import static com.example.nsd.pay.helper.LoadingDialog.alertDialog;
import static com.example.nsd.pay.helper.LoadingDialog.callLoadingDialog;

public class OtpVerificationActivity extends AppCompatActivity implements View.OnClickListener {

    public String TAG = OtpVerificationActivity.class.getName();
    public ImageView img_back;
    public TextView tv_toolbar_name;
    public Button bt_submit;
    public BottomSheetBehavior numberTakenSheet;
    public LinearLayout ll_number_taken_sheet;
    public LinearLayout bt_no_cancel, ll_call;
    public PinView pinView;
    Dialog dialog;
    public TextView otpReadRemainingTime, btn_resendOtp, tv_change_number;

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
        ll_call = findViewById(R.id.ll_call);
        pinView = findViewById(R.id.pinView);

        otpReadRemainingTime = findViewById(R.id.tv_otpReadRemainingTime);
        btn_resendOtp = findViewById(R.id.btn_resendOtp);
        tv_change_number = findViewById(R.id.tv_change_number);

        bt_no_cancel.setOnClickListener(this);
        ll_call.setOnClickListener(this);

        btn_resendOtp.setOnClickListener(this);
        tv_change_number.setOnClickListener(this);
        img_back.setOnClickListener(this);
        bt_submit.setOnClickListener(this);

        startTimer();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;

            case R.id.bt_submit:
                if (!TextUtils.isEmpty(pinView.getText().toString())) {
                    //numberTakenSheet.setState(BottomSheetBehavior.STATE_EXPANDED);
                    if (BaseApp.getInstance().sharedPref().getString(BaseApp.getInstance().sharedPref().COMMON_OTP_CHECK).
                            equalsIgnoreCase("login")) {
                        startActivity(new Intent(this, WelcomeActivity.class));
                    } else {
                        otpVerify(getIntent().getStringExtra("MOBILE_NUMBER"), pinView.getText().toString().trim());
                        startTimer();
                    }
                } else {
                    BaseApp.getInstance().toastHelper().showSnackBar(pinView,
                            "Please Enter valid OTP", false);
                }
                break;

            case R.id.ll_call:
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

            case R.id.btn_resendOtp:
                resendOtp(getIntent().getStringExtra("MOBILE_NUMBER"));
                startTimer();
                break;

            case R.id.tv_change_number:

                break;
        }
    }

    private void otpVerify(String mobileNumber, String otp) {
        callLoadingDialog(this, OtpVerificationActivity.this, R.drawable.test, "Please Wait...");
        ApiService apiService = ApiClient.getClient(this).create(ApiService.class);
        BaseApp.getInstance().getDisposable().add(apiService.otpVerify(mobileNumber, otp)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<ResponseModel>() {
                    @Override
                    public void onSuccess(ResponseModel response) {
                        alertDialog.dismiss();
                        if (response.status) {
                            try {
                                if (BaseApp.getInstance().sharedPref().getString(BaseApp.getInstance().sharedPref().COMMON_OTP_CHECK).
                                        equalsIgnoreCase("login")) {
                                    startActivity(new Intent(OtpVerificationActivity.this, WelcomeActivity.class));
                                } else {
                                    startActivity(new Intent(OtpVerificationActivity.this, CreatePasswordActivity.class));
                                }
                                Toast.makeText(OtpVerificationActivity.this, response.message, Toast.LENGTH_LONG).show();
                            } catch (Exception e) {
                            }
                        } else {
                            BaseApp.getInstance().toastHelper().showSnackBar(pinView,
                                    response.message, false);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        alertDialog.dismiss();
                        Log.d(TAG, e.getMessage());
                    }
                }));
    }

    private void resendOtp(String mobileNumber) {
        callLoadingDialog(this, OtpVerificationActivity.this, R.drawable.test, "Please Wait...");
        ApiService apiService = ApiClient.getClient(this).create(ApiService.class);
        BaseApp.getInstance().getDisposable().add(apiService.resendOtp(mobileNumber)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<ResponseModel>() {
                    @Override
                    public void onSuccess(ResponseModel response) {
                        alertDialog.dismiss();
                        if (response.status) {
                            try {
                                startTimer();
                                Toast.makeText(OtpVerificationActivity.this, response.data.otp, Toast.LENGTH_LONG).show();
                            } catch (Exception e) {
                            }
                        } else {
                            BaseApp.getInstance().toastHelper().showSnackBar(pinView,
                                    response.message, false);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        alertDialog.dismiss();
                        Log.d(TAG, e.getMessage());
                    }
                }));
    }

    private void startTimer() {
        btn_resendOtp.setVisibility(View.GONE);
        otpReadRemainingTime.setVisibility(View.VISIBLE);
        countDownTimer.start();
    }

    CountDownTimer countDownTimer = new CountDownTimer(1 * 30000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            int seconds = (int) (millisUntilFinished / 1000);
            int minutes = seconds / 60;
            seconds = seconds % 60;
            otpReadRemainingTime.setText("" + String.format("%02d", minutes) + ":" + String.format("%02d", seconds));
        }

        @Override
        public void onFinish() {
            btn_resendOtp.setVisibility(View.VISIBLE);
            otpReadRemainingTime.setVisibility(View.GONE);
        }
    };

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

