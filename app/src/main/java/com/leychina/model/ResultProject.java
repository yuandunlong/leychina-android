package com.leychina.model;

import com.google.gson.annotations.Expose;

/**
 * Created by yuandunlong on 11/28/15.
 */
public class ResultProject extends Result{
    @Expose
    Project project;

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
