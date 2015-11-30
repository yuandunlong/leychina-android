package com.leychina.model;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by yuandunlong on 11/5/15.
 */
public class ResultPaybacks extends Result {

    @Expose
    List<Payback> paybacks;

    public List<Payback> getPaybacks() {
        return paybacks;
    }

    public void setPaybacks(List<Payback> paybacks) {
        this.paybacks = paybacks;
    }
}
