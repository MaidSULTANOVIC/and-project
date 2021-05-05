package com.example.gamelife.leagueoflegends;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface LolApi {

        @GET("/lol/league/v4/entries/by-summoner/{encryptedSummonerId}?api_key=RGAPI-4aa70594-3541-467d-8c9f-2bf69e340f06")
        Call<List<LolResponse>> getSummoner(@Path("encryptedSummonerId") String id);

        @GET("/lol/match/v4/matchlists/by-account/{encryptedAccountId}?api_key=RGAPI-4aa70594-3541-467d-8c9f-2bf69e340f06")
        Call<MatchListResponse> getMatchList(@Path("encryptedAccountId") String accountId);

        @GET("/lol/match/v4/matches/{matchId}?api_key=RGAPI-4aa70594-3541-467d-8c9f-2bf69e340f06")
        Call<MatchDetailResponse> getMatchDetail(@Path("matchId") long matchId);



}
