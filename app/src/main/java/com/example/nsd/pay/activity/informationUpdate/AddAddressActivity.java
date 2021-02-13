package com.example.nsd.pay.activity.informationUpdate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nsd.pay.R;

public class AddAddressActivity extends AppCompatActivity implements View.OnClickListener {

    public ImageView img_back;
    public TextView tv_toolbar_name;
    public Button bt_update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);

        img_back = findViewById(R.id.img_back);
        tv_toolbar_name = findViewById(R.id.tv_toolbar_name);
        tv_toolbar_name.setText("Add Address");
        bt_update = findViewById(R.id.bt_update);

        img_back.setOnClickListener(this);
        bt_update.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;

            case R.id.bt_update:
                // startActivity(new Intent(this, AddRemitterActivity.class));
                break;
        }
    }
}