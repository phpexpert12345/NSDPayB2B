package com.example.nsd.pay.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nsd.pay.R;
import com.example.nsd.pay.adapter.CashBackOfferAdapter;
import com.example.nsd.pay.adapter.DiscountOfferPagerAdapter;
import com.example.nsd.pay.adapter.RechargeCategoryAdapter;
import com.example.nsd.pay.adapter.SubCategoryPagerAdapter;
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
    public ArrayList<AllListData> childCategoryList = new ArrayList<>();
    final long DELAY_MS = 500;// delay in milliseconds before task is to be executed
    final long PERIOD_MS = 6000; // time in milliseconds between successive task executions.
    public ViewPager discount_viewpager, subCategoryViewPager;
    int D_NUM_PAGES = 3, CHILD_PAGES = 2, discountCurrentPage = 0;
    public Timer discount_timer;
    public ArrayList<PInfo> walkThroughList = new ArrayList<>();
    private DiscountOfferPagerAdapter discountOfferPagerAdapter;
    private SubCategoryPagerAdapter subCategoryPagerAdapter;
    public RecyclerView rv_cashback_offer;
    public RecyclerView.Adapter mCashBackOffers;
    public CashBackOfferInterface cashBackOfferInterface;
    public ImageView ll_previous, ll_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge_and_pay_bills);

        cashBackOfferInterface = this;
        setData();
        setCategoryData();

        img_back = findViewById(R.id.img_back);
        tv_toolbar_name = findViewById(R.id.tv_toolbar_name);
        tv_toolbar_name.setText("Recharge & Pay Bills");

        discount_viewpager = findViewById(R.id.discount_viewpager);
        subCategoryViewPager = findViewById(R.id.subCategoryViewPager);
        ll_previous = findViewById(R.id.ll_previous);
        ll_next = findViewById(R.id.ll_next);

        rv_cashback_offer = findViewById(R.id.rv_cashback_offer);
        rv_cashback_offer.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL, false));

        mCashBackOffers = new CashBackOfferAdapter(RechargeAndPayBillsActivity.this, cashBackOfferInterface);
        rv_cashback_offer.setAdapter(mCashBackOffers);

        assignDiscountViewPager();
        assignSubCategoryViewPager();

        img_back.setOnClickListener(this);
        ll_previous.setOnClickListener(this);
        ll_next.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.ll_previous:
                int pre = subCategoryViewPager.getCurrentItem();
                pre--;
                subCategoryViewPager.setCurrentItem(pre);
                // subCategoryViewPager.setCurrentItem(currentPage--, true);
                break;
            case R.id.ll_next:
                int nex = subCategoryViewPager.getCurrentItem();
                nex++;
                subCategoryViewPager.setCurrentItem(nex);
                //  subCategoryViewPager.setCurrentItem(currentPage++, true);
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

    private void assignSubCategoryViewPager() {
        try {
            subCategoryPagerAdapter = new SubCategoryPagerAdapter(RechargeAndPayBillsActivity.this, childCategoryList);
            subCategoryViewPager.setAdapter(subCategoryPagerAdapter);
            TabLayout tabLayout = findViewById(R.id.sub_tab_layout);
            if (CHILD_PAGES > 1) {
                tabLayout.setupWithViewPager(subCategoryViewPager, true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*After setting the adapter use the timer */
       /* final Handler handler = new Handler();
        final Runnable Update = () -> {
            if (currentPage == CHILD_PAGES) {
                currentPage = 0;
            }
            subCategoryViewPager.setCurrentItem(currentPage++, true);
        };

        child_timer = new Timer(); // This will create a new Thread
        child_timer.schedule(new TimerTask() { // task to be scheduled
            @Override
            public void run() {
                handler.post(Update);
            }
        }, DELAY_MS, PERIOD_MS);*/
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

        ArrayList<PInfo> pInfos = new ArrayList<>();
        AllListData data = new AllListData();
        PInfo mPre = new PInfo();
        mPre.pname = "Mobile Prepaid";
        mPre.versionCode = R.mipmap.mobile_icon;
        pInfos.add(mPre);

        PInfo mPos = new PInfo();
        mPos.pname = "Mobile Postpaid";
        mPos.versionCode = R.mipmap.mobile_icon;
        pInfos.add(mPos);

        PInfo dTh = new PInfo();
        dTh.pname = "DTH";
        dTh.versionCode = R.mipmap.cable_icon;
        pInfos.add(dTh);

        PInfo dataCable = new PInfo();
        dataCable.pname = "Data Card";
        dataCable.versionCode = R.mipmap.data_card_icon;
        pInfos.add(dataCable);

        PInfo elecBill = new PInfo();
        elecBill.pname = "Electric Bill";
        elecBill.versionCode = R.mipmap.electric_icon;
        pInfos.add(elecBill);

        PInfo waterBills = new PInfo();
        waterBills.pname = "Water Bill";
        waterBills.versionCode = R.mipmap.water_icon;
        pInfos.add(waterBills);

        PInfo pipeGas = new PInfo();
        pipeGas.pname = "Pipe Gas Bill";
        pipeGas.versionCode = R.mipmap.pipe_icon;
        pInfos.add(pipeGas);

        PInfo broadbandLand = new PInfo();
        broadbandLand.pname = "Broadband\nLandline";
        broadbandLand.versionCode = R.mipmap.broadband_icon;
        pInfos.add(broadbandLand);

        PInfo metro = new PInfo();
        metro.pname = "Metro";
        metro.versionCode = R.mipmap.metro_icon;
        pInfos.add(metro);

        PInfo fastTag = new PInfo();
        fastTag.pname = "Fastag Recharge";
        fastTag.versionCode = R.mipmap.fast_tag_icon;
        pInfos.add(fastTag);

        PInfo iocl = new PInfo();
        iocl.pname = "IOCL Rewards";
        iocl.versionCode = R.mipmap.rewards_icon;
        pInfos.add(iocl);

        PInfo bookCylinder = new PInfo();
        bookCylinder.pname = "Book Cylinder";
        bookCylinder.versionCode = R.mipmap.cylinder_icon;
        pInfos.add(bookCylinder);

        data.pInfoList = pInfos;
        childCategoryList.add(data);

        ArrayList<PInfo> pInfos2 = new ArrayList<>();
        AllListData data2 = new AllListData();

        PInfo pInfo22 = new PInfo();
        pInfo22.pname = "Book Movies";
        pInfo22.versionCode = R.mipmap.recharge_pay_bills_icon;
        pInfos2.add(pInfo22);

        PInfo pInfo33 = new PInfo();
        pInfo33.pname = "Amazon Prime";
        pInfo33.versionCode = R.mipmap.recharge_pay_bills_icon;
        pInfos2.add(pInfo33);

        PInfo pInfo44 = new PInfo();
        pInfo44.pname = "Phone";
        pInfo44.versionCode = R.mipmap.recharge_pay_bills_icon;
        pInfos2.add(pInfo44);

        data2.pInfoList = pInfos2;
        childCategoryList.add(data2);
    }

    @Override
    public void onClickCategoryItem(AllListData allListData) {
        Toast.makeText(RechargeAndPayBillsActivity.this, allListData.name, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClickCashBackItem(AllListData allListData) {

    }
}