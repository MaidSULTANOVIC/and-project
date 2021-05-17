package com.example.gamelife.leagueoflegends;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * This interface is used to make the different api calls
 */
public interface LolApi {

        @GET("/lol/summoner/v4/summoners/by-name/{summonerName}?api_key=RGAPI-b86ad8d0-d473-4063-88cf-e1deb23cf2c7")
        Call<AccountResponse> getAccount(@Path("summonerName") String name);

        @GET("/lol/league/v4/entries/by-summoner/{encryptedSummonerId}?api_key=RGAPI-b86ad8d0-d473-4063-88cf-e1deb23cf2c7")
        Call<List<LolResponse>> getSummoner(@Path("encryptedSummonerId") String id);

        @GET("/lol/match/v4/matchlists/by-account/{encryptedAccountId}?api_key=RGAPI-b86ad8d0-d473-4063-88cf-e1deb23cf2c7")
        Call<MatchListResponse> getMatchList(@Path("encryptedAccountId") String accountId);

        @GET("/lol/match/v4/matches/{matchId}?api_key=RGAPI-b86ad8d0-d473-4063-88cf-e1deb23cf2c7")
        Call<MatchDetailResponse> getMatchDetail(@Path("matchId") long matchId);



}
