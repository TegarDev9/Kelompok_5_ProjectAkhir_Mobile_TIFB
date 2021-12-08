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
//    @GET("retrieve.php")
//    Call<RestroServerAdmin> ardRetrieveData();

    @FormUrlEncoded
    @POST("RizqunaRidho_Mobile/register.php")
    Call<Register> ardRegister(
            @Field("nama") String nama,
            @Field("notelp") String notelp,
            @Field("email") String email,
            @Field("password") String password,
            @Field("role") int role
    );

    @FormUrlEncoded
    @POST("RizqunaRidho_Mobile/Login.php")
    Call<Login> ardLogin(
            @Field("email") String email,
            @Field("password") String password
    );

    //Pelanggan

    @GET("RizqunaRidho_Mobile/retrieveusers.php")
    Call<ResponseModel> ardRetrieveData();

    @FormUrlEncoded
    @POST("RizqunaRidho_Mobile/create.php")
    Call<ResponseModel> ardCreateData(
            @Field ( "userid" ) int userid,
            @Field ( "nama" ) String nama,
            @Field ( "notelp" ) String notelp,
            @Field ( "alamat" ) String alamat,
            @Field ( "email" ) String email,
            @Field ( "password" ) String password
    );

    //petugas

    @GET("RizqunaRidho_Mobile/retrievepetugas.php")
    Call<com.risquna.risqunaridho.petugas.ResponseModel> ardRetrieveDatapetugas();


}
