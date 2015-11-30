package com.leychina.model;

import android.text.Html;
import android.text.Spanned;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by yuandunlong on 11/29/15.
 */
public class Address implements Serializable {
    @Expose
    private long id;
    @Expose
    @SerializedName("recieve_man")
    private String recieveMan;
    @Expose
    private String phone;
    @Expose
    @SerializedName("user_id")
    private long userId;
    @Expose
    private String address;
    @Expose
    @SerializedName("default")
    private int _default;

    public String getRecieveMan() {
        return recieveMan;
    }

    public void setRecieveMan(String recieveMan) {
        this.recieveMan = recieveMan;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int get_default() {
        return _default;
    }

    public void set_default(int _default) {
        this._default = _default;
    }

    public Spanned getDisplayText(){
        String template="<br>\t收货人:%s\t\t\t\t\t\t\t%s<br><br><br>\t收获地址:%s";

        return Html.fromHtml(String.format(template,this.recieveMan,this.phone,this.address));
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
