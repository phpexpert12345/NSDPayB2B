package com.example.nsd.pay.activity.informationUpdate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
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

public class IdProofActivity extends AppCompatActivity implements View.OnClickListener, OnAccountTypeInterface {

    public Button bt_submit_for_verification;
    public EditText ed_document_type;
    private Dialog documentTypeDialog;
    public ImageView img_document;
    public LinearLayout ll_upload_document, ll_document;
    public ImageView img_back;
    public OnAccountTypeInterface onDocumentTypeInterface;
    public TextView tv_toolbar_name;
    public List<AllListData> documentTypeList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_id_proof);

        onDocumentTypeInterface = this;
        DocumentTypeData();
        img_back = findViewById(R.id.img_back);
        tv_toolbar_name = findViewById(R.id.tv_toolbar_name);
        tv_toolbar_name.setText("Document Type");
        bt_submit_for_verification = findViewById(R.id.bt_submit_for_verification);
        ed_document_type = findViewById(R.id.ed_document_type);
        img_document = findViewById(R.id.img_document);
        ll_upload_document = findViewById(R.id.ll_upload_document);
        ll_document = findViewById(R.id.ll_document);
        ll_document.setVisibility(View.GONE);

        ed_document_type.setOnClickListener(this);
        img_back.setOnClickListener(this);
        bt_submit_for_verification.setOnClickListener(this);

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

            case R.id.ed_document_type:
                DocumentTypeDialog();
                break;
        }
    }

    private void DocumentTypeData() {
        AllListData accountData1 = new AllListData();
        accountData1.name = "Passport";
        documentTypeList.add(accountData1);

        AllListData accountData2 = new AllListData();
        accountData2.name = "Driving license";
        documentTypeList.add(accountData2);

        AllListData accountData3 = new AllListData();
        accountData3.name = "Voter ID card";
        documentTypeList.add(accountData3);

        AllListData accountData4 = new AllListData();
        accountData4.name = "Job card issued by NREGA";
        documentTypeList.add(accountData4);

        AllListData accountData5 = new AllListData();
        accountData5.name = "Utility bill";
        documentTypeList.add(accountData5);

        AllListData accountData6 = new AllListData();
        accountData6.name = "Bank Statement/Passbook Copy/Cheque";
        documentTypeList.add(accountData6);

    }

    public void DocumentTypeDialog() {

        documentTypeDialog = new Dialog(this, R.style.NewDialog);
        documentTypeDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        documentTypeDialog.setCancelable(true);
        documentTypeDialog.setContentView(R.layout.layout_account_type_dialog);

        TextView tv_dialog_title = documentTypeDialog.findViewById(R.id.tv_dialog_title);
        tv_dialog_title.setText("Select Account Type");

        RecyclerView rv_dropdown = documentTypeDialog.findViewById(R.id.rv_dropdown);
        AccountTypeListAdapter orderLimitListAdapter = new AccountTypeListAdapter(IdProofActivity.this, documentTypeList,
                onDocumentTypeInterface);
        rv_dropdown.setHasFixedSize(true);
        rv_dropdown.setLayoutManager(new LinearLayoutManager(this));
        rv_dropdown.setAdapter(orderLimitListAdapter);
        rv_dropdown.setNestedScrollingEnabled(false);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(documentTypeDialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        documentTypeDialog.getWindow().setAttributes(lp);
        documentTypeDialog.show();
    }

    @Override
    public void onAccountTypeItem(AllListData allListData) {
        ed_document_type.setText(allListData.name);
        documentTypeDialog.dismiss();
        ll_document.setVisibility(View.VISIBLE);
    }
}