package com.example.gamelife.leagueoflegends;

import com.google.common.math.Stats;

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
