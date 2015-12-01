package com.leychina.model;

import com.google.gson.annotations.Expose;

/**
 * Created by ydl on 15-11-30.
 */
public class ResultOrder extends Result {
    @Expose
    private Order order;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
