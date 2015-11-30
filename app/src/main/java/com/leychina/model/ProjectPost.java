package com.leychina.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by yuandunlong on 11/21/15.
 */
public class ProjectPost {

    public static  final int TYPE_PROJECT=2;
    public static final int TYPE_LINK=2;
    @Expose
    @SerializedName("post_type")
    private int postType;

    @Expose
    @SerializedName("image_url")
    private String imageURL;

    @Expose
    @SerializedName("project_id")
    private long projectId;

    @Expose
    private long id;

    @Expose
    private String link;

    public int getPostType() {
        return postType;
    }

    public void setPostType(int postType) {
        this.postType = postType;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public boolean isLink(){
        return postType==1;
    }
    public boolean isProject(){

        return postType==2;
    }
}
