package com.codelab.helmi.simrs.pesan;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PesanResponseModel {
    @SerializedName("result")
    String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
