package com.risquna.risqunaridho.Admin.model.register;

import com.google.gson.annotations.SerializedName;
import com.risquna.risqunaridho.Admin.model.login.LoginData;

import java.util.List;

public class Register {
    @SerializedName("code")
    private boolean code;

    @SerializedName("status")
    private String status;

    @SerializedName("user_list")
    private List<LoginData> user_list;

    public void setCode(boolean code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<LoginData> getUser_list() {
        return user_list;
    }

    public void setUser_list(List<LoginData> user_list) {
        this.user_list = user_list;
    }

    public boolean isCode(){
        return code;
    }
}
