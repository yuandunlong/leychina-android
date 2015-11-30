package com.leychina.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by yuandunlong on 10/26/15.
 */
public class ResultProjects extends Result {


    @Expose
    @SerializedName("projects")
    List<Project> projects;

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }
}
