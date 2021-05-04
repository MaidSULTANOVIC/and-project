package com.example.gamelife;

public class Summoner {

    private int mWins;
    private int mLosses;
    private String mTier;
    private String mRank;
    private int mLeaguePoints;

    Summoner(int wins, int losses, String tier, String rank, int lp){
        mWins = wins;
        mLosses = losses;
        mTier= tier;
        mRank = rank;
        mLeaguePoints = lp;
    }
}
