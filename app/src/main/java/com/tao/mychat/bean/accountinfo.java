package com.tao.mychat.bean;

/**
 * Created by AMOBBS on 2017/3/3.
 */

public class accountinfo {
    private String account;
    private String password;

    public accountinfo(String account, String password) {
        this.account = account;
        this.password = password;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
