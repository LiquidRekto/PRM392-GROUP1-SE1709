package com.example.fitapp.api.interfaces;

import com.example.fitapp.model.UserRole;
import com.example.fitapp.model.request.LoginRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;

public interface APIUserRoleInterface {
    @GET("api/UserRole/all")
    Call<List<UserRole>> getAllUserRoles();
}
