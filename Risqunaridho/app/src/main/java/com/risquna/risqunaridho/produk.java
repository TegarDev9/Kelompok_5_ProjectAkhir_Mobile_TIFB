package com.risquna.risqunaridho;

import com.google.gson.annotations.SerializedName;

public class produk {

    @SerializedName("idproduk")
    private int idproduk;
    @SerializedName("namaproduk")
    private String namaproduk;
    @SerializedName("deskripsi")
    private String deskripsi;
    @SerializedName("rating")
    private String rating;
    @SerializedName("stok")
    private String stok;
    @SerializedName("tgl")
    private String tgl;
    @SerializedName("gambar")
    private String gambar;
    @SerializedName("love")
    private Boolean love;
    @SerializedName("value")
    private String value;
    @SerializedName("message")
    private String massage;

    public int getIdproduk() {
        return idproduk;
    }

    public void setIdproduk(int idproduk) {
        this.idproduk = idproduk;
    }

    public String getNamaproduk() {
        return namaproduk;
    }

    public void setNamaproduk(String namaproduk) {
        this.namaproduk = namaproduk;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getstok() {
        return stok;
    }

    public void setstok(String stok) {
        this.stok = stok;
    }

    public String getTgl() {
        return tgl;
    }

    public void setTgl(String tgl) {
        this.tgl = tgl;
    }

    public String getPicture() {
        return gambar;
    }

    public void setPicture(String picture) {
        this.gambar = picture;
    }

    public Boolean getLove() {
        return love;
    }

    public void setLove(Boolean love) {
        this.love = love;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }




}

