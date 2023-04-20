
package com.travelease.nitant.ui.home;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResultCName {

    @SerializedName("CityName")
    @Expose
    private List<CityName> cityName;

    public List<CityName> getCityName() {
        return cityName;
    }

    public void setCityName(List<CityName> cityName) {
        this.cityName = cityName;
    }

}
