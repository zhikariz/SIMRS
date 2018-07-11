package com.codelab.helmi.simrs.info;

import com.google.gson.annotations.SerializedName;

public class GantiPasswordLoginResponseModel {
    @SerializedName("result")
    String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
