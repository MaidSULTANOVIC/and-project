package com.example.gamelife.pubg.models.ranked;


/**
 * PubgRanked contains ranked stats and data of the user
 */
public class PubgRanked {
    private String mTier;
    private String mSubTier;
    private PubgRankedSquad mSquad;

    /**
     * PubgRanked constructor
     * @param tier Tier of the rank
     * @param subTier Subtier of the rank
     * @param squad rankedSquad stats
     */
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
