package com.example.gamelife.leagueoflegends;

import java.util.ArrayList;
import java.util.List;

public class MatchDetailResponse {
    private String gameMode;
    private ArrayList<Participant> participants;
    private ArrayList<ParticipantIdentities> participantIdentities;

    public MatchDetail getMatchDetail(){
        return new MatchDetail(gameMode,participants,participantIdentities);
    }



   // private List<List<Team>> teams;


}
