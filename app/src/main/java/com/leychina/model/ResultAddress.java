package com.leychina.model;

import com.google.gson.annotations.Expose;

/**
 * Created by yuandunlong on 11/30/15.
 */
public class ResultAddress extends Result {

    @Expose
    private Address address;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
