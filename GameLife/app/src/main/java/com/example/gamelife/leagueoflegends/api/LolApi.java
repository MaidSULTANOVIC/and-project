package com.example.gamelife.leagueoflegends.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * This interface is used to make the different api calls
 */
public interface LolApi {

        // One issue here is that I didn't receive my permanent api key from Riot Games (it takes a long moment before they give you it), so I'm using
        // A development api key that expires every 24 hour, unfortunately it may not work for you but you can see how it should work on the presentation video

        @GET("/lol/summoner/v4/summoners/by-name/{summonerName}?api_key=RGAPI-c10b1a2e-e0e6-48cd-b93f-76926b381c25")
        Call<AccountResponse> getAccount(@Path("summonerName") String name);

        @GET("/lol/league/v4/entries/by-summoner/{encryptedSummonerId}?api_key=RGAPI-c10b1a2e-e0e6-48cd-b93f-76926b381c25")
        Call<List<LolResponse>> getSummoner(@Path("encryptedSummonerId") String id);

        @GET("/lol/match/v4/matchlists/by-account/{encryptedAccountId}?api_key=RGAPI-c10b1a2e-e0e6-48cd-b93f-76926b381c25")
        Call<MatchListResponse> getMatchList(@Path("encryptedAccountId") String accountId);

        @GET("/lol/match/v4/matches/{matchId}?api_key=RGAPI-c10b1a2e-e0e6-48cd-b93f-76926b381c25")
        Call<MatchDetailResponse> getMatchDetail(@Path("matchId") long matchId);



}
