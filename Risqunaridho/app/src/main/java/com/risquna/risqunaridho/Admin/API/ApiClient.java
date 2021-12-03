package com.risquna.risqunaridho.Admin.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    //membuat base url
    private static final String BASE_URL = "http://172.17.100.2/RizqunaRidho_Mobile/";
    //membuat variable retrofit
    private static Retrofit retrofit;

    //membuat function dengan tipe retrofit
    //private static RetroServer mInstance;
    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    //mengkonvert data menjadi json agar dapat dikenali android
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
    }
