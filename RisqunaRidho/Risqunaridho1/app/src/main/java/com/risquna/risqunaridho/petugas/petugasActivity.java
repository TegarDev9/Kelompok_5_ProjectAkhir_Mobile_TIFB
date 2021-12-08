package com.risquna.risqunaridho.petugas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.risquna.risqunaridho.R;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.widget.Toast;

import com.risquna.risqunaridho.Admin.API.ApiClient;
import com.risquna.risqunaridho.Admin.API.ApiInterface;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class petugasActivity extends AppCompatActivity {

    private RecyclerView rvData;
    private RecyclerView.Adapter adData;
    private RecyclerView.LayoutManager lmData;
    private List<DataPetugas> listData = new ArrayList<DataPetugas> ();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_petugas );

        rvData = findViewById ( R.id.rv_data );
        lmData = new LinearLayoutManager ( this, LinearLayoutManager.VERTICAL,false );
        rvData.setLayoutManager ( lmData );
        retrieveData ();
    }

    public void retrieveData() {
        ApiInterface ardData = ApiClient.getClient ( petugasActivity.this).create ( ApiInterface.class );
        Call<com.risquna.risqunaridho.petugas.ResponseModel> tampilData = ardData.ardRetrieveDatapetugas ();


        tampilData.enqueue ( new Callback<com.risquna.risqunaridho.petugas.ResponseModel> ()  {


            @Override
            public void onResponse(Call<com.risquna.risqunaridho.petugas.ResponseModel> call, Response<com.risquna.risqunaridho.petugas.ResponseModel> response) {

                int kode = response.body ().getKode ();
                String pesan = response.body ().getPesan ();

                Toast.makeText ( petugasActivity.this, "Kode:" + kode + "  |Pesan:" + pesan, Toast.LENGTH_SHORT ).show ();

                listData = response.body ().getData ();

                adData = new AdapterPetugas ( petugasActivity.this, listData );
                rvData.setAdapter ( adData );
                adData.notifyDataSetChanged ();
            }

            @Override
            public void onFailure(Call<com.risquna.risqunaridho.petugas.ResponseModel> call, Throwable t) {


                Toast.makeText ( petugasActivity.this, "gagal Menghubungkan Server" + t.getMessage (), Toast.LENGTH_SHORT ).show ();
            }
        } );

    }
}