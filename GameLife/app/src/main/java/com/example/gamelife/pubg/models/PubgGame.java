package com.example.gamelife.pubg.models;

import com.example.gamelife.pubg.models.gameData.PubgMatchPlayerStats;

public class PubgGame {
    private String mGameMode;
    private String mMatchType;
    private int mDuration;
    private PubgMatchPlayerStats mPlayer;

    public PubgGame(String gameMode, String type, int duration, PubgMatchPlayerStats player){
        mGameMode = gameMode;
        mMatchType = type;
        mDuration = duration;
        mPlayer = player;
    }

    public String getGameMode() {
        return mGameMode;
    }

    public String getMatchType() {
        return mMatchType;
    }

    public int getDuration() {
        return mDuration;
    }

    public PubgMatchPlayerStats getPlayer() {
        return mPlayer;
    }
}
