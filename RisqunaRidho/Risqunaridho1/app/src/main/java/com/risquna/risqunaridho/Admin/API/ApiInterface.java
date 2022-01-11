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
    @POST("risqunastore/api_register.php")
    Call<Register> ardRegister(

            @Field("namapetugas") String nama,
            @Field("notelp") String notelp,
            @Field("email") String email,
            @Field("password") String password,
            @Field("role") int role
    );

    @FormUrlEncoded
    @POST("risqunastore/api_Login.php")
    Call<Login> ardLogin(
            @Field("email") String email,
            @Field("password") String password
    );

    //Pelanggan

    @GET("risqunastore/api_retrieveusers.php")
    Call<ResponseModel> ardRetrieveData();

    @FormUrlEncoded
    @POST("risqunastore/api_create.php")
    Call<ResponseModel> ardCreateData(
            @Field ( "userid" ) int userid,
            @Field ( "nama" ) String nama,
            @Field ( "notelp" ) String notelp,
            @Field ( "alamat" ) String alamat,
            @Field ( "email" ) String email,
            @Field ( "password" ) String password
    );

    //petugas

    @GET("risqunastore/api_retrievepetugas.php")
    Call<com.risquna.risqunaridho.petugas.ResponseModel> ardRetrieveDatapetugas();

    @GET("risqunastore/api_retrievepemesanan.php")
    Call<com.risquna.risqunaridho.pemesanan.ResponseModel> ardRetrieveDataPemesanan();

    @FormUrlEncoded
    @POST("risqunastore/api_updatepetugas.php")
    Call<com.risquna.risqunaridho.petugas.ResponseModel> ardupdatepetugas(
            @Field ( "idpetugas" ) int idpetugas,
            @Field("nama") String namapetugas,
            @Field("notelp") String notelp,
            @Field("email") String email,
            @Field("password") String password,
            @Field("role") int role
    );
    @FormUrlEncoded
    @POST("risqunastore/api_delete_petugas.php")
    Call<com.risquna.risqunaridho.petugas.ResponseModel> ardDeletePetugas(
            @Field("idpetugas") int idpetugas
    );


    @FormUrlEncoded
    @POST("risqunastore/api_get_petugas.php")
    Call<com.risquna.risqunaridho.petugas.ResponseModel> ardGetPetugas(
            @Field("idpetugas") int idpetugas
    );

    //Produk
    @GET("risqunastore/api_retrieveproduk.php")
    Call<com.risquna.risqunaridho.produk.ResponseModel> ardRetrieveDataProduk();

    @FormUrlEncoded
    @POST("risqunastore/api_add_produk.php")
    Call<com.risquna.risqunaridho.produk.ResponseModel> ardCreateProduk(
            @Field ( "idkategori" ) int idkategori,
            @Field ( "namaproduk" ) String namaproduk,
            @Field ( "deskripsi" ) String deskripsi,
            @Field ( "rating" ) String rating,
            @Field ( "hargabefore" ) String hargabefore,
            @Field ( "hargaafter" ) String hargaafter,
            @Field ( "stok" ) String stok
    );

    @FormUrlEncoded
    @POST("risqunastore/api_update_produk.php")
    Call<com.risquna.risqunaridho.produk.ResponseModel> ardUpdateProduk(
            @Field("idproduk") int idproduk,
            @Field ( "idkategori" ) int idkategori,
            @Field ( "namaproduk" ) String namaproduk,
            @Field ( "deskripsi" ) String deskripsi,
            @Field ( "rating" ) String rating,
            @Field ( "hargabefore" ) String hargabefore,
            @Field ( "hargaafter" ) String hargaafter,
            @Field ( "stok" ) String stok
    );

    @FormUrlEncoded
    @POST("risqunastore/api_delete_produk.php")
    Call<com.risquna.risqunaridho.produk.ResponseModel> ardDeleteProduk(
            @Field("idproduk") int idproduk
    );

    @FormUrlEncoded
    @POST("risqunastore/api_get_produk.php")
    Call<com.risquna.risqunaridho.produk.ResponseModel> ardGetProduk(
            @Field("idproduk") int idproduk
    );

    @GET("risqunastore/api_jumlah_user.php")
    Call<String>ardGetJumlahUser();

    @GET("risqunastore/api_jumlah_pendapatan.php")
    Call<String>ardGetJumlahPendapatan();

    @GET("risqunastore/api_jumlah_pesananbaru.php")
    Call<String>ardGetJumlahPesananBaru();

    @GET("risqunastore/api_jumlah_sendingpackage.php")
    Call<String>ardGetJumlahSendingPackage();

    @GET("risqunastore/api_jumlah_pesanancomplate.php")
    Call<String>ardGetJumlahPesananComplate();

    @FormUrlEncoded
    @POST("risqunastore/api_upload_gambar.php")
    Call<com.risquna.risqunaridho.produk.ResponseModel> ardUploadImage(
            @Field("idproduk") int idproduk,
            @Field("gambar") String gambar
    );

    @GET("risqunastore/api_seecomplate.php")
    Call<com.risquna.risqunaridho.transaksi.ResponseModel> ardSeeComplate();

    @GET("risqunastore/api_retrievepemesanan.php")
    Call<com.risquna.risqunaridho.transaksi.ResponseModel> ardRetrievePemesanan();

    @FormUrlEncoded
    @POST("risqunastore/api_getpesanan.php")
    Call<com.risquna.risqunaridho.transaksi.ResponseModel> ardGetPesanan(
            @Field("idcart") int idcart
    );

    @FormUrlEncoded
    @POST("risqunastore/api_updatesendingpackage.php")
    Call<com.risquna.risqunaridho.transaksi.ResponseModel> ardGetUpdateSendingPackage(
            @Field("idcart") int idcart,
            @Field("status") String status
    );
    @FormUrlEncoded
    @POST("risqunastore/api_updatecomplate.php")
    Call<com.risquna.risqunaridho.transaksi.ResponseModel> ardGetUpdateComplate(
            @Field("idcart") int idcart,
            @Field("status") String status
    );
}
