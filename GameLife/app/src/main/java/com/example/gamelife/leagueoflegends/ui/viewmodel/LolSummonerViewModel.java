package com.example.gamelife.leagueoflegends.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.gamelife.leagueoflegends.models.LolAccount;
import com.example.gamelife.leagueoflegends.LolRepository;
import com.example.gamelife.leagueoflegends.models.match.MatchDetail;
import com.example.gamelife.leagueoflegends.models.match.MatchList;
import com.example.gamelife.leagueoflegends.models.Summoner;

/**
 * LolViewModel
 */
public class LolSummonerViewModel extends ViewModel
{
    LolRepository repository;

    //Create a new instance of lolRepo
    public LolSummonerViewModel(){
        repository = LolRepository.getInstance();
    }

    public LiveData<Summoner> getSearchedSummoner(){
        return repository.getSearchedSummoner();
    }

    public void searchForSummoner(String id){
        repository.searchForSummoner(id);
    }

    public LiveData<MatchList> getSearchedMatchList(){
        return repository.getSearchedMatchList();
    }

    public void searchForMatchList(String summonerId){
        repository.searchForMatchList(summonerId);
    }

    public LiveData<MatchDetail> getSearchedMatchDetail(){
        return repository.getSearchedMatchDetail();
    }

    public void searchForMatchDetail(long matchId){
        repository.searchForMatchDetail(matchId);
    }

    public LiveData<LolAccount> getSearchedLolAccount(){
        return repository.getSearchedAccount();
    }

    public void searchForLolAccount(String name){
        repository.searchForAccount(name);
    }

}
