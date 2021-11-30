package com.risquna.risqunaridho.Admin;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIRequestDataAdmin {
    @GET("retrieve.php")
    Call<RestroServerAdmin> ardRetrieveData();

    @FormUrlEncoded
    @POST("register.php")
    Call<ResponseModelAdmin> ardRegister(
            @Field("nama") String nama,
            @Field("email") String email,
            @Field("password") String password,
            @Field("no_telp") String no_telp,
            @Field("role") int role
    );

    @FormUrlEncoded
    @POST("Login.php")
    Call<RestroServerAdmin> ardLogin(
            @Field("email") String email,
            @Field("password") String password
    );
}
