package com.example.nsd.pay.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.nsd.pay.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

public class TransferToWalletActivity extends AppCompatActivity implements View.OnClickListener {

    public ImageView img_back;
    public TextView tv_toolbar_name;
    public Button bt_confirm;
    public LinearLayout ll_biometric;
    public BottomSheetBehavior clickPaymentBySheetBehavior;
    public Button bt_cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_to_wallet);

        ll_biometric = findViewById(R.id.ll_biometric);
        clickPaymentBySheetBehavior = BottomSheetBehavior.from(ll_biometric);

        img_back = findViewById(R.id.img_back);
        tv_toolbar_name = findViewById(R.id.tv_toolbar_name);
        tv_toolbar_name.setText("Transfer to NSDPay Wallet");
        bt_confirm = findViewById(R.id.bt_confirm);
        bt_cancel = findViewById(R.id.bt_cancel);

        img_back.setOnClickListener(this);
        bt_confirm.setOnClickListener(this);
        bt_cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;

            case R.id.bt_confirm:
                clickPaymentBySheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                break;

            case R.id.bt_cancel:
                clickPaymentBySheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                startActivity(new Intent(TransferToWalletActivity.this, WithdrawlSuccessActivity.class));
                break;
        }
    }
}