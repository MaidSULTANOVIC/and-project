package com.example.gamelife.leagueoflegends;

import java.util.ArrayList;
import java.util.List;

public class MatchList {

    private ArrayList<MatchInfo> mMatches;

    public MatchList(ArrayList<MatchInfo> matches){
        mMatches = matches;
    }


    public ArrayList<MatchInfo> getmMatches() {
        return mMatches;
    }
}
