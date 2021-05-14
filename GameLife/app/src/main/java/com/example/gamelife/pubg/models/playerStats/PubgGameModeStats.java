package com.example.gamelife.pubg.models.playerStats;

public class PubgGameModeStats {
    private PubgGameMode duo;
    private PubgGameMode solo;
    private PubgGameMode squad;

    public PubgGameMode getDuo() {
        return duo;
    }

    public PubgGameMode getSolo() {
        return solo;
    }

    public PubgGameMode getSquad() {
        return squad;
    }
}
