package com.example.nsd.pay.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
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

        bt_no_cancel.setOnClickListener(this);
        ll_call.setOnClickListener(this);

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
                if (!TextUtils.isEmpty(pinView.getText().toString())) {
                    //numberTakenSheet.setState(BottomSheetBehavior.STATE_EXPANDED);
                    resendOtp(getIntent().getStringExtra("MOBILE_NUMBER"), pinView.getText().toString().trim());
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
        }
    }

    private void resendOtp(String mobileNumber, String otp) {
        callLoadingDialog(this, OtpVerificationActivity.this, R.drawable.test, "Please Wait...");
        ApiService apiService = ApiClient.getClient(this).create(ApiService.class);
        BaseApp.getInstance().getDisposable().add(apiService.otpVerify(mobileNumber, otp)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<ResponseModel>() {
                    @Override
                    public void onSuccess(ResponseModel response) {
                        alertDialog.dismiss();
                        if (response.data.status) {
                            try {
                            } catch (Exception e) {
                            }
                        } else {
                            BaseApp.getInstance().toastHelper().showSnackBar(pinView,
                                    response.data.message, false);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        alertDialog.dismiss();
                        Log.d(TAG, e.getMessage());
                    }
                }));
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

