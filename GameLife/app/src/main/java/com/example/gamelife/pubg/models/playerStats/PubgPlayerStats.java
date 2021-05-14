package com.example.gamelife.pubg.models.playerStats;

public class PubgPlayerStats {
    private PubgGameMode mDuo;
    private PubgGameMode mSolo;
    private PubgGameMode mSquad;

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
