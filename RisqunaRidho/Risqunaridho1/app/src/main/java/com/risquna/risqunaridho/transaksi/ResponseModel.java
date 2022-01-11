package com.risquna.risqunaridho.transaksi;

import java.util.List;

public class ResponseModel {
    private int code;
    private String status;
    private List<DataTransaksi> data;

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

    public List<DataTransaksi> getData() {
        return data;
    }

    public void setData(List<DataTransaksi> data) {
        this.data = data;
    }
}
