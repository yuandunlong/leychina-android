package com.leychina.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by yuandunlong on 10/26/15.
 */
public class Project implements Parcelable{


    @Expose
    private long id;
    @Expose
    private String title;
    @Expose
    @SerializedName("cover_image")
    private String coverImage;
    @Expose
    private String summary;
    @Expose
    @SerializedName("total_money")
    private float totalMoney;
    @Expose
    private String description;
    @Expose
    @SerializedName("support_times")
    private int supportTiems;
    @Expose
    @SerializedName("created_time")
    private String createdTime;

    @Expose
    @SerializedName("updated_time")
    private String updatedTime;
    @Expose
    @SerializedName("current_money")
    private float currentMoney;

    protected Project(Parcel in) {
        id = in.readLong();
        title = in.readString();
        coverImage = in.readString();
        summary = in.readString();
        totalMoney = in.readFloat();
        description = in.readString();
        supportTiems = in.readInt();
        createdTime = in.readString();
        updatedTime = in.readString();
        currentMoney = in.readFloat();
    }

    public static final Creator<Project> CREATOR = new Creator<Project>() {
        @Override
        public Project createFromParcel(Parcel in) {
            return new Project(in);
        }

        @Override
        public Project[] newArray(int size) {
            return new Project[size];
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public float getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(float totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSupportTiems() {
        return supportTiems;
    }

    public void setSupportTiems(int supportTiems) {
        this.supportTiems = supportTiems;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(String updatedTime) {
        this.updatedTime = updatedTime;
    }

    public float getCurrentMoney() {
        return currentMoney;
    }

    public void setCurrentMoney(float currentMoney) {
        this.currentMoney = currentMoney;
    }

    public int getComplishRate(){
        if(this.totalMoney>0){
            return (int) (this.currentMoney*100/this.totalMoney);
        }else{
            return 0;
        }

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(title);
        dest.writeString(coverImage);
        dest.writeString(summary);
        dest.writeFloat(totalMoney);
        dest.writeString(description);
        dest.writeInt(supportTiems);
        dest.writeString(createdTime);
        dest.writeString(updatedTime);
        dest.writeFloat(currentMoney);
    }
}
