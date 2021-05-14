package com.example.gamelife.pubg.models.ranked;

public class PubgRanked {
    private String mTier;
    private String mSubTier;
    private PubgRankedSquad mSquad;

    public PubgRanked(String tier, String subTier, PubgRankedSquad squad){
        mTier = tier;
        mSubTier = subTier;
        mSquad = squad;
    }

    public String getTier() {
        return mTier;
    }

    public String getSubTier() {
        return mSubTier;
    }

    public PubgRankedSquad getSquad() {
        return mSquad;
    }
}
