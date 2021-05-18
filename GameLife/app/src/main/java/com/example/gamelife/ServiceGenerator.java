package com.example.gamelife;

import com.example.gamelife.leagueoflegends.api.LolApi;

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
