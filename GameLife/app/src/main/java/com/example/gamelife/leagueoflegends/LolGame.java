package com.example.gamelife.leagueoflegends;

public class LolGame {

    public String getType() {
        return mType;
    }

    public String getChampion() {
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

    private String mType;
    private String mChampion;
    private String mCondition;
    private String mKda;
    private String mKdaRatio;

    LolGame(String type, String champion, String condition, String kda, String ratio){
        mType = type;
        mChampion = champion;
        mCondition = condition;
        mKda = kda;
        mKdaRatio = ratio;
    }

}
