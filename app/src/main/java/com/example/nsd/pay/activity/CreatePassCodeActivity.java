package com.example.nsd.pay.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.chaos.view.PinView;
import com.example.nsd.pay.R;
import com.example.nsd.pay.enums.ButtonActions;
import com.example.nsd.pay.fragment.NumberBoard;

public class CreatePassCodeActivity extends AppCompatActivity implements View.OnClickListener, NumberBoard.OnKeyBoard {

    public PinView pinView;
    public ImageView img_back;
    public TextView tv_toolbar_name;
    public Button bt_create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_pass_code);

        bt_create = findViewById(R.id.bt_create);
        NumberBoard.getInstance().setButtonAction(ButtonActions.CREATE);
        tv_toolbar_name = findViewById(R.id.tv_toolbar_name);
        bt_create = findViewById(R.id.bt_create);
        img_back = findViewById(R.id.img_back);
        pinView = findViewById(R.id.pinView);
        tv_toolbar_name.setText("Create Passcode");
    /*    bt_create.setOnClickListener(v -> {
            if (pinView.length() == 4)
                savePassCode();
            //     else
            //  BaseApp.getInstance().toastHelper().showSnackBar(pinView, "Choose your passcode first", false);
        });*/

        img_back.setOnClickListener(v -> finish());
        bt_create.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_create:
                startActivity(new Intent(CreatePassCodeActivity.this, LoginActivity.class));
                break;
        }
    }

    private String clearText() {
        String str = pinView.getText().toString();

        if (str != null && str.length() > 0) {
            str = str.substring(0, str.length() - 1);
        }
        return str;
    }

    @Override
    public void onOnDeletePressed(View v) {
        pinView.setText(clearText());
    }

    private String appendText(String key) {
        return String.valueOf(pinView.getText().append(key));
    }

    @Override
    public void onKeyPressed(int value) {
        pinView.setText(appendText(String.valueOf(value)));
    }

    @Override
    public void onOkPressed(View v, ButtonActions buttonActions) {
        switch (buttonActions) {
            case CREATE:
                if (pinView.length() == 4)
                    //   savePassCode();
                    //  else
                    //  BaseApp.getInstance().toastHelper().showSnackBar(pinView, "Choose your passcode first", false);
                    break;
        }
    }
}