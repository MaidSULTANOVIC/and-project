package com.example.gamelife.pubg.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.gamelife.pubg.PubgRepository;
import com.example.gamelife.pubg.models.playerInfo.PlayerInfo;
import com.example.gamelife.pubg.models.playerStats.PubgPlayerStats;

public class PubgViewModel extends ViewModel {
    PubgRepository repository;

    //Create a new instance of lolRepo
    public PubgViewModel(){
        repository = PubgRepository.getInstance();
    }

    public LiveData<PlayerInfo> getSearchedAccount(){
        return repository.getSearchedAccount();
    }

    public LiveData<PubgPlayerStats> getSearchedPlayerStats(){return repository.getSearchedPlayerStats();}
    public void searchForAccount(String name){
        repository.searchForAccount(name);
    }

    public void searchForPlayerStats(String accountId){
        repository.searchForPlayerStats(accountId);
    }
}
