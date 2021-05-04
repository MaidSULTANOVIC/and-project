package com.example.gamelife;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface LolApi {

        @GET("/lol/league/v4/entries/by-summoner/{encryptedSummonerId}?api_key=RGAPI-93b38c5e-8b6b-4a30-bf3b-0ecfa6801bec")
        Call<LolResponse> getSummoner(@Path("encryptedSummonerId") String id);

}
