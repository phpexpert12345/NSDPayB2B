package com.example.nsd.pay.model;

import android.graphics.drawable.Drawable;

public class PInfo {

    public String appname = "";
    public String pname = "";
    public String versionName = "";
    public int versionCode = 0;
    public Drawable icon;

    @Override
    public String toString() {
        return "PInfo{" +
                "appname='" + appname + '\'' +
                ", pname='" + pname + '\'' +
                ", versionName='" + versionName + '\'' +
                ", versionCode=" + versionCode +
                ", icon=" + icon +
                '}';
    }
}
