package com.leychina.model;

import com.leychina.manager.PrefManager;

/**
 * Created by yuandunlong on 11/15/15.
 */
public class User {
    private long id;
    private String email;
    private String mobile;
    private String name;

    private String accessToken;

    private int sex;


    private int status;

    private boolean isSignIn;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String acessToken) {
        this.accessToken = acessToken;
    }

    public boolean isSignIn() {
        return isSignIn;
    }

    public void setIsSignIn(boolean isSignIn) {
        this.isSignIn = isSignIn;
    }

    public void loadFromPref(){

      PrefManager pm=  PrefManager.getInstance();
        this.email=pm.getString("email");
        this.accessToken=pm.getString("access_token");
        this.id=pm.getLong("id");
        this.mobile=pm.getString("mobile");
        this.name=pm.getString("name");
        this.sex=pm.getInteger("sex");
        this.isSignIn=pm.getBoolean("is_sign_in");
    }

    public void save2Pref(){
        PrefManager pm=  PrefManager.getInstance();
        pm.putString("email",this.email);
        pm.putString("access_token",this.accessToken);
        pm.putLong("id", this.id);
        pm.putString("mobile", this.mobile);
        pm.putString("name", this.name);
        pm.putInteger("sex", this.sex);
        pm.putBoolean("is_sign_in",this.isSignIn);
    }
}
