package com.risquna.risqunaridho.Pelanggan;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIRequestData {

    @GET("retrieve.php")
    Call<ResponseModel> ardRetrieveData();


    // @FormUrlEncoded
    //@POST("create.php")
    //Call<ResponseModel> ardCreateData(
    //      @Field ( "userid" ) String userid,
    //    @Field ( "nama" ) String nama,
    //  @Field ( "email" ) String email,
    //@Field ( "password" ) String password,
    //@Field ( "verif_code" ) String verif_code,
    //@Field ( "is_verified" ) String is_verified
    //  );


}


