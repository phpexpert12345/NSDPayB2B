package com.example.nsd.pay.api;


import com.example.nsd.pay.model.RequestModel;
import com.example.nsd.pay.model.ResponseModel;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    //    @Header("X-Bearer-Token") String authorization

    @GET("/nsdpay/public/api/post")
    Single<ResponseModel> getAllPosts();

    @FormUrlEncoded
    @POST("/nsdpay/public/api/saveContatNumber")
    Single<ResponseModel> saveContactNumber(@Field("mobile_number") String mobile_number);

    @FormUrlEncoded
    @POST("/nsdpay/public/api/resendOtp")
    Single<ResponseModel> resendOtp(@Field("mobile_number") String mobile_number);

    @FormUrlEncoded
    @POST("/nsdpay/public/api/otpVerify")
    Single<ResponseModel> otpVerify(@Field("mobile_number") String mobile_number, @Field("otp") String otp);

    @POST("/nsdpay/public/api/register")
    Single<ResponseModel> register(@Body RequestModel requestModel);

}