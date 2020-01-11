package com.bhagyashree.wipropocproject.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PlaceModel {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("rows")
    @Expose
    private List<DetailModel> rows = null;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<DetailModel> getRows() {
        return rows;
    }

    public void setRows(List<DetailModel> rows) {
        this.rows = rows;
    }

}
