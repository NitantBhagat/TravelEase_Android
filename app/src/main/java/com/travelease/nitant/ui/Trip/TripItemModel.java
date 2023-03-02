package com.travelease.nitant.ui.Trip;

import android.content.Context;

public class TripItemModel {
    int id;
    String title,detail;


    public TripItemModel(int id, String title, String detail) {
        this.id = id;
        this.title = title;
        this.detail = detail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

}
