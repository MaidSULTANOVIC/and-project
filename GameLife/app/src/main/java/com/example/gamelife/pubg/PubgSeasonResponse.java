package com.example.gamelife.pubg;

import com.example.gamelife.pubg.models.season.PubgSeason;

import java.util.ArrayList;

public class PubgSeasonResponse {
    private ArrayList<PubgSeason> data;

    public PubgSeason getPubgSeason(){
        return new PubgSeason(data.get(data.size()-1).getId());
    }

}
