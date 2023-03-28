package com.example.tp_api.manager;

import com.example.tp_api.Service.ReseauxService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiManager {

    public static int NumLigne = 0;
    final static String BASE_URL = "https://public.opendatasoft.com/api/records/1.0/search/";
    private ReseauxService reseauxService = null;

    private static ApiManager instance;

    public ReseauxService getReseauxService() {
        return reseauxService;
    }

    public static ApiManager getInstance() {
        if (instance == null) {
            instance = new ApiManager();
        }

        return instance;
    }

    private ApiManager() {
        createRetrofitReseaux();
    }

    private void createRetrofitReseaux() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();


        Retrofit retrofitReseaux = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        reseauxService = retrofitReseaux.create(ReseauxService.class);
    }
}


