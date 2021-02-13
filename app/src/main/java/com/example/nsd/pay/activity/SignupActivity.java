package com.example.nsd.pay.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nsd.pay.BaseApp;
import com.example.nsd.pay.R;
import com.example.nsd.pay.api.ApiClient;
import com.example.nsd.pay.api.ApiService;
import com.example.nsd.pay.model.ResponseModel;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.hbb20.CountryCodePicker;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

import static com.example.nsd.pay.helper.LoadingDialog.alertDialog;
import static com.example.nsd.pay.helper.LoadingDialog.callLoadingDialog;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {

    public String TAG = SignupActivity.class.getName();
    public ImageView img_back;
    public TextView tv_toolbar_name;
    public EditText ed_mobile_number;
    public Button bt_submit;
    public LinearLayout ll_phone_verification;
    public BottomSheetBehavior clickPaymentBySheetBehavior;
    public Button bt_cancel, bt_next;
    public CheckBox chk_terms;
    public boolean check = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        ll_phone_verification = findViewById(R.id.ll_phone_verification);
        clickPaymentBySheetBehavior = BottomSheetBehavior.from(ll_phone_verification);

        img_back = findViewById(R.id.img_back);
        tv_toolbar_name = findViewById(R.id.tv_toolbar_name);
        tv_toolbar_name.setText("Signup");
        bt_submit = findViewById(R.id.bt_submit);
        bt_cancel = findViewById(R.id.bt_cancel);
        bt_next = findViewById(R.id.bt_next);
        chk_terms = findViewById(R.id.chk_terms);
        ed_mobile_number = findViewById(R.id.ed_mobile_number);

        img_back.setOnClickListener(this);
        bt_submit.setOnClickListener(this);
        bt_cancel.setOnClickListener(this);
        bt_next.setOnClickListener(this);

        chk_terms.setOnCheckedChangeListener((compoundButton, b) -> check = b);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;

            case R.id.bt_submit:
                if (check) {
                    if (!TextUtils.isEmpty(ed_mobile_number.getText().toString())) {
                        clickPaymentBySheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    } else {
                        BaseApp.getInstance().toastHelper().showSnackBar(ed_mobile_number,
                                "Please Enter valid mobile number", false);
                    }
                } else {
                    Toast.makeText(this, "Please tick check box", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.bt_next:
                clickPaymentBySheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                BaseApp.getInstance().sharedPref().setString(BaseApp.getInstance().sharedPref().COMMON_OTP_CHECK, "signup");
                saveContactNumber(ed_mobile_number.getText().toString().trim());
                break;

            case R.id.bt_cancel:
                clickPaymentBySheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                break;
        }
    }

    private void saveContactNumber(String mobileNumber) {
        callLoadingDialog(this, SignupActivity.this, R.drawable.test, "Please Wait...");
        ApiService apiService = ApiClient.getClient(this).create(ApiService.class);
        BaseApp.getInstance().getDisposable().add(apiService.saveContactNumber(mobileNumber)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<ResponseModel>() {
                    @Override
                    public void onSuccess(ResponseModel response) {
                        alertDialog.dismiss();
                        clickPaymentBySheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                        if (response.status) {
                            try {
                                startActivity(new Intent(SignupActivity.this, OtpVerificationActivity.class).
                                        putExtra("MOBILE_NUMBER", ed_mobile_number.getText().toString().trim()));
                                finish();
                                Toast.makeText(SignupActivity.this, response.data.otp, Toast.LENGTH_LONG).show();
                            } catch (Exception e) {
                            }
                        } else {
                            BaseApp.getInstance().toastHelper().showSnackBar(ed_mobile_number,
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
}