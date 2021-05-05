package com.example.gamelife.leagueoflegends;

public class AccountResponse {
    private String id;
    private String accountId;

    public LolAccount getAccount(){
        return new LolAccount(id, accountId);
    }

}
