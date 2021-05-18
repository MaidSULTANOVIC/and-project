package com.example.gamelife.pubg;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.gamelife.pubg.api.PubgAccountResponse;
import com.example.gamelife.pubg.api.PubgApi;
import com.example.gamelife.pubg.api.PubgMatchResponse;
import com.example.gamelife.pubg.api.PubgRankedResponse;
import com.example.gamelife.pubg.api.PubgSeasonResponse;
import com.example.gamelife.pubg.api.PubgStatsResponse;
import com.example.gamelife.pubg.models.gameData.PubgMatchData;
import com.example.gamelife.pubg.models.playerInfo.PlayerInfo;
import com.example.gamelife.pubg.models.playerStats.PubgPlayerStats;
import com.example.gamelife.pubg.models.ranked.PubgRanked;
import com.example.gamelife.pubg.models.season.PubgSeason;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PubgRepository {
    private static PubgRepository instance;

    //All LiveData used to display data
    private final MutableLiveData<PlayerInfo> searchedAccount;
    private final MutableLiveData<PubgPlayerStats> searchedPlayerStats;
    private final MutableLiveData<PubgMatchData> searchedMatchData;
    private final MutableLiveData<PubgSeason> searchedSeason;
    private final MutableLiveData<PubgRanked> searchedRanked;

    private PubgRepository(){
        searchedAccount = new MutableLiveData<>();
        searchedPlayerStats = new MutableLiveData<>();
        searchedMatchData = new MutableLiveData<>();
        searchedSeason = new MutableLiveData<>();
        searchedRanked = new MutableLiveData<>();

    }

    public static synchronized PubgRepository getInstance(){
        if (instance == null){
            instance = new PubgRepository();
        }
        return instance;
    }



    public LiveData<PlayerInfo> getSearchedAccount(){
        return searchedAccount;
    }
    public LiveData<PubgPlayerStats> getSearchedPlayerStats(){
        return searchedPlayerStats;
    }
    public LiveData<PubgMatchData> getSearchedMatchData(){
        return searchedMatchData;
    }
    public LiveData<PubgSeason> getSearchedSeason(){
        return searchedSeason;
    }
    public LiveData<PubgRanked> getSearchedRanked(){
        return searchedRanked;
    }


    /*
        All the functions called when API request is needed
     */



    public void searchForAccount(String name){
        //A new PubgApi object is created
        PubgApi pubgApi = ServiceGeneratorPubg.getPubgApi();

        //We use pubgPi's api link to use it to fetch data
        Call<PubgAccountResponse> call = pubgApi.getAccount(name);

        //Fetch data
        call.enqueue(new Callback<PubgAccountResponse>() {
            @Override
            public void onResponse(Call<PubgAccountResponse> call, Response<PubgAccountResponse> response) {
                //If data is received, setValue for searchedAccount to the json response
                if (response.code() == 200) {
                    searchedAccount.setValue(response.body().getPubgAccount());

                }
            }
            @Override
            public void onFailure(Call<PubgAccountResponse> call, Throwable t) {
                Log.i("Retrofit", "Something went wrong :(" + t.getMessage());
            }
        });



    }

    public void searchForPlayerStats(String accountId){
        //A new PubgApi object is created
        PubgApi pubgApi = ServiceGeneratorPubg.getPubgApi();

        //We use pubgPi's api link to use it to fetch data
        Call<PubgStatsResponse> call = pubgApi.getStats(accountId,"false");


        //Fetch data
        call.enqueue(new Callback<PubgStatsResponse>() {
            @Override
            public void onResponse(Call<PubgStatsResponse> call, Response<PubgStatsResponse> response) {
                //If data is received, setValue for searchedPlayerStats to the json response
                if (response.code() == 200) {
                    searchedPlayerStats.setValue(response.body().getPlayerStats());
                }
            }
            @Override
            public void onFailure(Call<PubgStatsResponse> call, Throwable t) {
                Log.i("Retrofit", "Something went wrong :(" + t.getMessage());
            }
        });

    }

    public void searchForMatchData(String matchId){
        //A new PubgApi object is created
        PubgApi pubgApi = ServiceGeneratorPubg.getPubgApi();

        //We use pubgPi's api link to use it to fetch data
        Call<PubgMatchResponse> call = pubgApi.getMatchDetail(matchId);


        //Fetch data
        call.enqueue(new Callback<PubgMatchResponse>() {
            @Override
            public void onResponse(Call<PubgMatchResponse> call, Response<PubgMatchResponse> response) {
                //If data is received, setValue for searchedMatchData to the json response
                if (response.code() == 200) {
                    searchedMatchData.setValue(response.body().getMatchData());
                }
            }
            @Override
            public void onFailure(Call<PubgMatchResponse> call, Throwable t) {
                Log.i("Retrofit", "Something went wrong :(" + t.getMessage());
            }
        });



    }

    public void searchForSeason(){
        //A new PubgApi object is created
        PubgApi pubgApi = ServiceGeneratorPubg.getPubgApi();

        //We use pubgPi's api link to use it to fetch data
        Call<PubgSeasonResponse> call = pubgApi.getSeason();


        //Fetch data
        call.enqueue(new Callback<PubgSeasonResponse>() {
            @Override
            public void onResponse(Call<PubgSeasonResponse> call, Response<PubgSeasonResponse> response) {
                //If data is received, setValue for searchedSeason to the json response
                if (response.code() == 200) {
                    Log.d("Test"," into 200");
                    searchedSeason.setValue(response.body().getPubgSeason());
                }
            }
            @Override
            public void onFailure(Call<PubgSeasonResponse> call, Throwable t) {
                Log.i("Retrofit", "Something went wrong :(" + t.getMessage());
            }
        });



    }

    public void searchForRanked(String accountId, String seasonId){
        //A new PubgApi object is created
        PubgApi pubgApi = ServiceGeneratorPubg.getPubgApi();

        //We use pubgPi's api link to use it to fetch data
        Call<PubgRankedResponse> call = pubgApi.getRanked(accountId,seasonId);


        //Fetch data
        call.enqueue(new Callback<PubgRankedResponse>() {
            @Override
            public void onResponse(Call<PubgRankedResponse> call, Response<PubgRankedResponse> response) {
                //If data is received, setValue for searchedRanked to the json response
                if (response.code() == 200) {
                    searchedRanked.setValue(response.body().getPubgRanked());
                }
            }
            @Override
            public void onFailure(Call<PubgRankedResponse> call, Throwable t) {
                Log.i("Retrofit", "Something went wrong :(" + t.getMessage());
            }
        });



    }
}
