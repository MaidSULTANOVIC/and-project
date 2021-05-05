package com.example.gamelife;

import android.util.Log;

import com.example.gamelife.leagueoflegends.LolApi;
import com.example.gamelife.leagueoflegends.LolResponse;
import com.example.gamelife.leagueoflegends.Summoner;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    private static Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
            .baseUrl("https://euw1.api.riotgames.com")
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();

    private static LolApi lolApi = retrofit.create(LolApi.class);

    public static LolApi getLolApi() {
        return lolApi;
    }





}
