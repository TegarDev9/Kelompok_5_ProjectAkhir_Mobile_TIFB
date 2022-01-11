package com.risquna.risqunaridho.transaksi;

public class DataTransaksi {
    private int idcart, totalbelanja,userid;
    private String kodeorder,nama, tglbelanja, status;

    public int getIdcart() {
        return idcart;
    }

    public void setIdcart(int idcart) {
        this.idcart = idcart;
    }

    public int getTotalbelanja() {
        return totalbelanja;
    }

    public void setTotalbelanja(int totalbelanja) {
        this.totalbelanja = totalbelanja;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getKodeorder() {
        return kodeorder;
    }

    public void setKodeorder(String kodeorder) {
        this.kodeorder = kodeorder;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTglbelanja() {
        return tglbelanja;
    }

    public void setTglbelanja(String tglbelanja) {
        this.tglbelanja = tglbelanja;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
