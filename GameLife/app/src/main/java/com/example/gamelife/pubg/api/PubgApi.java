package com.example.gamelife.pubg.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * PubgApi interface
 *
 * Most of the query need to have specific Headers in order to work (+ API key in Headers)
 */
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


    @Headers({
            "Accept: application/vnd.api+json"
    })
    @GET("/shards/steam/matches/{matchId}")
    Call<PubgMatchResponse> getMatchDetail(@Path("matchId") String matchId);


    @Headers({
            "Accept: application/vnd.api+json",
            "Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI0YTgzZjIwMC05NjJjLTAxMzktMGNjNy0xZGRmYTY2NTE1OTkiLCJpc3MiOiJnYW1lbG9ja2VyIiwiaWF0IjoxNjIwOTE5MDY0LCJwdWIiOiJibHVlaG9sZSIsInRpdGxlIjoicHViZyIsImFwcCI6ImdhbWVsaWZlIn0.Wiz632qc7Y59Pf1lBVl1cNpcqNQzydKpxhC9Evezdq0"
    })
    @GET("/shards/steam/seasons")
    Call<PubgSeasonResponse> getSeason();

    @Headers({
            "Accept: application/vnd.api+json",
            "Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI0YTgzZjIwMC05NjJjLTAxMzktMGNjNy0xZGRmYTY2NTE1OTkiLCJpc3MiOiJnYW1lbG9ja2VyIiwiaWF0IjoxNjIwOTE5MDY0LCJwdWIiOiJibHVlaG9sZSIsInRpdGxlIjoicHViZyIsImFwcCI6ImdhbWVsaWZlIn0.Wiz632qc7Y59Pf1lBVl1cNpcqNQzydKpxhC9Evezdq0"
    })
    @GET("/shards/steam/players/{accountId}/seasons/{seasonId}/ranked")
    Call<PubgRankedResponse> getRanked(@Path("accountId") String accountId, @Path("seasonId") String seasonId);
}
