package com.codelab.helmi.simrs.login;

import com.google.gson.annotations.SerializedName;

public class LoginModel {
    @SerializedName("no_rm")
    private String no_rm;
    @SerializedName("password")
    private String password;

    public LoginModel(String friendId) {
        this.no_rm = friendId;
    }


    public String getNo_rm() {
        return no_rm;
    }

    public void setNo_rm(String no_rm) {
        this.no_rm = no_rm;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
