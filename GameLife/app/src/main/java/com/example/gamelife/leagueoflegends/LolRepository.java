package com.example.gamelife.leagueoflegends;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.gamelife.ServiceGenerator;
import com.example.gamelife.leagueoflegends.models.LolAccount;
import com.example.gamelife.leagueoflegends.models.MatchDetail;
import com.example.gamelife.leagueoflegends.models.MatchList;
import com.example.gamelife.leagueoflegends.models.Summoner;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * This class is used to use LolApi in order to fetch data from the api and store it inside MutableLiveData
 */
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

    //The call to search an account given the username
    public void searchForAccount(String name){
        //A new LolApi object is created
        LolApi lolApi = ServiceGenerator.getLolApi();

        //We use lolApi's api link to use it to fetch data
        Call<AccountResponse> call = lolApi.getAccount(name);

        //Fetch data
        call.enqueue(new Callback<AccountResponse>() {
            @Override
            public void onResponse(Call<AccountResponse> call, Response<AccountResponse> response) {
                //If data is received, setValue for searchedAccount to the json response
                if (response.code() == 200) {
                    searchedAccount.setValue(response.body().getAccount());

                }
            }
            @Override
            public void onFailure(Call<AccountResponse> call, Throwable t) {
                Log.i("Retrofit", "Something went wrong :(" + t.getMessage());
            }
        });



    }

    //Call to search a summoner given its id
    public void searchForSummoner(String id){
        LolApi lolApi = ServiceGenerator.getLolApi();
        Call<List<LolResponse>> call = lolApi.getSummoner(id);

        call.enqueue(new Callback<List<LolResponse>>() {
            @Override
            public void onResponse(Call<List<LolResponse>> call, Response<List<LolResponse>> response) {
                if (response.code() == 200) {
                    searchedSummoner.setValue(response.body().get(0).getSummoner());

                }
            }
            @Override
            public void onFailure(Call<List<LolResponse>> call, Throwable t) {
                Log.i("Retrofit", "Something went wrong :(" + t.getMessage());
            }
        });



    }

    //Call to search the list of last played game given the summonerId
    public void searchForMatchList(String summonerId){
        LolApi lolApi = ServiceGenerator.getLolApi();
        Call<MatchListResponse> call = lolApi.getMatchList(summonerId);

        call.enqueue(new Callback<MatchListResponse>() {
            @Override
            public void onResponse(Call<MatchListResponse> call, Response<MatchListResponse> response) {
                if (response.code() == 200) {
                    searchedMatchList.setValue(response.body().getMatchList());

                }
            }
            @Override
            public void onFailure(Call<MatchListResponse> call, Throwable t) {
                Log.i("Retrofit", "Something went wrong :(" + t.getMessage());
            }
        });



    }

    //The call to search a match detail for a specific game
    public void searchForMatchDetail(long matchId){
        LolApi lolApi = ServiceGenerator.getLolApi();
        Call<MatchDetailResponse> call = lolApi.getMatchDetail(matchId);

        call.enqueue(new Callback<MatchDetailResponse>() {
            @Override
            public void onResponse(Call<MatchDetailResponse> call, Response<MatchDetailResponse> response) {
                if (response.code() == 200) {
                    searchedMatchDetail.setValue(response.body().getMatchDetail());

                }
            }
            @Override
            public void onFailure(Call<MatchDetailResponse> call, Throwable t) {
                Log.i("Retrofit", "Something went wrong :(" + t.getMessage());
            }
        });



    }
}
