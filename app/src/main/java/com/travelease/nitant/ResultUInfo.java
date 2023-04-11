
package com.travelease.nitant;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultUInfo {

    @SerializedName("Registration")
    @Expose
    private List<RegistrationModel> registrationModel;

    public List<RegistrationModel> getRegistration() {
        return registrationModel;
    }

    public void setRegistration(List<RegistrationModel> registrationModel) {
        this.registrationModel = registrationModel;
    }

}
