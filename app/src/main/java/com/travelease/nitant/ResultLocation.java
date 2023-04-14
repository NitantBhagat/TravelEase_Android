
package com.travelease.nitant;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultLocation {

    @SerializedName("Location")
    @Expose
    private List<Location> location;

    public List<Location> getLocation() {
        return location;
    }

    public void setLocation(List<Location> location) {
        this.location = location;
    }

}
