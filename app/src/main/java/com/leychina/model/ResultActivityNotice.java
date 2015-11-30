package com.leychina.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by yuandunlong on 11/22/15.
 */
public class ResultActivityNotice extends Result {

    @Expose
    @SerializedName("activity_notices")
    private List<ActivityNotice> activityNotices;

    public List<ActivityNotice> getActivityNotices() {
        return activityNotices;
    }

    public void setActivityNotices(List<ActivityNotice> activityNotices) {
        this.activityNotices = activityNotices;
    }
}
