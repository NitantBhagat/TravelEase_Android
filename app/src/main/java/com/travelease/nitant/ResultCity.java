
package com.travelease.nitant;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultCity {

    @SerializedName("City")
    @Expose
    private List<CityModel> city;

    public List<CityModel> getCity() {
        return city;
    }

    public void setCity(List<CityModel> city) {
        this.city = city;
    }

}
