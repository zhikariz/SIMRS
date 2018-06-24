package com.codelab.helmi.simrs.register;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RegisterResponseModel {
    @SerializedName("result")
   String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
