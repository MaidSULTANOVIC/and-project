package com.example.gamelife;

import com.example.gamelife.pubg.PubgGameAdapter;
import com.example.gamelife.pubg.models.PubgGame;
import com.example.gamelife.pubg.models.gameData.PubgMatchPlayerStats;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class PubgAdapterUnitTest {

    private PubgGameAdapter adapter;
    private ArrayList<PubgGame> gameList;

    @Before
    public void setUp(){
        gameList = new ArrayList<PubgGame>();
        adapter = new PubgGameAdapter(gameList);
    }

    @Test
    public void initObject() {
        assertEquals(0, adapter.getItemCount());
    }

    @Test
    public void addGame(){
        adapter.addGame(new PubgGame("gameMode","official",555,null));
        assertEquals(1,adapter.getItemCount());
    }
    @Test
    public void clearList(){
        adapter.addGame(new PubgGame("gameMode","official",555,new PubgMatchPlayerStats()));
        adapter.addGame(new PubgGame("gameMode","official",5255,new PubgMatchPlayerStats()));
        adapter.clearGames();
        assertEquals(0,adapter.getItemCount());
    }

}
