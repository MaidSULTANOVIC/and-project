package com.example.gamelife.leagueoflegends.models;

/**
 * Summoner contains every data about the user's account, this includes the number of its losses and wins with his rank and league points.
 * @param wins number of win
 * @param losses number of lose
 * @param tier rank tier of the user
 * @param rank rank of the user
 * @param lp number of league point of the user
 */
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
