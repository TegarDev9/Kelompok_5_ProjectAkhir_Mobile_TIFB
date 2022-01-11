package com.risquna.risqunaridho.petugas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.risquna.risqunaridho.Admin.model.login.Login;
import com.risquna.risqunaridho.Admin.model.register.RegistrasiActivity;
import com.risquna.risqunaridho.DashbordActivity;
import com.risquna.risqunaridho.R;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.risquna.risqunaridho.Admin.API.ApiClient;
import com.risquna.risqunaridho.Admin.API.ApiInterface;
import com.risquna.risqunaridho.produk.produkActivity;
import com.risquna.risqunaridho.produk.tambahProdukActivity;


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
    private FloatingActionButton fabPetugas;
    private SwipeRefreshLayout srlPetugas;
    private ProgressBar pbPetugas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_petugas );
        fabPetugas = findViewById(R.id.fab_petugas);
        srlPetugas = findViewById(R.id.srl_petugas);
        rvData = findViewById ( R.id.rv_data );
        pbPetugas = findViewById(R.id.pb_petugas);
        lmData = new LinearLayoutManager ( this, LinearLayoutManager.VERTICAL,false );
        rvData.setLayoutManager ( lmData );
        retrieveData ();


        srlPetugas.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srlPetugas.setRefreshing(true);
                retrieveData ();
                srlPetugas.setRefreshing(false);
            }
        });

        //pindah ke activity tambah
        fabPetugas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(petugasActivity.this, RegistrasiActivity.class));
            }
        });
    }





    public void retrieveData() {
        ApiInterface ardData = ApiClient.getClient ( petugasActivity.this).create ( ApiInterface.class );
        Call<com.risquna.risqunaridho.petugas.ResponseModel> tampilData = ardData.ardRetrieveDatapetugas ();


        tampilData.enqueue ( new Callback<com.risquna.risqunaridho.petugas.ResponseModel> ()  {


            @Override
            public void onResponse(Call<com.risquna.risqunaridho.petugas.ResponseModel> call, Response<com.risquna.risqunaridho.petugas.ResponseModel> response) {

                int kode = response.body ().getKode ();
                String pesan = response.body ().getPesan ();

//                Toast.makeText ( petugasActivity.this, "Kode:" + kode + "  |Pesan:" + pesan, Toast.LENGTH_SHORT ).show ();

                listData = response.body ().getData ();

                adData = new AdapterPetugas ( petugasActivity.this, listData );
                rvData.setAdapter ( adData );
                adData.notifyDataSetChanged ();
                pbPetugas.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<com.risquna.risqunaridho.petugas.ResponseModel> call, Throwable t) {


                Toast.makeText ( petugasActivity.this, "gagal Menghubungkan Server" + t.getMessage (), Toast.LENGTH_SHORT ).show ();

                pbPetugas.setVisibility(View.INVISIBLE);
            }
        } );

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.action_settings){
            startActivity(new Intent(this, Login.class));
        } else if (item.getItemId() == R.id.action_Tambahdata) {
            startActivity(new Intent(this, RegistrasiActivity.class));
        } else if (item.getItemId() == R.id.action_updatedata) {
            startActivity(new Intent(this, updatepetugasActivity.class));
        }

        return true;
    }

    public void actionBack(View view) {
        Intent intent = new Intent ( petugasActivity.this, DashbordActivity.class );
        startActivity ( intent );
    }
}
