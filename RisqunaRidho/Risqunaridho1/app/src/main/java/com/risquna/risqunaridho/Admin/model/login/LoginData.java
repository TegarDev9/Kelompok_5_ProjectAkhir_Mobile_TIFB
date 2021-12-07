package com.risquna.risqunaridho.Admin.model.login;

import com.google.gson.annotations.SerializedName;

public class LoginData {
    @SerializedName("idpetugas")
    private String idpetugas;

    @SerializedName("nama")
    private String nama;

    @SerializedName("email")
    private String email;

    public String getIdpetugas() {
        return idpetugas;
    }

    public void setIdpetugas(String idpetugas) {
        this.idpetugas = idpetugas;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
