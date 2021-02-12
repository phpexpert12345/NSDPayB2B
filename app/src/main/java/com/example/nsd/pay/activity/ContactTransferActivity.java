package com.example.nsd.pay.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nsd.pay.R;
import com.example.nsd.pay.adapter.TransferContactAdapter;
import com.example.nsd.pay.interfaces.ContactInterface;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ContactTransferActivity extends AppCompatActivity implements View.OnClickListener, ContactInterface {

    public ImageView img_back;
    public TextView tv_toolbar_name;
    public LinearLayout floating_action_button;
    public RecyclerView rv_contact_list;
    public RecyclerView.Adapter mContactListAdapter;
    public ContactInterface contactInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_transfer);

        contactInterface = this;

        img_back = findViewById(R.id.img_back);
        tv_toolbar_name = findViewById(R.id.tv_toolbar_name);
        floating_action_button = findViewById(R.id.floating_action_button);
        tv_toolbar_name.setText("Transfer");

        rv_contact_list = findViewById(R.id.rv_contact_list);
        rv_contact_list.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL, false));

        img_back.setOnClickListener(this);
        floating_action_button.setOnClickListener(this);

        mContactListAdapter = new TransferContactAdapter(ContactTransferActivity.this, contactInterface);
        rv_contact_list.setAdapter(mContactListAdapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.floating_action_button:
                startActivity(new Intent(this, AddRemitterActivity.class));
                break;
        }
    }

    @Override
    public void onClickContactItem(String name, String id) {
        startActivity(new Intent(ContactTransferActivity.this, SelectBankActivity.class));
    }
}