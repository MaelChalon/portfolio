package com.example.tp5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.tp5.model.Clock;
import com.example.tp5.model.Competition;
import com.example.tp5.model.CompetitionListe;
import com.example.tp5.service.ClockService;
import com.example.tp5.service.CompetitionService;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    final static String BASE_URL = "http://worldtimeapi.org/api/timezone/";
    final static String BASE_URL_COMPETITION = "https://perso.univ-lyon1.fr/lionel.buathier/";
    ClockService clockService = null;
    CompetitionService competitionService = null;
    ArrayList<Competition> listeCompetition = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createRetrofitClock();
        createRetrofitCompetition();
        getTime();
        CompetitionAdapter adapter = new CompetitionAdapter(new ArrayList<>());
        ListView lv = findViewById(R.id.listViewCompetition);
        lv.setAdapter(adapter);
        getFootballCompetition(adapter);
    }

    private void createRetrofitClock(){

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofitClock = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        clockService = retrofitClock.create(ClockService.class);
    }

    public void getTime(){
        Call<Clock> callTimeParis = clockService.getTimeParis();
        callTimeParis.enqueue(new Callback<Clock>() {
            @Override
            public void onResponse(Call<Clock> call, Response<Clock> response) {
                if(response.isSuccessful()){
                    Clock c = response.body();

                    Log.e("onResponse: ", c.getDatetime() );
                }else{
                    Log.e("onResponse: ", "non successful : " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Clock> call, Throwable t) {
                Log.e("onFailure: ", t.getMessage());
            }
        });
    }

    private void createRetrofitCompetition(){

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofitClock = new Retrofit.Builder()
                .baseUrl(BASE_URL_COMPETITION)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        competitionService = retrofitClock.create(CompetitionService.class);
    }

    private void getFootballCompetition(CompetitionAdapter ca){
        Call<CompetitionListe> callFootballCompetition = competitionService.getFootballCompetition();
        callFootballCompetition.enqueue(new Callback<CompetitionListe>() {
            @Override
            public void onResponse(Call<CompetitionListe> call, Response<CompetitionListe> response) {
                if(response.isSuccessful()){
                    CompetitionListe cl = response.body();
                    ca.setListeCompetition(new ArrayList<>(cl.getCompetitions()));
                    ca.notifyDataSetChanged();
                    Log.e("onResponse: ", cl.getCompetitions().toString());
                }else{
                    Log.e("onResponse: ", "non successful : " + response.code());
                }
            }

            @Override
            public void onFailure(Call<CompetitionListe> call, Throwable t) {
                Log.e("onFailure: ", t.getMessage());
            }
        });
    }
}