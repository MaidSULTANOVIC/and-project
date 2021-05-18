package com.example.gamelife.pubg.models.playerInfo;

import java.util.ArrayList;

/**
 * PlayerInfo contains primary data of the user account
 */
public class PlayerInfo {
    private ArrayList<MatchPubg> mGameList;
    private String mPlayerId;

    /**
     * @param id user id
     * @param games user last played games
     */
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
