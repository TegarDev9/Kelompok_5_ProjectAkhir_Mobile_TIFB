package com.risquna.risqunaridho.Admin;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestroServerAdmin {
    //membuat base url
    private static final String baseURL = "http://192.168.1.10/RisqunaRidho_Mobile/";
    //membuat variable retrofit
    private static Retrofit retro;

    //membuat function dengan tipe retrofit
    //private static RetroServer mInstance;
    public static Retrofit konekRetrofit() {
        if (retro == null) {
            retro = new Retrofit.Builder()
                    .baseUrl(baseURL)
                    //mengkonvert data menjadi json agar dapat dikenali android
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retro;
    }
}
