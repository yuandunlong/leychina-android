package com.leychina.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by yuandunlong on 11/5/15.
 */
public class Artist implements Serializable{
    @Expose
    private long id;
    @Expose
    @SerializedName("user_id")
    private long userId;
    @Expose
    private int height;
    @Expose
    private int weight;

    @Expose
    private String photo;
    @Expose
    private String blood;
    @Expose
    private int popularity;
    @Expose
    private String description;

    @Expose
    @SerializedName("real_name")
    private String realName;

    @Expose
    @SerializedName("nick_name")
    private String nickName;

    public Artist(String photo,int pop){
        this.photo=photo;
        this.popularity=pop;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getBlood() {
        return blood;
    }

    public void setBlood(String blood) {
        this.blood = blood;
    }

    public String  getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
