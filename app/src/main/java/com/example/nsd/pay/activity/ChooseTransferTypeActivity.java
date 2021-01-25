package com.example.nsd.pay.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nsd.pay.R;
import com.example.nsd.pay.adapter.RecentHistoryAdapter;
import com.example.nsd.pay.adapter.TransferContactAdapter;
import com.example.nsd.pay.interfaces.ContactInterface;

public class ChooseTransferTypeActivity extends AppCompatActivity implements View.OnClickListener, ContactInterface {

    public ImageView img_back;
    public TextView tv_toolbar_name;
    public RecyclerView rv_recent_history;
    public RecyclerView.Adapter mRecentHistory;
    public ContactInterface contactInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_transfer_type);

        contactInterface = this;

        img_back = findViewById(R.id.img_back);
        tv_toolbar_name = findViewById(R.id.tv_toolbar_name);
        tv_toolbar_name.setText("Transfer");

        rv_recent_history = findViewById(R.id.rv_recent_history);
        rv_recent_history.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL, false));


        mRecentHistory = new RecentHistoryAdapter(ChooseTransferTypeActivity.this, contactInterface);
        rv_recent_history.setAdapter(mRecentHistory);

        img_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
        }
    }

    @Override
    public void onClickContactItem(String name, String id) {
        startActivity(new Intent(ChooseTransferTypeActivity.this, PayToActivity.class));
    }
}