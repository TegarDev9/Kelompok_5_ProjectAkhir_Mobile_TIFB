package com.risquna.risqunaridho.pemesanan;

import java.util.List;

public class ResponseModel {

    private int kode;
    private String pesan;
    private List<DataPemesanan> data;

    public int getKode() {
        return kode;
    }

    public void setKode(int kode) {
        this.kode = kode;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public List<DataPemesanan> getData() {
        return data;
    }

    public void setData(List<DataPemesanan> data) {
        this.data = data;
    }
}
