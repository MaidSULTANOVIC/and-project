package com.example.gamelife.leagueoflegends.models.match;

import com.example.gamelife.leagueoflegends.models.match.MatchInfo;

import java.util.ArrayList;

/**
 * MatchList is a class that contains the list of the last games played by the user
 * @param matches the list of the games played
 */
public class MatchList {

    private ArrayList<MatchInfo> mMatches;

    public MatchList(ArrayList<MatchInfo> matches){
        mMatches = matches;
    }


    public ArrayList<MatchInfo> getmMatches() {
        return mMatches;
    }
}
