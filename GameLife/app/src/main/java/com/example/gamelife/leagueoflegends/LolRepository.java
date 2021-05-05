package com.example.gamelife.leagueoflegends;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.gamelife.ServiceGenerator;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LolRepository {
    private static LolRepository instance;
    private final MutableLiveData<LolAccount> searchedAccount;
    private final MutableLiveData<Summoner> searchedSummoner;
    private final MutableLiveData<MatchList> searchedMatchList;
    private final MutableLiveData<MatchDetail> searchedMatchDetail;

    private LolRepository(){
        searchedAccount = new MutableLiveData<>();
        searchedSummoner = new MutableLiveData<>();
        searchedMatchList = new MutableLiveData<>();
        searchedMatchDetail = new MutableLiveData<>();
    }

    public static synchronized LolRepository getInstance(){
        if (instance == null){
            instance = new LolRepository();
        }
        return instance;
    }
    public LiveData<LolAccount> getSearchedAccount(){
        return searchedAccount;
    }
    public LiveData<Summoner> getSearchedSummoner(){
        return searchedSummoner;
    }
    public LiveData<MatchList> getSearchedMatchList(){
        return searchedMatchList;
    }
    public LiveData<MatchDetail> getSearchedMatchDetail(){
        return searchedMatchDetail;
    }

    public void searchForAccount(String name){
        LolApi lolApi = ServiceGenerator.getLolApi();
        Call<AccountResponse> call = lolApi.getAccount(name);

        call.enqueue(new Callback<AccountResponse>() {
            @Override
            public void onResponse(Call<AccountResponse> call, Response<AccountResponse> response) {
                if (response.code() == 200) {
                    searchedAccount.setValue(response.body().getAccount());
                    Log.i("Retrofit", "NORMALEMENT C BON");
                }
            }
            @Override
            public void onFailure(Call<AccountResponse> call, Throwable t) {
                Log.i("Retrofit", "Something went wrong :(" + t.getMessage());
            }
        });



    }

    public void searchForSummoner(String id){
        LolApi lolApi = ServiceGenerator.getLolApi();
        Call<List<LolResponse>> call = lolApi.getSummoner(id);

        call.enqueue(new Callback<List<LolResponse>>() {
            @Override
            public void onResponse(Call<List<LolResponse>> call, Response<List<LolResponse>> response) {
                if (response.code() == 200) {
                    searchedSummoner.setValue(response.body().get(0).getSummoner());
                    Log.i("Retrofit", "NORMALEMENT C BON");
                }
            }
            @Override
            public void onFailure(Call<List<LolResponse>> call, Throwable t) {
                Log.i("Retrofit", "Something went wrong :(" + t.getMessage());
            }
        });



    }

    public void searchForMatchList(String summonerId){
        LolApi lolApi = ServiceGenerator.getLolApi();
        Call<MatchListResponse> call = lolApi.getMatchList(summonerId);

        call.enqueue(new Callback<MatchListResponse>() {
            @Override
            public void onResponse(Call<MatchListResponse> call, Response<MatchListResponse> response) {
                if (response.code() == 200) {
                    searchedMatchList.setValue(response.body().getMatchList());
                    Log.i("Retrofit", "NORMALEMENT C BON");
                }
            }
            @Override
            public void onFailure(Call<MatchListResponse> call, Throwable t) {
                Log.i("Retrofit", "Something went wrong :(" + t.getMessage());
            }
        });



    }

    public void searchForMatchDetail(long matchId){
        LolApi lolApi = ServiceGenerator.getLolApi();
        Call<MatchDetailResponse> call = lolApi.getMatchDetail(matchId);

        call.enqueue(new Callback<MatchDetailResponse>() {
            @Override
            public void onResponse(Call<MatchDetailResponse> call, Response<MatchDetailResponse> response) {
                if (response.code() == 200) {
                    searchedMatchDetail.setValue(response.body().getMatchDetail());
                    Log.i("Retrofit", "NORMALEMENT C BON");
                }
            }
            @Override
            public void onFailure(Call<MatchDetailResponse> call, Throwable t) {
                Log.i("Retrofit", "Something went wrong :(" + t.getMessage());
            }
        });



    }
}
