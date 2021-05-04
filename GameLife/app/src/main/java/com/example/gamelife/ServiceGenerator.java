package com.example.gamelife;

import android.util.Log;

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

    public void requestSummoner(String id) {
        LolApi lolApi = ServiceGenerator.getLolApi();
        Call<LolResponse> call = lolApi.getSummoner(id);
        call.enqueue(new Callback<LolResponse>() {
            @Override
            public void onResponse(Call<LolResponse> call, Response<LolResponse> response) {
                if (response.code() == 200) {
                    Log.i("Retrofit",response.body().toString());
                }
            }
            @Override
            public void onFailure(Call<LolResponse> call, Throwable t) {
                Log.i("Retrofit", "Something went wrong :(");
            }
        });
    }



}
