package com.example.nsd.pay.activity.informationUpdate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.nsd.pay.R;

public class UploadPanCardActivity extends AppCompatActivity implements View.OnClickListener {

    public ImageView img_back;
    public TextView tv_toolbar_name;
    public Button bt_submit_for_verification;
    public ImageView img_pan_card;
    public LinearLayout ll_upload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_pan_card);

        img_back = findViewById(R.id.img_back);
        tv_toolbar_name = findViewById(R.id.tv_toolbar_name);
        tv_toolbar_name.setText("Pan Card");
        bt_submit_for_verification = findViewById(R.id.bt_submit_for_verification);
        img_pan_card = findViewById(R.id.img_pan_card);
        ll_upload = findViewById(R.id.ll_upload);

        img_back.setOnClickListener(this);
        bt_submit_for_verification.setOnClickListener(this);
        ll_upload.setOnClickListener(this);
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

            case R.id.ll_upload:
                break;
        }
    }
}