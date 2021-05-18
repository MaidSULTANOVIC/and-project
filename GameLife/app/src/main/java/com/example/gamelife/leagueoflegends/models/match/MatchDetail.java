package com.example.gamelife.leagueoflegends.models.match;

import com.example.gamelife.leagueoflegends.models.Participant;
import com.example.gamelife.leagueoflegends.models.ParticipantIdentities;

import java.util.ArrayList;

/**
 * MatchDetail is used to contain a match full detail, including the participants and their identities
 * @param gameMode gameMode of the game
 * @param participants a list of the players in the game
 * @param participantIdentities a list of the identities of the players
 */
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
