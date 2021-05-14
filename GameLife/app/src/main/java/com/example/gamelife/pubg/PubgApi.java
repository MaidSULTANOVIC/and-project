package com.example.gamelife.pubg;
import com.example.gamelife.leagueoflegends.AccountResponse;
import com.example.gamelife.leagueoflegends.LolResponse;
import com.example.gamelife.leagueoflegends.MatchDetailResponse;
import com.example.gamelife.leagueoflegends.MatchListResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PubgApi {

    @Headers({
            "Accept: application/vnd.api+json",
            "Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI0YTgzZjIwMC05NjJjLTAxMzktMGNjNy0xZGRmYTY2NTE1OTkiLCJpc3MiOiJnYW1lbG9ja2VyIiwiaWF0IjoxNjIwOTE5MDY0LCJwdWIiOiJibHVlaG9sZSIsInRpdGxlIjoicHViZyIsImFwcCI6ImdhbWVsaWZlIn0.Wiz632qc7Y59Pf1lBVl1cNpcqNQzydKpxhC9Evezdq0"
    })

    @GET("/shards/steam/players")
    Call<PubgAccountResponse> getAccount(@Query("filter[playerNames]") String name);

    @Headers({
            "Accept: application/vnd.api+json",
            "Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI0YTgzZjIwMC05NjJjLTAxMzktMGNjNy0xZGRmYTY2NTE1OTkiLCJpc3MiOiJnYW1lbG9ja2VyIiwiaWF0IjoxNjIwOTE5MDY0LCJwdWIiOiJibHVlaG9sZSIsInRpdGxlIjoicHViZyIsImFwcCI6ImdhbWVsaWZlIn0.Wiz632qc7Y59Pf1lBVl1cNpcqNQzydKpxhC9Evezdq0"
    })
    @GET("/shards/steam/players/{accountId}/seasons/lifetime")
    Call<PubgStatsResponse> getStats(@Path("accountId") String accountId, @Query("filter[gamepad]") String gamePad);

    @GET("/lol/match/v4/matchlists/by-account/{encryptedAccountId}?api_key=RGAPI-48bd8999-3b1e-4627-a4b5-109ba0132a0b")
    Call<MatchListResponse> getMatchList(@Path("encryptedAccountId") String accountId);

    @GET("/lol/match/v4/matches/{matchId}?api_key=RGAPI-48bd8999-3b1e-4627-a4b5-109ba0132a0b")
    Call<MatchDetailResponse> getMatchDetail(@Path("matchId") long matchId);
}
