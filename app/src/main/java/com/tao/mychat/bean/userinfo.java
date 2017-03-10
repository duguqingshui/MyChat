package com.tao.mychat.bean;

/**
 * Created by AMOBBS on 2017/3/3.
 */

public class userinfo {
    private String user_id;
    private String user_head_img;
    private String user_name;
    private String account;
    private int user_sex;//0表示 男 1 表示女
    private int user_age;
    private String user_birthday;

    public userinfo(String user_id, String user_head_img, String user_name, String account, int user_sex, int user_age, String user_birthday) {
        this.user_id = user_id;
        this.user_head_img = user_head_img;
        this.user_name = user_name;
        this.account = account;
        this.user_sex = user_sex;
        this.user_age = user_age;
        this.user_birthday = user_birthday;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_head_img() {
        return user_head_img;
    }

    public void setUser_head_img(String user_head_img) {
        this.user_head_img = user_head_img;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public int getUser_sex() {
        return user_sex;
    }

    public void setUser_sex(int user_sex) {
        this.user_sex = user_sex;
    }

    public int getUser_age() {
        return user_age;
    }

    public void setUser_age(int user_age) {
        this.user_age = user_age;
    }

    public String getUser_birthday() {
        return user_birthday;
    }

    public void setUser_birthday(String user_birthday) {
        this.user_birthday = user_birthday;
    }
}
