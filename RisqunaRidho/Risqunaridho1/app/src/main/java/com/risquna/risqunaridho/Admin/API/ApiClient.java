package com.risquna.risqunaridho.Admin.API;

import android.content.Context;

import com.readystatesoftware.chuck.ChuckInterceptor;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    //membuat base url
    private static final String BASE_URL = "http://192.168.43.195";
    //membuat variable retrofit
    private static Retrofit retrofit;

    //membuat function dengan tipe retrofit
    //private static RetroServer mInstance;
    public static Retrofit getClient(Context context) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(logging).addInterceptor(new ChuckInterceptor(context)).build();
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    //mengkonvert data menjadi json agar dapat dikenali android
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
        return retrofit;
    }
}
