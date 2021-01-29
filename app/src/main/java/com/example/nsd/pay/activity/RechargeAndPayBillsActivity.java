package com.example.nsd.pay.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nsd.pay.R;
import com.example.nsd.pay.adapter.AllCategoryAdapter;
import com.example.nsd.pay.adapter.CashBackOfferAdapter;
import com.example.nsd.pay.adapter.DiscountOfferPagerAdapter;
import com.example.nsd.pay.adapter.MainOfferPagerAdapter;
import com.example.nsd.pay.adapter.RechargeCategoryAdapter;
import com.example.nsd.pay.interfaces.AllCategoryInterface;
import com.example.nsd.pay.interfaces.CashBackOfferInterface;
import com.example.nsd.pay.model.AllListData;
import com.example.nsd.pay.model.PInfo;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class RechargeAndPayBillsActivity extends AppCompatActivity implements View.OnClickListener, AllCategoryInterface, CashBackOfferInterface {

    public ImageView img_back;
    public TextView tv_toolbar_name;
    public RecyclerView rv_all_categories;
    public RecyclerView.Adapter mAllCategory;
    public AllCategoryInterface allCategoryInterface;
    public ArrayList<AllListData> allCategoryList = new ArrayList<>();
    final long DELAY_MS = 500;// delay in milliseconds before task is to be executed
    final long PERIOD_MS = 6000; // time in milliseconds between successive task executions.
    public ViewPager discount_viewpager;
    int D_NUM_PAGES = 3, currentPage = 0, discountCurrentPage = 0;
    public Timer discount_timer;
    public ArrayList<PInfo> walkThroughList = new ArrayList<>();
    private DiscountOfferPagerAdapter discountOfferPagerAdapter;
    public RecyclerView rv_cashback_offer;
    public RecyclerView.Adapter mCashBackOffers;
    public CashBackOfferInterface cashBackOfferInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge_and_pay_bills);

        allCategoryInterface = this;
        cashBackOfferInterface = this;
        setData();
        setCategoryData();

        img_back = findViewById(R.id.img_back);
        tv_toolbar_name = findViewById(R.id.tv_toolbar_name);
        tv_toolbar_name.setText("Recharge & Pay Bills");

        discount_viewpager = findViewById(R.id.discount_viewpager);

        rv_all_categories = findViewById(R.id.rv_all_categories);
        rv_all_categories.setLayoutManager(new GridLayoutManager(getApplicationContext(), 4));

        mAllCategory = new RechargeCategoryAdapter(RechargeAndPayBillsActivity.this, allCategoryList, allCategoryInterface);
        rv_all_categories.setAdapter(mAllCategory);

        rv_cashback_offer = findViewById(R.id.rv_cashback_offer);
        rv_cashback_offer.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL, false));

        mCashBackOffers = new CashBackOfferAdapter(RechargeAndPayBillsActivity.this, cashBackOfferInterface);
        rv_cashback_offer.setAdapter(mCashBackOffers);


        assignDiscountViewPager();

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

    private void assignDiscountViewPager() {
        try {
            discountOfferPagerAdapter = new DiscountOfferPagerAdapter(RechargeAndPayBillsActivity.this, walkThroughList);
            discount_viewpager.setAdapter(discountOfferPagerAdapter);
            TabLayout tabLayout = findViewById(R.id.discount_tab_layout);
            if (D_NUM_PAGES > 1) {
                tabLayout.setupWithViewPager(discount_viewpager, true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        discount_viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        /*After setting the adapter use the timer */
        final Handler handler = new Handler();
        final Runnable Update = () -> {
            if (discountCurrentPage == D_NUM_PAGES) {
                discountCurrentPage = 0;
            }
            discount_viewpager.setCurrentItem(discountCurrentPage++, true);
        };

        discount_timer = new Timer(); // This will create a new Thread
        discount_timer.schedule(new TimerTask() { // task to be scheduled
            @Override
            public void run() {
                handler.post(Update);
            }
        }, DELAY_MS, PERIOD_MS);
    }

    private void setData() {
        PInfo pInfo = new PInfo();
        pInfo.versionCode = R.drawable.loan_section_banner;
        walkThroughList.add(pInfo);

        PInfo pInfo2 = new PInfo();
        pInfo2.versionCode = R.drawable.footer_banner;
        walkThroughList.add(pInfo2);

        PInfo pInfo3 = new PInfo();
        pInfo3.versionCode = R.drawable.loan_section_banner;
        walkThroughList.add(pInfo3);
    }

    private void setCategoryData() {
        AllListData list1 = new AllListData();
        list1.name = "Recharge & Pay Bills";
        list1.drawable = R.mipmap.recharge_pay_bills_icon;
        allCategoryList.add(list1);

        AllListData list2 = new AllListData();
        list2.name = "Financial Service\n(DMT)";
        list2.drawable = R.mipmap.payments_icon;
        allCategoryList.add(list2);

        AllListData list3 = new AllListData();
        list3.name = "AePs";
        list3.drawable = R.mipmap.aeps_icon;
        allCategoryList.add(list3);

        AllListData list4 = new AllListData();
        list4.name = "Travels";
        list4.drawable = R.mipmap.travel_icon;
        allCategoryList.add(list4);

        AllListData list5 = new AllListData();
        list5.name = "NSDL Pan Card";
        list5.drawable = R.mipmap.nsdl_pancard_icon;
        allCategoryList.add(list5);

        AllListData list6 = new AllListData();
        list6.name = "Prepaid Cards";
        list6.drawable = R.mipmap.nsdl_pancard_icon;
        allCategoryList.add(list6);


        AllListData list7 = new AllListData();
        list7.name = "Movies & Events";
        list7.drawable = R.mipmap.movies_events_icon;
        allCategoryList.add(list7);

        AllListData list8 = new AllListData();
        list8.name = "Wallet to Wallet";
        list8.drawable = R.mipmap.wallet_to_wallet_icon;
        allCategoryList.add(list8);

        AllListData list9 = new AllListData();
        list9.name = "Fund Request";
        list9.drawable = R.mipmap.fund_request_icon;
        allCategoryList.add(list9);
    }

    @Override
    public void onClickCategoryItem(AllListData allListData) {
        Toast.makeText(RechargeAndPayBillsActivity.this, allListData.name, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClickCashBackItem(AllListData allListData) {

    }
}