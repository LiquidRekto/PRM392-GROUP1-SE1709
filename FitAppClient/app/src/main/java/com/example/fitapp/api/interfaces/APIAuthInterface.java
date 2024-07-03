package com.example.fitapp.api.interfaces;

import com.example.fitapp.model.FCMRequest;
import com.example.fitapp.model.request.LoginRequest;
import com.example.fitapp.model.request.RegisterRequest;
import com.example.fitapp.model.response.TokenResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface APIAuthInterface {
    @POST("/api/Auth/login")
    Call<TokenResponse> login(@Body LoginRequest loginReq);

    @POST("/api/Auth/register")
    Call<TokenResponse> register(@Body RegisterRequest registerReq);

    @POST("/api/Auth/logout")
    Call<Object> logout();
}
