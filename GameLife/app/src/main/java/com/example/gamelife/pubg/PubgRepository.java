package com.example.gamelife.pubg;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.gamelife.ServiceGenerator;
import com.example.gamelife.leagueoflegends.AccountResponse;
import com.example.gamelife.leagueoflegends.LolApi;
import com.example.gamelife.leagueoflegends.LolRepository;
import com.example.gamelife.leagueoflegends.models.LolAccount;
import com.example.gamelife.leagueoflegends.models.MatchDetail;
import com.example.gamelife.leagueoflegends.models.MatchList;
import com.example.gamelife.leagueoflegends.models.Summoner;
import com.example.gamelife.pubg.models.playerInfo.PlayerInfo;
import com.example.gamelife.pubg.models.playerStats.PubgPlayerStats;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PubgRepository {
    private static PubgRepository instance;
    private final MutableLiveData<PlayerInfo> searchedAccount;
    private final MutableLiveData<PubgPlayerStats> searchedPlayerStats;

    private PubgRepository(){
        searchedAccount = new MutableLiveData<>();
        searchedPlayerStats = new MutableLiveData<>();

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


    public void searchForAccount(String name){
        //A new LolApi object is created
        PubgApi pubgApi = ServiceGeneratorPubg.getPubgApi();

        //We use lolApi's api link to use it to fetch data
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
        //A new LolApi object is created
        PubgApi pubgApi = ServiceGeneratorPubg.getPubgApi();

        //We use lolApi's api link to use it to fetch data
        Call<PubgStatsResponse> call = pubgApi.getStats(accountId,"false");


        //Fetch data
        call.enqueue(new Callback<PubgStatsResponse>() {
            @Override
            public void onResponse(Call<PubgStatsResponse> call, Response<PubgStatsResponse> response) {
                //If data is received, setValue for searchedAccount to the json response
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
}
