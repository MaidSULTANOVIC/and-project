package com.example.gamelife.leagueoflegends.models;


/**
 * LolAccount class contains user's account data
 * @param id the summoner's id
 * @param accountId the account's id
 *
 */
public class LolAccount {
    private String mId;
    private String mAccountId;

    public LolAccount(String id, String accountId){
        mId = id;
        mAccountId = accountId;
    }


    public String getmId() {
        return mId;
    }

    public String getmAccountId() {
        return mAccountId;
    }
}