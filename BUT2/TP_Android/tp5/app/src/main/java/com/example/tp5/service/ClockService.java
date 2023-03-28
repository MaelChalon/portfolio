package com.example.tp5.service;

import com.example.tp5.model.Clock;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ClockService {
    @GET("Europe/paris")
    Call<Clock> getTimeParis();

    @GET("Europe/city")
    Call<Clock> getTimeCityEurope(@Path("city")String citySelected);
}
