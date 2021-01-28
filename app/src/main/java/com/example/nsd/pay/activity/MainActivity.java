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

import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nsd.pay.R;
import com.example.nsd.pay.adapter.DrawerMenuAdapter;
import com.example.nsd.pay.adapter.MainOfferPagerAdapter;
import com.example.nsd.pay.interfaces.DrawerInterface;
import com.example.nsd.pay.model.PInfo;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, DrawerInterface {

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
    final long PERIOD_MS = 3000; // time in milliseconds between successive task executions.
    public ViewPager viewpager;
    int NUM_PAGES = 5, currentPage = 0;
    public Timer timer;
    private MainOfferPagerAdapter adapter;
    public ArrayList<PInfo> walkThroughList = new ArrayList<>();
    public RecyclerView rv_all_categories;
    public RecyclerView.Adapter mAllCategory;

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

        drawerInterface = this;
        tv_nav_user_name = navigationView.findViewById(R.id.tv_nav_user_name);
        civ_user_image = navigationView.findViewById(R.id.civ_user_image);
        ll_login_signup = navigationView.findViewById(R.id.ll_login_signup);

        rv_all_categories = findViewById(R.id.rv_all_categories);
        rv_all_categories.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));

        viewpager = findViewById(R.id.viewpager);

        tv_nav_balance_amount = navigationView.findViewById(R.id.tv_nav_balance_amount);
        tv_version = navigationView.findViewById(R.id.tv_version);

        rv_side_menu_list = findViewById(R.id.rv_side_menu_list);
        rv_side_menu_list.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL, false));

        mDrawerAdapter = new DrawerMenuAdapter(MainActivity.this, drawerInterface);
        rv_side_menu_list.setAdapter(mDrawerAdapter);

        assignViewPager();
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

    private void setData() {
        PInfo pInfo = new PInfo();
        pInfo.versionCode = R.mipmap.splash_tint_icon;
        walkThroughList.add(pInfo);

        PInfo pInfo2 = new PInfo();
        pInfo2.versionCode = R.mipmap.splash_tint_icon;
        walkThroughList.add(pInfo2);

        PInfo pInfo3 = new PInfo();
        pInfo3.versionCode = R.mipmap.splash_tint_icon;
        walkThroughList.add(pInfo3);

        PInfo pInfo4 = new PInfo();
        pInfo4.versionCode = R.mipmap.splash_tint_icon;
        walkThroughList.add(pInfo4);

        PInfo pInfo5 = new PInfo();
        pInfo5.versionCode = R.mipmap.splash_tint_icon;
        walkThroughList.add(pInfo5);
    }
}