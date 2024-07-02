package com.example.fitapp.api;

import com.example.fitapp.model.FCMRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIInterface {
    @POST("/api/FCMTest")
    Call<String> pushNotification(@Body FCMRequest fcmReq);
}
