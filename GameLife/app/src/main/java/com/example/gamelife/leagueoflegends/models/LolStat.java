package com.example.gamelife.leagueoflegends.models;

/**
 * This class contains data concerning the user stats including kills, deaths, assists and the condition of the game
 */
public class LolStat {
    private boolean win;
    private int kills;
    private int deaths;
    private int assists;


    public boolean isWin() {
        return win;
    }

    public int getKills() {
        return kills;
    }

    public int getDeaths() {
        return deaths;
    }

    public int getAssists() {
        return assists;
    }
}
