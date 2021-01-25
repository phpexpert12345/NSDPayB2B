package com.example.nsd.pay.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

public class SharedPref {
    private static final String SHARED_PREF_NAME = "go_stylish";
    private Context context;

    public final String ACCESS_TOKEN = "accessToken";
    public final String FIREBASE_TOKEN = "FirebaseToken";
    public final String S_KEY = "key";


    public SharedPref(Context context) {
        this.context = context;
        SaveKey();
    }

    private static SharedPreferences getUserSharedPreferences(Context context) {
        return context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
    }


    public void setBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = getUserSharedPreferences(context).edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public void setLong(String key, long value) {
        SharedPreferences.Editor editor = getUserSharedPreferences(context).edit();
        editor.putLong(key, value);
        editor.commit();
    }

    public Long getLong(String key) {
        return getUserSharedPreferences(context).getLong(key, 0);
    }

    public boolean getBoolean(String key) {
        return getUserSharedPreferences(context).getBoolean(key, false);
    }

    public void setString(String key, String value) {
        SharedPreferences.Editor editor = getUserSharedPreferences(context).edit();
        editor.putString(key, value);
        editor.commit();
    }


    public String getString(String key) {
        return getUserSharedPreferences(context).getString(key, null);
    }

    public void setInt(String key, int value) {
        SharedPreferences.Editor editor = getUserSharedPreferences(context).edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public int getInt(String key) {
        return getUserSharedPreferences(context).getInt(key, 0);
    }

    public void setObject(String key, String value) {
        SharedPreferences.Editor editor = getUserSharedPreferences(context).edit();
//        editor.putString(key, new Gson().toJson(value));
        editor.putString(key, value);
        editor.commit();
    }

    public Object getObject(String key, final Class<?> aClass) {
        return new Gson().fromJson(getString(key), aClass);
    }

    public void clearPref() {

    }

    private void SaveKey() {
        SharedPref.this.setString(S_KEY, "*9@8#7$6%5&4*");
    }
}
