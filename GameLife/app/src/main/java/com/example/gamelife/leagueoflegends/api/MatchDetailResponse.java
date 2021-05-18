package com.example.gamelife.leagueoflegends.api;

import com.example.gamelife.leagueoflegends.models.match.MatchDetail;
import com.example.gamelife.leagueoflegends.models.Participant;
import com.example.gamelife.leagueoflegends.models.ParticipantIdentities;

import java.util.ArrayList;


// This class is used to store the response from the fetch, it will contain a match detail
public class MatchDetailResponse {
    private String gameMode;
    private ArrayList<Participant> participants;
    private ArrayList<ParticipantIdentities> participantIdentities;

    public MatchDetail getMatchDetail(){
        return new MatchDetail(gameMode,participants,participantIdentities);
    }

}
