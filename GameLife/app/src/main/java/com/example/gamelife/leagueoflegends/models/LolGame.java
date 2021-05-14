package com.example.gamelife.leagueoflegends.models;

/**
 * LolGame is a class that contains every data for a specific game
 * @param type type of game
 * @param champion played champion
 * @param condition win or lose
 * @param kda player's kda
 * @param ratio player's ratio
 */
public class LolGame {
    private String mType;
    private int mChampion;
    private String mCondition;
    private String mKda;
    private String mKdaRatio;

    public LolGame(String type, int champion, String condition, String kda, String ratio){
        mType = type;
        mChampion = champion;
        mCondition = condition;
        mKda = kda;
        mKdaRatio = ratio;
    }

    public String getType() {
        return mType;
    }

    public int getChampion() {
        return mChampion;
    }

    public String getCondition() {
        return mCondition;
    }

    public String getKda() {
        return mKda;
    }

    public String getKdaRatio() {
        return mKdaRatio;
    }

}
