package com.example.gamelife;

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
