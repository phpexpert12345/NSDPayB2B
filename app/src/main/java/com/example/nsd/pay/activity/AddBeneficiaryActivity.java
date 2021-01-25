package com.example.nsd.pay.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nsd.pay.R;

public class AddBeneficiaryActivity extends AppCompatActivity implements View.OnClickListener {

    public ImageView img_back;
    public TextView tv_toolbar_name;
    public Button bt_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_beneficiary);

        img_back = findViewById(R.id.img_back);
        tv_toolbar_name = findViewById(R.id.tv_toolbar_name);
        tv_toolbar_name.setText("Add Beneficiary");
        bt_submit = findViewById(R.id.bt_submit);

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
                startActivity(new Intent(this, AddRemitterActivity.class));
                break;
        }
    }
}