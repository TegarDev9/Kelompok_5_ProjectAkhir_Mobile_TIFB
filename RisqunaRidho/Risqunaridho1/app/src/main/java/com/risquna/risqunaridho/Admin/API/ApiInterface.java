package com.risquna.risqunaridho.Admin.API;

import com.risquna.risqunaridho.Admin.model.login.Login;
import com.risquna.risqunaridho.Admin.model.register.Register;
import com.risquna.risqunaridho.pelanggan.ResponseModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {
    @FormUrlEncoded
    @POST("Rizquna_Mobile/register.php")
    Call<Register> ardRegister(
            @Field("nama") String nama,
            @Field("notelp") String notelp,
            @Field("email") String email,
            @Field("password") String password,
            @Field("role") int role
    );

    @FormUrlEncoded
    @POST("Rizquna_Mobile/Login.php")
    Call<Login> ardLogin(
            @Field("email") String email,
            @Field("password") String password
    );

    //Pelanggan

    @GET("Rizquna_Mobile/retrieveusers.php")
    Call<ResponseModel> ardRetrieveData();

    @FormUrlEncoded
    @POST("Rizquna_Mobile/create.php")
    Call<ResponseModel> ardCreateData(
            @Field ( "userid" ) int userid,
            @Field ( "nama" ) String nama,
            @Field ( "notelp" ) String notelp,
            @Field ( "alamat" ) String alamat,
            @Field ( "email" ) String email,
            @Field ( "password" ) String password
    );

    //petugas

    @GET("Rizquna_Mobile/retrievepetugas.php")
    Call<com.risquna.risqunaridho.petugas.ResponseModel> ardRetrieveDatapetugas();

    @GET("Rizquna_Mobile/retrievepemesanan.php")
    Call<com.risquna.risqunaridho.pemesanan.ResponseModel> ardRetrieveDataPemesanan();

    @GET("Rizquna_Mobile/retrieveproduk.php")
    Call<com.risquna.risqunaridho.produk.ResponseModel> ardRetrieveDataProduk();

    @FormUrlEncoded
    @POST("Rizquna_Mobile/updatepetugas.php")
    Call<com.risquna.risqunaridho.petugas.ResponseModel> ardupdatepetugas(
            @Field("nama") String nama,
            @Field("notelp") String notelp,
            @Field("email") String email,
            @Field("password") String password,
            @Field("role") int role
    );
    @FormUrlEncoded
    @POST("Rizquna_Mobile/delete_petugas.php")
    Call<com.risquna.risqunaridho.petugas.ResponseModel> ardDeletePetugas(
            @Field("idpetugas") int idpetugas
    );


    @FormUrlEncoded
    @POST("Rizquna_Mobile/get_petugas.php")
    Call<com.risquna.risqunaridho.petugas.ResponseModel> ardGetPetugas(
            @Field("idpetugas") int idpetugas
    );

}
