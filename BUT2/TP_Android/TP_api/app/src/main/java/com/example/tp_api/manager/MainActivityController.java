package com.example.tp_api.manager;

import android.util.Log;

import com.example.tp_api.Model.Reseaux;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivityController {
    private final ApiManager apiManager;

    public MainActivityController() {
        apiManager = ApiManager.getInstance();
    }

    public void getReseaux(ReseauxDataManagerCallBack callBack, String start, String loc){
        Call<Reseaux> reseauxCall = apiManager.getReseauxService().getRecords(start, loc);
        reseauxCall.enqueue(new Callback<Reseaux>() {
            @Override
            public void onResponse(Call<Reseaux> call, Response<Reseaux> response) {
                if (response.isSuccessful()) {
                    Reseaux r = response.body();
                    Log.e("onResponse",Integer.toString(r.getNhits()));
                    callBack.getReseauxResponseSuccess(r);
                    ApiManager.NumLigne+=20;

                } else {
                    Log.e("onResponse", "Not successfull : " + response.code());
                    callBack.getReseauxResponseError("Erreur le serveur a repondu status : " + response.code());
                }

            }

            @Override
            public void onFailure(Call<Reseaux> call, Throwable t) {
                Log.e("onFailure", t.getLocalizedMessage());
                callBack.getReseauxResponseError("Erreur lors de la requete : " + t.getLocalizedMessage());

            }
        });
    }
}
