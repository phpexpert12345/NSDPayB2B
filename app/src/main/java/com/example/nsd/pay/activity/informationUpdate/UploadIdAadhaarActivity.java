package com.example.nsd.pay.activity.informationUpdate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.nsd.pay.R;

public class UploadIdAadhaarActivity extends AppCompatActivity implements View.OnClickListener {

    public ImageView img_back;
    public TextView tv_toolbar_name;
    public Button bt_submit_for_verification;
    public ImageView img_aadhar_front_card, img_aadhar_back_card;
    public LinearLayout ll_upload_aadhar_front, ll_upload_aadhar_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_id_aadhaar);

        img_back = findViewById(R.id.img_back);
        tv_toolbar_name = findViewById(R.id.tv_toolbar_name);
        tv_toolbar_name.setText("Document Type");
        bt_submit_for_verification = findViewById(R.id.bt_submit_for_verification);
        img_aadhar_front_card = findViewById(R.id.img_aadhar_front_card);
        img_aadhar_back_card = findViewById(R.id.img_aadhar_back_card);
        ll_upload_aadhar_front = findViewById(R.id.ll_upload_aadhar_front);
        ll_upload_aadhar_back = findViewById(R.id.ll_upload_aadhar_back);

        img_back.setOnClickListener(this);
        bt_submit_for_verification.setOnClickListener(this);
        ll_upload_aadhar_front.setOnClickListener(this);
        ll_upload_aadhar_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;

            case R.id.bt_submit_for_verification:
                // startActivity(new Intent(this, AddRemitterActivity.class));
                break;

            case R.id.ll_upload_aadhar_front:
                break;

            case R.id.ll_upload_aadhar_back:
                break;
        }
    }
}