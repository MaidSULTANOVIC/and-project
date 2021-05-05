package com.example.gamelife.leagueoflegends;

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
