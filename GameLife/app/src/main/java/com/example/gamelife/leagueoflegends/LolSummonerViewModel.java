package com.example.gamelife.leagueoflegends;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class LolSummonerViewModel extends ViewModel
{
    LolRepository repository;

    public LolSummonerViewModel(){
        repository = LolRepository.getInstance();
    }

    LiveData<Summoner> getSearchedSummoner(){
        return repository.getSearchedSummoner();
    }

    public void searchForSummoner(String id){
        repository.searchForSummoner(id);
    }

    LiveData<MatchList> getSearchedMatchList(){
        return repository.getSearchedMatchList();
    }

    public void searchForMatchList(String summonerId){
        repository.searchForMatchList(summonerId);
    }

    LiveData<MatchDetail> getSearchedMatchDetail(){
        return repository.getSearchedMatchDetail();
    }

    public void searchForMatchDetail(long matchId){
        repository.searchForMatchDetail(matchId);
    }

}
