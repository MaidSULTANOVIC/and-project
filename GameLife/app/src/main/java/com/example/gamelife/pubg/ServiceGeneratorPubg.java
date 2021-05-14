package com.example.gamelife.pubg;

import com.example.gamelife.leagueoflegends.LolApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGeneratorPubg {

    private static Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
            .baseUrl("https://api.pubg.com")
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();

    private static PubgApi pubgApi = retrofit.create(PubgApi.class);

    public static PubgApi getPubgApi() {
        return pubgApi;
    }





}
