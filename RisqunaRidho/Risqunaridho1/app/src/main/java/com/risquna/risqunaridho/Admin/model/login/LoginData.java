package com.risquna.risqunaridho.Admin.model.login;

import com.google.gson.annotations.SerializedName;

public class LoginData {
    @SerializedName("idpetugas")
    private String idpetugas;

    @SerializedName("namapetugas")
    private String namapetugas;

    @SerializedName("email")
    private String email;

    public String getIdpetugas() {
        return idpetugas;
    }

    public void setIdpetugas(String idpetugas) {
        this.idpetugas = idpetugas;
    }

    public String getNamapetugas() {
        return namapetugas;
    }

    public void setNamapetugas(String namapetugas) {
        this.namapetugas = namapetugas;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
