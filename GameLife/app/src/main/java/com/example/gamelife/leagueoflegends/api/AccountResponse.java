package com.example.gamelife.leagueoflegends.api;

import com.example.gamelife.leagueoflegends.models.LolAccount;

/**
 * AccountResponse contains the account data and return a LolAccount object
 */
public class AccountResponse {
    private String id;
    private String accountId;

    public LolAccount getAccount(){
        return new LolAccount(id, accountId);
    }

}
