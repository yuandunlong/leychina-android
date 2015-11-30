package com.leychina.model;

import com.google.gson.annotations.Expose;

/**
 * Created by yuandunlong on 10/26/15.
 */
public class Result {
    @Expose
    int code;
    @Expose
    String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


    public boolean isOK(){
        return this.code==0;
    }
}
