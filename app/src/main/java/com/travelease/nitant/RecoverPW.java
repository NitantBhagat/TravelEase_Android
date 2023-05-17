
package com.travelease.nitant;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RecoverPW {

    @SerializedName("Recover")
    @Expose
    private List<Recover> recover;

    public List<Recover> getRecover() {
        return recover;
    }

    public void setRecover(List<Recover> recover) {
        this.recover = recover;
    }

}
