package com.example.nsd.pay.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import com.example.nsd.pay.R;
import com.example.nsd.pay.adapter.OfferPagerAdapter;
import com.example.nsd.pay.model.AllListData;
import com.example.nsd.pay.model.PInfo;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class WalkThroughActivity extends AppCompatActivity implements View.OnClickListener {

    final long DELAY_MS = 600;//delay in milliseconds before task is to be executed
    final long PERIOD_MS = 6000; // time in milliseconds between successive task executions.
    public ViewPager viewpager;
    int NUM_PAGES = 4, currentPage = 0;
    Timer timer;
    private OfferPagerAdapter adapter;
    public ArrayList<PInfo> walkThroughList = new ArrayList<>();
    public Button bt_register, bt_sign_in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walk_through);

        setData();

        viewpager = findViewById(R.id.viewpager);
        bt_register = findViewById(R.id.bt_register);
        bt_sign_in = findViewById(R.id.bt_sign_in);

        bt_register.setOnClickListener(this);
        bt_sign_in.setOnClickListener(this);

        assignViewPager();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_register:
                startActivity(new Intent(getApplicationContext(), SignupActivity.class));
                break;

            case R.id.bt_sign_in:
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                break;
        }
    }

    private void assignViewPager() {
        try {
            adapter = new OfferPagerAdapter(WalkThroughActivity.this, walkThroughList);
            viewpager.setAdapter(adapter);
            TabLayout tabLayout = findViewById(R.id.tab_layout);
            if (NUM_PAGES > 1) {
                tabLayout.setupWithViewPager(viewpager, true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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
            if (currentPage == NUM_PAGES) {
                currentPage = 0;
            }
            viewpager.setCurrentItem(currentPage++, true);
        };

        timer = new Timer(); // This will create a new Thread
        timer.schedule(new TimerTask() { // task to be scheduled
            @Override
            public void run() {
                handler.post(Update);
            }
        }, DELAY_MS, PERIOD_MS);
    }

    private void setData() {
        PInfo pInfo = new PInfo();
        pInfo.pname = "Absolute Safety";
        pInfo.appname = "Multi-story Security,\nInternational standard";
        pInfo.versionCode = R.drawable.img_4;
        walkThroughList.add(pInfo);

        PInfo pInfo2 = new PInfo();
        pInfo2.pname = "Various Services";
        pInfo2.appname = "Consumer loan Payment Pay\nBills And Money Other\nServices";
        pInfo2.versionCode = R.drawable.img_3;
        walkThroughList.add(pInfo2);

        PInfo pInfo3 = new PInfo();
        pInfo3.pname = "Easy Deposit and Withdrawal";
        pInfo3.appname = "Diversify recharge and withdraw\nmoney,free recharge with bank\naccount";
        pInfo3.versionCode = R.drawable.img_2;
        walkThroughList.add(pInfo3);

        PInfo pInfo4 = new PInfo();
        pInfo4.pname = "Attractive Deals";
        pInfo4.appname = "High discount, great promotion";
        pInfo4.versionCode = R.drawable.img_1;
        walkThroughList.add(pInfo4);
    }
}