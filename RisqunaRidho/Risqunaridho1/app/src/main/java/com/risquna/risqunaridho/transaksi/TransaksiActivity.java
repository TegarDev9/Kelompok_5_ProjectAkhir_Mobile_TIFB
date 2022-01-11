package com.risquna.risqunaridho.transaksi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.risquna.risqunaridho.Admin.API.ApiClient;
import com.risquna.risqunaridho.Admin.API.ApiInterface;
import com.risquna.risqunaridho.DashbordActivity;
import com.risquna.risqunaridho.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransaksiActivity extends AppCompatActivity {

    private RecyclerView rvPesanan;
    private RecyclerView.Adapter adData;
    private RecyclerView.LayoutManager lmPesanan;
    private List<DataTransaksi> listData = new ArrayList<DataTransaksi>();
    private SwipeRefreshLayout srlPesanan;
    private ProgressBar pbPesanan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_transaksi );
        srlPesanan = findViewById(R.id.srl_pesanan);
        rvPesanan = findViewById ( R.id.rv_pesanan );
        pbPesanan = findViewById(R.id.pb_pesanan);
        lmPesanan = new LinearLayoutManager( this, LinearLayoutManager.VERTICAL,false );
        rvPesanan.setLayoutManager ( lmPesanan );
        retrieveData();


        srlPesanan.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srlPesanan.setRefreshing(true);
                retrieveData();
                srlPesanan.setRefreshing(false);
            }
        });
    }





    public void retrieveData() {
        ApiInterface ardData = ApiClient.getClient ( TransaksiActivity.this).create ( ApiInterface.class );
        Call<ResponseModel> tampilData = ardData.ardRetrievePemesanan ();


        tampilData.enqueue ( new Callback<ResponseModel>()  {


            @Override
            public void onResponse(Call<com.risquna.risqunaridho.transaksi.ResponseModel> call, Response<ResponseModel> response) {

                int code = response.body ().getCode ();
                String status = response.body ().getStatus ();

                //Toast.makeText ( TransaksiActivity.this, "Kode:" + code + "  |Pesan:" + status, Toast.LENGTH_SHORT ).show ();

                listData = response.body ().getData ();

                adData = new AdapterTransaksi( TransaksiActivity.this, listData );
                rvPesanan.setAdapter ( adData );
                adData.notifyDataSetChanged ();
                pbPesanan.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<com.risquna.risqunaridho.transaksi.ResponseModel> call, Throwable t) {


                Toast.makeText ( TransaksiActivity.this, "gagal Menghubungkan Server" + t.getMessage (), Toast.LENGTH_SHORT ).show ();

                pbPesanan.setVisibility(View.INVISIBLE);
            }
        } );

    }


    public void actionBack(View view) {
        Intent intent = new Intent ( TransaksiActivity.this, DashbordActivity.class );
        intent.setFlags ( Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY );
        startActivity ( intent );
        finish ();
    }
}