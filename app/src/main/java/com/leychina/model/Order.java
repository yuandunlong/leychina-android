package com.leychina.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by ydl on 15-11-30.
 */
public class Order implements Serializable {
    @Expose
    private long id;
    @Expose
    @SerializedName("order_no")
    private String orderNo;
    @Expose
    @SerializedName("address_id")
    private long addressId;
    @Expose
    @SerializedName("payback_id")
    private long paybakId;
    @Expose
    private int amount;
    @Expose
    private int status;
    @Expose
    @SerializedName("payback_money")
    private float paybackMoney;
    @Expose
    @SerializedName("total_money")
    private float totalMoney;
    @Expose
    @SerializedName("delivery_money")
    private float deliveryMoney;

    public float getDeliveryMoney() {
        return deliveryMoney;
    }

    public void setDeliveryMoney(float deliveryMoney) {
        this.deliveryMoney = deliveryMoney;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public long getAddressId() {
        return addressId;
    }

    public void setAddressId(long addressId) {
        this.addressId = addressId;
    }

    public long getPaybakId() {
        return paybakId;
    }

    public void setPaybakId(long paybakId) {
        this.paybakId = paybakId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public float getPaybackMoney() {
        return paybackMoney;
    }

    public void setPaybackMoney(float paybackMoney) {
        this.paybackMoney = paybackMoney;
    }

    public float getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(float totalMoney) {
        this.totalMoney = totalMoney;
    }
}
