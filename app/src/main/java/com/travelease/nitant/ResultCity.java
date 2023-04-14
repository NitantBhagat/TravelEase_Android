
package com.travelease.nitant;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultCity {

    @SerializedName("City")
    @Expose
    private List<City> city;

    public List<City> getCity() {
        return city;
    }

    public void setCity(List<City> city) {
        this.city = city;
    }

}
