package com.example.gamelife.pubg;

import com.example.gamelife.pubg.models.playerStats.PubgGameModeStats;
import com.example.gamelife.pubg.models.playerStats.PubgPlayerStats;
import com.example.gamelife.pubg.models.playerStats.PubgStatData;

public class PubgStatsResponse {
    private PubgStatData data;

    public PubgPlayerStats getPlayerStats(){
        PubgGameModeStats gm = data.getAttributes().getGameModeStats();

        return new PubgPlayerStats(gm.getDuo(), gm.getSolo(),gm.getSquad());
    }

}
