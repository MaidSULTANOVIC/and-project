package com.example.gamelife.pubg;

import com.example.gamelife.pubg.models.gameData.PubgMatchData;
import com.example.gamelife.pubg.models.gameData.PubgMatchInclude;
import com.example.gamelife.pubg.models.gameData.PubgMatchInfo;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class PubgMatchResponse {
    private ArrayList<PubgMatchInclude> included;
    private PubgMatchInfo data;

    public PubgMatchData getMatchData(){
        return new PubgMatchData(data.getAttributes(), included);
    }
}
