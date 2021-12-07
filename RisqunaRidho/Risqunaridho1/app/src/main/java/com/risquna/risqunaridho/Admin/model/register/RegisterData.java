package com.risquna.risqunaridho.Admin.model.register;

public class RegisterData {
    private int idpetugas, role;
    private String nama, notelp, email, password;

    public int getIdpetugas() {
        return idpetugas;
    }

    public void setIdpetugas(int idpetugas) {
        this.idpetugas = idpetugas;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNotelp() {
        return notelp;
    }

    public void setNotelp(String notelp) {
        this.notelp = notelp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
