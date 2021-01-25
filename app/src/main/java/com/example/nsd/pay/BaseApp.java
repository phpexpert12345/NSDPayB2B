package com.example.nsd.pay;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Handler;


import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

import com.example.nsd.pay.helper.ToastHelper;
import com.example.nsd.pay.utils.LocaleManager;
import com.example.nsd.pay.utils.SharedPref;


import io.reactivex.disposables.CompositeDisposable;

public class BaseApp extends MultiDexApplication {

    private static BaseApp mInstance;
    public static String Code = "*9@8#7$6%5&4*";

    public static synchronized BaseApp getInstance() {
        return mInstance;
    }

    private CompositeDisposable disposable;
    private ToastHelper toastHelper;
    private SharedPref sharedPref;
    private Handler handler;

    @Override
    protected void attachBaseContext(Context context) {
        //  super.attachBaseContext(context);
        super.attachBaseContext(LocaleManager.setLocale(context));
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        disposable = new CompositeDisposable();
        toastHelper = new ToastHelper();
        sharedPref = new SharedPref(this);
        handler = new Handler();
    }

    public SharedPref sharedPref() {
        return sharedPref;
    }

    public CompositeDisposable getDisposable() {
        return disposable;
    }

    public ToastHelper toastHelper() {
        return toastHelper;
    }

    public Handler handler() {
        return handler;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        LocaleManager.setLocale(this);
    }
}
