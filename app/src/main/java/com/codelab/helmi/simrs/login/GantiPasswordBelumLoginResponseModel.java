package com.codelab.helmi.simrs.login;

import com.google.gson.annotations.SerializedName;

public class GantiPasswordBelumLoginResponseModel {
    @SerializedName("result")
    String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
