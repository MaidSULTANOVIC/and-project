package com.example.gamelife.pubg.api;

import com.example.gamelife.pubg.models.season.PubgSeason;
import com.example.gamelife.pubg.models.season.PubgSeasonData;

import java.util.ArrayList;

public class PubgSeasonResponse {
    private ArrayList<PubgSeasonData> data;

    public PubgSeason getPubgSeason(){
        return new PubgSeason(data.get(data.size()-1).getId());
    }

}
