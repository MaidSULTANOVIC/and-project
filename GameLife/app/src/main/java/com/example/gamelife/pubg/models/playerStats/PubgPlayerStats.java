package com.example.gamelife.pubg.models.playerStats;


/**
 * PubgPlayerStats contains every data about user in different game mode such as Squad, Duo, Solo
 */
public class PubgPlayerStats {
    private PubgGameMode mDuo;
    private PubgGameMode mSolo;
    private PubgGameMode mSquad;

    /**
     * @param duo Duo game mode data
     * @param solo Solo game mode data
     * @param squad Squad game mode data
     */
    public PubgPlayerStats(PubgGameMode duo, PubgGameMode solo, PubgGameMode squad) {
        mDuo = duo;
        mSolo = solo;
        mSquad = squad;
    }

    public PubgGameMode getDuo() {
        return mDuo;
    }

    public PubgGameMode getSolo() {
        return mSolo;
    }

    public PubgGameMode getSquad() {
        return mSquad;
    }
}
