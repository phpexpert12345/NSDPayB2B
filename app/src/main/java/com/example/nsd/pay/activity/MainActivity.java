package com.example.nsd.pay.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nsd.pay.R;
import com.example.nsd.pay.activity.informationUpdate.ProfileSetupActivity;
import com.example.nsd.pay.adapter.AllCategoryAdapter;
import com.example.nsd.pay.adapter.CashBackOfferAdapter;
import com.example.nsd.pay.adapter.DiscountOfferPagerAdapter;
import com.example.nsd.pay.adapter.DrawerMenuAdapter;
import com.example.nsd.pay.adapter.MainOfferPagerAdapter;
import com.example.nsd.pay.interfaces.AllCategoryInterface;
import com.example.nsd.pay.interfaces.CashBackOfferInterface;
import com.example.nsd.pay.interfaces.DrawerInterface;
import com.example.nsd.pay.model.AllListData;
import com.example.nsd.pay.model.PInfo;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, DrawerInterface,
        AllCategoryInterface, View.OnClickListener, CashBackOfferInterface {

    private boolean doubleBackToExitPressedOnce = false;
    public static int BadgeCount = 0;
    public DrawerLayout drawer;
    public RecyclerView rv_side_menu_list;
    public LinearLayout ll_login_signup;
    public RecyclerView.Adapter mDrawerAdapter;
    public TextView tv_version, tv_nav_user_name, tv_nav_balance_amount;
    public ImageView civ_user_image;
    public ImageView nav_icon;
    public DrawerInterface drawerInterface;
    final long DELAY_MS = 500;// delay in milliseconds before task is to be executed
    final long PERIOD_MS = 6000; // time in milliseconds between successive task executions.
    public ViewPager viewpager, discount_viewpager;
    int NUM_PAGES = 3, D_NUM_PAGES = 3, currentPage = 0, discountCurrentPage = 0;
    public Timer timer, discount_timer;
    public LinearLayout ll_b_profile, ll_b_bank, ll_b_home, ll_b_shop, ll_b_qr_scn;
    private MainOfferPagerAdapter adapter;
    private DiscountOfferPagerAdapter discountOfferPagerAdapter;
    public ArrayList<PInfo> walkThroughList = new ArrayList<>();
    public RecyclerView rv_all_categories;
    public RecyclerView.Adapter mAllCategory;
    public AllCategoryInterface allCategoryInterface;
    public ArrayList<AllListData> allCategoryList = new ArrayList<>();
    public RecyclerView rv_cashback_offer;
    public RecyclerView.Adapter mCashBackOffers;
    public CashBackOfferInterface cashBackOfferInterface;
    public LinearLayout ll_payment, ll_fund_transfer, ll_send_money, ll_add_money;
    public Button bt_finish_profile_setup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupNavigation();
    }

    private void setupNavigation() {

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        nav_icon = findViewById(R.id.nav_icon);
        nav_icon.setOnClickListener(nav_iconListner);
        drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        setData();
        setCategoryData();

        allCategoryInterface = this;
        drawerInterface = this;
        cashBackOfferInterface = this;

        tv_nav_user_name = navigationView.findViewById(R.id.tv_nav_user_name);
        civ_user_image = navigationView.findViewById(R.id.civ_user_image);
        ll_login_signup = navigationView.findViewById(R.id.ll_login_signup);

        ll_b_profile = findViewById(R.id.ll_b_profile);
        ll_b_bank = findViewById(R.id.ll_b_bank);
        ll_b_home = findViewById(R.id.ll_b_home);
        ll_b_shop = findViewById(R.id.ll_b_shop);
        ll_b_qr_scn = findViewById(R.id.ll_b_qr_scn);

        rv_all_categories = findViewById(R.id.rv_all_categories);
        rv_all_categories.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));

        mAllCategory = new AllCategoryAdapter(MainActivity.this, allCategoryList, allCategoryInterface);
        rv_all_categories.setAdapter(mAllCategory);

        viewpager = findViewById(R.id.viewpager);
        discount_viewpager = findViewById(R.id.discount_viewpager);

        tv_nav_balance_amount = navigationView.findViewById(R.id.tv_nav_balance_amount);
        tv_version = navigationView.findViewById(R.id.tv_version);

        rv_side_menu_list = findViewById(R.id.rv_side_menu_list);
        rv_side_menu_list.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL, false));

        mDrawerAdapter = new DrawerMenuAdapter(MainActivity.this, drawerInterface);
        rv_side_menu_list.setAdapter(mDrawerAdapter);

        rv_cashback_offer = findViewById(R.id.rv_cashback_offer);
        rv_cashback_offer.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL, false));

        mCashBackOffers = new CashBackOfferAdapter(MainActivity.this, cashBackOfferInterface);
        rv_cashback_offer.setAdapter(mCashBackOffers);

        ll_payment = findViewById(R.id.ll_payment);
        ll_fund_transfer = findViewById(R.id.ll_fund_transfer);
        ll_send_money = findViewById(R.id.ll_send_money);
        ll_add_money = findViewById(R.id.ll_add_money);
        bt_finish_profile_setup = findViewById(R.id.bt_finish_profile_setup);

        ll_b_profile.setOnClickListener(this);
        ll_b_bank.setOnClickListener(this);
        ll_b_home.setOnClickListener(this);
        ll_b_shop.setOnClickListener(this);
        ll_b_qr_scn.setOnClickListener(this);
        ll_payment.setOnClickListener(this);
        ll_fund_transfer.setOnClickListener(this);
        ll_send_money.setOnClickListener(this);
        ll_add_money.setOnClickListener(this);
        bt_finish_profile_setup.setOnClickListener(this);

        assignViewPager();
        assignDiscountViewPager();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_payment:
                Toast.makeText(MainActivity.this, "Coming soon Payment", Toast.LENGTH_SHORT).show();
                break;

            case R.id.ll_fund_transfer:
                startActivity(new Intent(this, AddBeneficiaryActivity.class));
                break;

            case R.id.ll_send_money:
                Toast.makeText(MainActivity.this, "Coming soon Send Money", Toast.LENGTH_SHORT).show();
                break;

            case R.id.ll_add_money:
                Toast.makeText(MainActivity.this, "Coming soon Add Money", Toast.LENGTH_SHORT).show();
                break;

            case R.id.bt_finish_profile_setup:
                startActivity(new Intent(MainActivity.this, ProfileSetupActivity.class));
                break;
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
        }
    }

    private View.OnClickListener nav_iconListner = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            drawer.openDrawer(GravityCompat.START);
        }
    };

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    public void onClickDrawerItem(String name, String id) {
        drawer.closeDrawers();
      /*  if (id.equalsIgnoreCase("1")) {
            drawer.closeDrawers();
        } else if (id.equalsIgnoreCase("2")) {
            startActivity(new Intent(this, MainActivity.class).putExtra("KEY", "2"));
            drawer.closeDrawers();
        } else if (id.equalsIgnoreCase("3")) {
            startActivity(new Intent(this, MainActivity.class).putExtra("KEY", "3"));
            drawer.closeDrawers();
        } else if (id.equalsIgnoreCase("4")) {
            startActivity(new Intent(this, MainActivity.class).putExtra("KEY", "16"));
            drawer.closeDrawers();
        }*/
    }

    private void assignViewPager() {
        try {
            adapter = new MainOfferPagerAdapter(MainActivity.this, walkThroughList);
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

    private void assignDiscountViewPager() {
        try {
            discountOfferPagerAdapter = new DiscountOfferPagerAdapter(MainActivity.this, walkThroughList);
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
        startActivity(new Intent(MainActivity.this, RechargeAndPayBillsActivity.class));
        Toast.makeText(MainActivity.this, allListData.name, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClickCashBackItem(AllListData allListData) {

    }
}