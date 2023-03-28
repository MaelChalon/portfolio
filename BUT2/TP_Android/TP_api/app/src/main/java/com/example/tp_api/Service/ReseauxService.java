package com.example.tp_api.Service;

import com.example.tp_api.Model.Reseaux;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface ReseauxService {

    @GET("?dataset=sites-2g-3g-4g-france-metropolitaine-mon-reseau-mobile&q=&rows=20")
    Call<Reseaux> getRecords(@Query("start") String start, @Query("geofilter.distance") String loc);

}
