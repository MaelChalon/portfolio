package com.example.tp5.service;

import com.example.tp5.model.Clock;
import com.example.tp5.model.Competition;
import com.example.tp5.model.CompetitionListe;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CompetitionService {
    @GET("football/competitions/")
    Call<CompetitionListe> getFootballCompetition();
}
