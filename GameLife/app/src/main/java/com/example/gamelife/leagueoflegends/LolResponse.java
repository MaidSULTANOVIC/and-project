package com.example.gamelife.leagueoflegends;

import com.example.gamelife.leagueoflegends.models.Summoner;

/**
 * LolResponse contains data for the user summonerStat
 */
public class LolResponse {

    private int wins;
    private int losses;
    private String tier;
    private String rank;
    private int leaguePoints;

    public Summoner getSummoner(){
        return new Summoner(wins,losses,tier,rank, leaguePoints);
    }




}
