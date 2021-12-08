package com.risquna.risqunaridho.petugas;



import java.util.List;

public class ResponseModel {

    private int kode;
    private String pesan;
    private List<DataPetugas> data;

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

    public List<DataPetugas> getData() {
        return data;
    }

    public void setData(List<DataPetugas> data) {
        this.data = data;
    }
}


