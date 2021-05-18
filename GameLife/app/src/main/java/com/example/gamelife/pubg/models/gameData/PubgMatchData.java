package com.example.gamelife.pubg.models.gameData;

import java.util.ArrayList;

/**
 * PubgMatchData contains every data for one specific game in Pubg
 */
public class PubgMatchData {
    private String mGameMode;
    private String mMatchType;
    private int mDuration;
    private ArrayList<PubgMatchInclude>  mPlayerList;


    /**
     * @param match the match Attributes
     * @param playerList the list of players in this game
     */
    public PubgMatchData(PubgMatchAttributes match, ArrayList<PubgMatchInclude>  playerList){
        mGameMode = match.getGameMode();
        mMatchType = match.getMatchType();
        mDuration = match.getDuration();

        mPlayerList = playerList;
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

    public ArrayList<PubgMatchInclude>  getPlayerList() {
        return mPlayerList;
    }
}
