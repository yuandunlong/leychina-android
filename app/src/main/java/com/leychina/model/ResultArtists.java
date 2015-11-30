package com.leychina.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by yuandunlong on 11/21/15.
 */
public class ResultArtists extends Result {


    @Expose
    @SerializedName("artist")
    private List<Artist> artists;


    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }
}
