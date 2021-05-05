package com.example.gamelife.leagueoflegends;

import java.util.ArrayList;

public class MatchDetail {

    private String mGameMode;
    private ArrayList<Participant> mParticipants;
    private ArrayList<ParticipantIdentities> mParticipantIdentities;

    public MatchDetail(String gameMode, ArrayList<Participant> participants, ArrayList<ParticipantIdentities> participantIdentities){
        mGameMode = gameMode;
        mParticipants = participants;
        mParticipantIdentities = participantIdentities;
    }

    public String getmGameMode() {
        return mGameMode;
    }

    public ArrayList<Participant> getmParticipants() {
        return mParticipants;
    }

    public ArrayList<ParticipantIdentities> getmParticipantIdentities() {
        return mParticipantIdentities;
    }
}
