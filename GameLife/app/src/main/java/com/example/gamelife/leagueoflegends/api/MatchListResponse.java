package com.example.gamelife.leagueoflegends.api;

import com.example.gamelife.leagueoflegends.models.match.MatchInfo;
import com.example.gamelife.leagueoflegends.models.match.MatchList;

import java.util.ArrayList;

// This class contains the response for api call, it contains the matchlist
public class MatchListResponse {
    private ArrayList<MatchInfo> matches;


    public MatchList getMatchList(){
        return new MatchList(matches);
    }

}
