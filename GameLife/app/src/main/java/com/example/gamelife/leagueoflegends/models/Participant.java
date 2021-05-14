package com.example.gamelife.leagueoflegends.models;

import com.example.gamelife.leagueoflegends.models.LolStat;

/**
 * Participant contains the data for a specific player in a game
 */
public class Participant {
    private int teamId;
    private int participantId;
    private int championId;
    private LolStat stats;


    public int getTeamId() {
        return teamId;
    }

    public int getParticipantId() {
        return participantId;
    }

    public int getChampionId() {
        return championId;
    }


    public LolStat getStats() {
        return stats;
    }
}
