package com.example.fitapp.api.interfaces;

import androidx.core.app.ShareCompat;

import com.example.fitapp.model.Schedule;
import com.example.fitapp.model.UserRole;

import java.time.LocalDate;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface APIScheduleInterface {
    @GET("api/Schedule/byUser/{user}/date/{date}")
    Call<List<Schedule>> getSchedulesOfDate(@Path("user") String userId, @Path("date") String date);
}
