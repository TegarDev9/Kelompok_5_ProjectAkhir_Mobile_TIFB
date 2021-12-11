package com.risquna.risqunaridho.produk;

import java.util.List;

public class ResponseModel {
    private int code;
    private String status;
    private List<DataProduk> produk_list;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<DataProduk> getProduk_list() {
        return produk_list;
    }

    public void setProduk_list(List<DataProduk> produk_list) {
        this.produk_list = produk_list;
    }
}
