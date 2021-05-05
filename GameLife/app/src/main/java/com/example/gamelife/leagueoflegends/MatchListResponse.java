package com.example.gamelife.leagueoflegends;

import java.util.ArrayList;
import java.util.List;

public class MatchListResponse {
    private ArrayList<MatchInfo> matches;


    public MatchList getMatchList(){
        return new MatchList(matches);
    }

}
