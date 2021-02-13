package com.example.nsd.pay.api;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.example.nsd.pay.BaseApp;
import com.example.nsd.pay.activity.LoginActivity;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.nsd.pay.BaseApp.Code;

public class ApiClient {

    public static final String BASE_URL = "http://nsdpay.in/";
    public static String ImagePath = BASE_URL;
    private static Retrofit retrofit = null;
    private static int REQUEST_TIMEOUT = 60;
    private static OkHttpClient okHttpClient;

    public static Retrofit getClient(Context context) {

        if (okHttpClient == null)
            initOkHttp(context);

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    private static void initOkHttp(final Context context) {
        OkHttpClient.Builder httpClient = new OkHttpClient().newBuilder()
                .connectTimeout(REQUEST_TIMEOUT, TimeUnit.MINUTES)
                .readTimeout(REQUEST_TIMEOUT, TimeUnit.MINUTES)
                .writeTimeout(REQUEST_TIMEOUT, TimeUnit.MINUTES);

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        httpClient.addInterceptor(interceptor);

        httpClient.addInterceptor(chain -> {
            Request original = chain.request();
            Request.Builder requestBuilder = null;
            requestBuilder = original.newBuilder()
                    .addHeader("Accept", "application/json")
                    .addHeader("Content-Type", "application/json");

            // Adding Authorization token (API Key)
            // Requests will be denied without API key
            if (BaseApp.getInstance().sharedPref().getString(BaseApp.getInstance().sharedPref().ACCESS_TOKEN) != null) {
                requestBuilder.addHeader("security", Code);
                requestBuilder.addHeader("Authorization", "Bearer " + BaseApp.getInstance().sharedPref().getString(BaseApp.getInstance().sharedPref().ACCESS_TOKEN));
            }
            Request request = requestBuilder.build();
            Response response = chain.proceed(request);

            if (response.code() == 401) {
                try {
                    BaseApp.getInstance().sharedPref().setString(BaseApp.getInstance().sharedPref().FIREBASE_TOKEN, null);
                    BaseApp.getInstance().sharedPref().setString(BaseApp.getInstance().sharedPref().ACCESS_TOKEN, null);
                    context.startActivity(new Intent(context, LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                    ((Activity) context).finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return response;
            }
            return response;
        });

        okHttpClient = httpClient.build();
    }
}

