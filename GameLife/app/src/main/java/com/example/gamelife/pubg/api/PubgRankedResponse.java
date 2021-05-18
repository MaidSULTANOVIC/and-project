package com.example.gamelife.pubg.api;

import com.example.gamelife.pubg.models.ranked.PubgRanked;
import com.example.gamelife.pubg.models.ranked.PubgRankedData;
import com.example.gamelife.pubg.models.ranked.PubgRankedSquad;
import com.example.gamelife.pubg.models.ranked.PubgSquadCurrentTier;

public class PubgRankedResponse {
    private PubgRankedData data;

    public PubgRanked getPubgRanked(){
        PubgSquadCurrentTier squadTier = data.getAttributes().getRankedGameModeStats().getSquad().getCurrentTier();
        PubgRankedSquad squad = data.getAttributes().getRankedGameModeStats().getSquad();
        return new PubgRanked(squadTier.getTier(),squadTier.getSubTier(), squad);
    }
}
