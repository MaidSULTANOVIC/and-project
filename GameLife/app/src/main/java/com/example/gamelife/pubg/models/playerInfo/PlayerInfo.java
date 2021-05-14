package com.example.gamelife.pubg.models.playerInfo;

import java.util.ArrayList;

public class PlayerInfo {
    private ArrayList<MatchPubg> mGameList;
    private String mPlayerId;

    public PlayerInfo(String id, ArrayList<MatchPubg> games){
        mGameList = games;
        mPlayerId = id;
    }

    public ArrayList<MatchPubg> getGameList() {
        return mGameList;
    }

    public String getPlayerId() {
        return mPlayerId;
    }
}
