package com.leychina.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by yuandunlong on 11/5/15.
 */
public class Payback  implements Serializable{
    @Expose
    private long id ;

    @Expose
    @SerializedName("project_id")
    private long projectId;

    @Expose
    private int limit;

    @Expose
    private String title;

    @Expose
    private String detail;

    @Expose
    @SerializedName("cover_image_thumbnail")
    private String coverImageThumbnail;

    @Expose
    @SerializedName("cover_image")
    private String coverImage;

    @Expose
    @SerializedName("money")
    private float money;

    @Expose
    @SerializedName("payback_after_days")
    private int paybackAfterDays;

    @Expose
    @SerializedName("delivery_money")
    private float deliveryMoney;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getCoverImageThumbnail() {
        return coverImageThumbnail;
    }

    public void setCoverImageThumbnail(String coverImageThumbnail) {
        this.coverImageThumbnail = coverImageThumbnail;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public int getPaybackAfterDays() {
        return paybackAfterDays;
    }

    public void setPaybackAfterDays(int paybackAfterDays) {
        this.paybackAfterDays = paybackAfterDays;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public float getDeliveryMoney() {
        return deliveryMoney;
    }

    public void setDeliveryMoney(float deliveryMoney) {
        this.deliveryMoney = deliveryMoney;
    }
}
