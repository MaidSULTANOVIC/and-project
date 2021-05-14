package com.example.gamelife.pubg.models.gameData;

public class PubgMatchPlayerStats {
    private String name;
    private double damageDealt;
    private int assists;
    private double rideDistance;
    private double walkDistance;
    private int winPlace;
    private int kills;

    public String getName() {
        return name;
    }

    public double getDamageDealt() {
        return damageDealt;
    }

    public int getAssists() {
        return assists;
    }

    public double getRideDistance() {
        return rideDistance;
    }

    public double getWalkDistance() {
        return walkDistance;
    }

    public int getWinPlace() {
        return winPlace;
    }

    public int getKills() {
        return kills;
    }
}
