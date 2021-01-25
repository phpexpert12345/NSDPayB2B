package com.example.nsd.pay.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nsd.pay.R;
import com.example.nsd.pay.adapter.OtherBankAdapter;
import com.example.nsd.pay.adapter.PopularBankAdapter;

import java.util.ArrayList;
import java.util.List;

public class SelectBankActivity extends AppCompatActivity implements View.OnClickListener {

    public ImageView img_back, image_help;
    public TextView tv_toolbar_name;

    public RecyclerView rv_popular_bank, rv_other_bank;
    public RecyclerView.Adapter mPopularBankAdapter, mOtherBankAdapter;
    public List<String> popularArrayList = new ArrayList<>();
    public List<String> otherArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_bank);

        img_back = findViewById(R.id.img_back);
        image_help = findViewById(R.id.image_help);
        tv_toolbar_name = findViewById(R.id.tv_toolbar_name);
        tv_toolbar_name.setText("Select Bank");

        rv_popular_bank = findViewById(R.id.rv_popular_bank);
        rv_popular_bank.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));

        rv_other_bank = findViewById(R.id.rv_other_bank);
        rv_other_bank.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

        setData();

        img_back.setOnClickListener(this);
        image_help.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.image_help:
                Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void setData() {

        popularArrayList.add("Kotak Bank");
        popularArrayList.add("YES Bank");
        popularArrayList.add("SBI");
        popularArrayList.add("ICICI Bank");
        popularArrayList.add("AXIS Bank");
        popularArrayList.add("HDFC Bank");

        //----------------------------------------------------------------------------------------------------------------------------------

        otherArrayList.add("Allahabad Bank");
        otherArrayList.add("Andhra Bank");
        otherArrayList.add("Bank of Bahrain and Kuwait");
        otherArrayList.add("Bank of Baroda - Corporate Banking");
        otherArrayList.add("Bank of Baroda - Retail Banking");
        otherArrayList.add("Bank of India");
        otherArrayList.add("Bank of Maharashtra");
        otherArrayList.add("Canara Bank");
        otherArrayList.add("Central Bank of India");
        otherArrayList.add("City Union Bank");
        otherArrayList.add("Corporation Bank");
        otherArrayList.add("Deutsche Bank");
        otherArrayList.add("Development Credit Bank");
        otherArrayList.add("Dhanlaxmi Bank");
        otherArrayList.add("Federal Bank");
        otherArrayList.add("IDBI Bank");
        otherArrayList.add("Indian Bank");
        otherArrayList.add("Indian Overseas Bank");
        otherArrayList.add("IndusInd Bank");
        otherArrayList.add("ING Vysya Bank");
        otherArrayList.add("Jammu and Kashmir Bank");
        otherArrayList.add("Karnataka Bank Ltd");
        otherArrayList.add("Karur Vysya Bank");
        otherArrayList.add("Kotak Bank");
        otherArrayList.add("Laxmi Vilas Bank");
        otherArrayList.add("Oriental Bank of Commerce");
        otherArrayList.add("Punjab National Bank - Corporate Banking");
        otherArrayList.add("Punjab National Bank - Retail Banking");
        otherArrayList.add("Punjab & Sind Bank");
        otherArrayList.add("Shamrao Vitthal Co-operative Bank");
        otherArrayList.add("South Indian Bank");
        otherArrayList.add("State Bank of Bikaner & Jaipur");
        otherArrayList.add("State Bank of Hyderabad");
        otherArrayList.add("State Bank of India");
        otherArrayList.add("State Bank of Mysore");
        otherArrayList.add("State Bank of Patiala");
        otherArrayList.add("State Bank of Travancore");
        otherArrayList.add("Syndicate Bank");
        otherArrayList.add("Tamilnad Mercantile Bank Ltd.");
        otherArrayList.add("UCO Bank");
        otherArrayList.add("Union Bank of India");
        otherArrayList.add("United Bank of India");
        otherArrayList.add("Vijaya Bank");

        mPopularBankAdapter = new PopularBankAdapter(this, SelectBankActivity.this, popularArrayList);
        rv_popular_bank.setAdapter(mPopularBankAdapter);

        mOtherBankAdapter = new OtherBankAdapter(this, SelectBankActivity.this, otherArrayList);
        rv_other_bank.setAdapter(mOtherBankAdapter);
    }
}