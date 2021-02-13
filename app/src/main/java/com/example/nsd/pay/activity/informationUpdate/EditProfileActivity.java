package com.example.nsd.pay.activity.informationUpdate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.nsd.pay.R;
import com.example.nsd.pay.adapter.AccountTypeListAdapter;
import com.example.nsd.pay.interfaces.OnAccountTypeInterface;
import com.example.nsd.pay.model.AllListData;

import java.util.ArrayList;
import java.util.List;

public class EditProfileActivity extends AppCompatActivity implements View.OnClickListener, OnAccountTypeInterface {

    private Dialog accountTypeDialog;
    public ImageView img_back;
    public TextView tv_toolbar_name;
    public Button bt_update;
    public LinearLayout ll_gst;
    public OnAccountTypeInterface onAccountTypeInterface;
    public EditText ed_account_type;
    public List<AllListData> accountTypeList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        onAccountTypeInterface = this;
        setAccountTypeData();

        img_back = findViewById(R.id.img_back);
        tv_toolbar_name = findViewById(R.id.tv_toolbar_name);
        tv_toolbar_name.setText("Profile Edit");
        bt_update = findViewById(R.id.bt_update);
        ed_account_type = findViewById(R.id.ed_account_type);
        ll_gst = findViewById(R.id.ll_gst);
        ll_gst.setVisibility(View.GONE);

        ed_account_type.setOnClickListener(this);
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

            case R.id.ed_account_type:
                AccountTypeDialog();
                break;
        }
    }

    private void setAccountTypeData() {
        AllListData accountData1 = new AllListData();
        accountData1.name = "Individual";
        accountTypeList.add(accountData1);

        AllListData accountData2 = new AllListData();
        accountData2.name = "Company";
        accountTypeList.add(accountData2);
    }

    public void AccountTypeDialog() {

        accountTypeDialog = new Dialog(this, R.style.NewDialog);
        accountTypeDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        accountTypeDialog.setCancelable(true);
        accountTypeDialog.setContentView(R.layout.layout_account_type_dialog);

        TextView tv_dialog_title = accountTypeDialog.findViewById(R.id.tv_dialog_title);
        tv_dialog_title.setText("Select Account Type");

        RecyclerView rv_dropdown = accountTypeDialog.findViewById(R.id.rv_dropdown);
        AccountTypeListAdapter orderLimitListAdapter = new AccountTypeListAdapter(EditProfileActivity.this, accountTypeList,
                onAccountTypeInterface);
        rv_dropdown.setHasFixedSize(true);
        rv_dropdown.setLayoutManager(new LinearLayoutManager(this));
        rv_dropdown.setAdapter(orderLimitListAdapter);
        rv_dropdown.setNestedScrollingEnabled(false);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(accountTypeDialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        accountTypeDialog.getWindow().setAttributes(lp);
        accountTypeDialog.show();
    }

    @Override
    public void onAccountTypeItem(AllListData allListData) {
        ed_account_type.setText(allListData.name);
        accountTypeDialog.dismiss();
        if (allListData.name.equalsIgnoreCase("Company")) {
            ll_gst.setVisibility(View.VISIBLE);
        } else {
            ll_gst.setVisibility(View.GONE);
        }
    }
}