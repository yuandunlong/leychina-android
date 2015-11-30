package com.leychina.model;

import com.google.gson.annotations.Expose;

/**
 * Created by yuandunlong on 11/28/15.
 */
public class ResultUser extends Result {

    @Expose
    private  User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
