package com.risquna.risqunaridho.produk;

public class DataProduk {
    private int idproduk, idkategori, rating, hargabefore, hargaafter, stok;
    private String namaproduk, gambar, deskripsi, tgl;

    public int getIdproduk() {
        return idproduk;
    }

    public void setIdproduk(int idproduk) {
        this.idproduk = idproduk;
    }

    public int getIdkategori() {
        return idkategori;
    }

    public void setIdkategori(int idkategori) {
        this.idkategori = idkategori;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getHargabefore() {
        return hargabefore;
    }

    public void setHargabefore(int hargabefore) {
        this.hargabefore = hargabefore;
    }

    public int getHargaafter() {
        return hargaafter;
    }

    public void setHargaafter(int hargaafter) {
        this.hargaafter = hargaafter;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    public String getNamaproduk() {
        return namaproduk;
    }

    public void setNamaproduk(String namaproduk) {
        this.namaproduk = namaproduk;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getTgl() {
        return tgl;
    }

    public void setTgl(String tgl) {
        this.tgl = tgl;
    }
}
