package com.risquna.risqunaridho.Pelanggan;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.risquna.risqunaridho.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PelangganActivity extends AppCompatActivity {
    private RecyclerView rvData;
    private RecyclerView.Adapter adData;
    private RecyclerView.LayoutManager lmData;
    private List<DataModel> listData = new ArrayList<> ();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_pelanggan );
        rvData = findViewById ( R.id.rv_data );
        lmData = new LinearLayoutManager ( this, LinearLayoutManager.VERTICAL,false );
        rvData.setLayoutManager ( lmData );
        retrieveData ();
    }

    public void retrieveData() {
        APIRequestData ardData = RetroServer.konekRetrofit ().create ( APIRequestData.class );
        Call<ResponseModel> tampilData = ardData.ardRetrieveData ();


        tampilData.enqueue ( new Callback<ResponseModel> () {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                int kode = response.body ().getKode ();
                String pesan = response.body ().getPesan ();

                Toast.makeText ( PelangganActivity.this, "Kode:" + kode + "  |Pesan:" + pesan, Toast.LENGTH_SHORT ).show ();

                listData = response.body ().getData ();

                adData = new AdapterData ( PelangganActivity.this, listData );
                rvData.setAdapter ( adData );
                adData.notifyDataSetChanged ();
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText ( PelangganActivity.this, "gagal Menghubungkan Server" + t.getMessage (), Toast.LENGTH_SHORT ).show ();
            }
        } );

    }
}
