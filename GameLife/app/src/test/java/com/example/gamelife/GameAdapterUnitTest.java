package com.example.gamelife;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class GameAdapterUnitTest {

    private GameAdapter adapter;
    private ArrayList<Game> gameList;

    @Before
    public void setUp(){
        gameList = new ArrayList<Game>();
        adapter = new GameAdapter(gameList,null);
    }

    @Test
    public void initObject() {
        assertEquals(0, adapter.getItemCount());
    }

    @Test
    public void addGame(){
        adapter.addGame(new Game("Game1"));
        assertEquals(1, adapter.getItemCount());
        assertEquals("Game1", adapter.getGame(0).getName());
    }
}
