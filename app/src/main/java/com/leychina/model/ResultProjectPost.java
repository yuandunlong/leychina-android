package com.leychina.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by yuandunlong on 11/21/15.
 */
public class ResultProjectPost extends Result {
    @Expose
    @SerializedName("project_posts")
    private List<ProjectPost> projectPosts;

    public List<ProjectPost> getProjectPosts() {
        return projectPosts;
    }

    public void setProjectPosts(List<ProjectPost> projectPosts) {
        this.projectPosts = projectPosts;
    }
}
