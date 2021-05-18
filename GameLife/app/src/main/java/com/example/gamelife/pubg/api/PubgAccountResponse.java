package com.example.gamelife.pubg.api;

import com.example.gamelife.pubg.models.playerInfo.PlayerData;
import com.example.gamelife.pubg.models.playerInfo.PlayerInfo;

import java.util.ArrayList;

public class PubgAccountResponse {
    private ArrayList<PlayerData> data;

    public PlayerInfo getPubgAccount(){
        return new PlayerInfo(data.get(0).getId(),data.get(0).getRelationships().getMatches().getData());
    }

}
