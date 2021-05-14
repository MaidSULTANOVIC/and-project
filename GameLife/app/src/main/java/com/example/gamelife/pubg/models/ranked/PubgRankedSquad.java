package com.example.gamelife.pubg.models.ranked;

public class PubgRankedSquad {
    private PubgSquadCurrentTier currentTier;
    private double winRatio;
    private double top10Ratio;
    private double kda;
    private int wins;
    private int currentRankPoint;
    private int kills;


    public PubgSquadCurrentTier getCurrentTier() {
        return currentTier;
    }

    public double getWinRatio() {
        return winRatio;
    }

    public double getTop10Ratio() {
        return top10Ratio;
    }

    public double getKda() {
        return kda;
    }

    public int getWins() {
        return wins;
    }

    public int getCurrentRankPoint() {
        return currentRankPoint;
    }

    public int getKills() {
        return kills;
    }
}
