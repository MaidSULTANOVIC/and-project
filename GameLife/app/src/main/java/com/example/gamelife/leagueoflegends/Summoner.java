package com.example.gamelife.leagueoflegends;

public class Summoner {

    private int mWins;
    private int mLosses;
    private String mTier;
    private String mRank;
    private int mLeaguePoints;

    public Summoner(int wins, int losses, String tier, String rank, int lp){
        mWins = wins;
        mLosses = losses;
        mTier= tier;
        mRank = rank;
        mLeaguePoints = lp;
    }

    public String getmTier() {
        return mTier;
    }

    public int getmLeaguePoints() {
        return mLeaguePoints;
    }

    public String getmRank() {
        return mRank;
    }

    public int getmWins() {
        return mWins;
    }

    public int getmLosses() {
        return mLosses;
    }

    public int getWinRatio(){
        return (mWins/(mWins+mLosses))*100;
    }
}
