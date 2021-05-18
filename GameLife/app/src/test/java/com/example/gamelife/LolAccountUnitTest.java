package com.example.gamelife;

import com.example.gamelife.leagueoflegends.models.LolAccount;
import com.example.gamelife.pubg.PubgGameAdapter;
import com.example.gamelife.pubg.models.PubgGame;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class LolAccountUnitTest {

    private LolAccount account;

    @Before
    public void setUp(){
        account = new LolAccount("myId","accountId");
    }

    @Test
    public void initObject() {
        assertEquals("myId", account.getmId());
        assertEquals("accountId",account.getmAccountId());
    }

}
