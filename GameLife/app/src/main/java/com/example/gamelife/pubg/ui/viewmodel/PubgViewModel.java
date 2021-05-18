package com.example.gamelife.pubg.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.gamelife.pubg.PubgRepository;
import com.example.gamelife.pubg.models.gameData.PubgMatchData;
import com.example.gamelife.pubg.models.playerInfo.PlayerInfo;
import com.example.gamelife.pubg.models.playerStats.PubgPlayerStats;
import com.example.gamelife.pubg.models.ranked.PubgRanked;
import com.example.gamelife.pubg.models.season.PubgSeason;

public class PubgViewModel extends ViewModel {
    PubgRepository repository;

    //Create a new instance of PubgRepo
    public PubgViewModel(){
        repository = PubgRepository.getInstance();
    }

    public LiveData<PlayerInfo> getSearchedAccount(){
        return repository.getSearchedAccount();
    }

    public LiveData<PubgPlayerStats> getSearchedPlayerStats(){return repository.getSearchedPlayerStats();}

    public LiveData<PubgMatchData> getSearchedMatchData(){return repository.getSearchedMatchData();}

    public LiveData<PubgSeason> getSearchedSeason(){return repository.getSearchedSeason();}

    public LiveData<PubgRanked> getSearchedRanked(){return repository.getSearchedRanked();}

    public void searchForAccount(String name){
        repository.searchForAccount(name);
    }

    public void searchForPlayerStats(String accountId){
        repository.searchForPlayerStats(accountId);
    }
    public void searchForMatchData(String matchId){
        repository.searchForMatchData(matchId);
    }

    public void searchForSeason(){
        repository.searchForSeason();
    }

    public void searchForRanked(String accountId,String seasonId){
        repository.searchForRanked(accountId, seasonId);
    }
}
