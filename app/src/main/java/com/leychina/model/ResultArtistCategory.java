package com.leychina.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by yuandunlong on 11/22/15.
 */
public class ResultArtistCategory extends Result {
    @Expose
    @SerializedName("art_categories")
    List<ArtistCategory> artCategories;

    public List<ArtistCategory> getArtCategories() {
        return artCategories;
    }

    public void setArtCategories(List<ArtistCategory> artCategories) {
        this.artCategories = artCategories;
    }
}
