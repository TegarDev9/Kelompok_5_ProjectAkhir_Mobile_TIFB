package com.risquna.risqunaridho.produk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.risquna.risqunaridho.Admin.API.ApiClient;
import com.risquna.risqunaridho.Admin.API.ApiInterface;
import com.risquna.risqunaridho.Admin.model.login.Login;
import com.risquna.risqunaridho.DashbordActivity;
import com.risquna.risqunaridho.R;
import com.risquna.risqunaridho.petugas.petugasActivity;
import com.risquna.risqunaridho.petugas.updatepetugasActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class produkActivity extends AppCompatActivity {

    private RecyclerView rvProduk;
    private RecyclerView.Adapter adProduk;
    private RecyclerView.LayoutManager lmProduk;
    private List<DataProduk> listProduk = new ArrayList<>();
    private SwipeRefreshLayout srlProduk;
    private ProgressBar pbProduk;
    private FloatingActionButton fabProduk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_produk );

        rvProduk = findViewById(R.id.rv_produk);
        srlProduk = findViewById(R.id.srl_produk);
        pbProduk = findViewById(R.id.pb_produk);
        fabProduk = findViewById(R.id.fab_produk);

        lmProduk= new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvProduk.setLayoutManager(lmProduk);

        retrieveProduk();

        srlProduk.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srlProduk.setRefreshing(true);
                retrieveProduk();
                srlProduk.setRefreshing(false);
            }
        });

        //pindah ke activity tambah
        fabProduk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(produkActivity.this, tambahProdukActivity.class));
            }
        });
    }

    public void retrieveProduk() {
        pbProduk.setVisibility(View.VISIBLE);
        //memanggil API
        ApiInterface ardData = ApiClient.getClient ( produkActivity.this).create ( ApiInterface.class );
        Call<com.risquna.risqunaridho.produk.ResponseModel> tampilData = ardData.ardRetrieveDataProduk ();

        //antrikan perintah
        tampilData.enqueue(new Callback<ResponseModel>() {
            //untuk menangkap jika berhasil
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {

                //membuat variable penampung
                int code = response.body().getCode();
                String status= response.body().getStatus();

                Toast.makeText(produkActivity.this, "Code : " + code + "Status" + status, Toast.LENGTH_SHORT).show();

                listProduk = response.body().getProduk_list();

                //pasangkan dengan adapter
                adProduk = new AdapterProduk(produkActivity.this, listProduk);
                rvProduk.setAdapter(adProduk);
                adProduk.notifyDataSetChanged();

                pbProduk.setVisibility(View.INVISIBLE);
            }

            //untuk menangkap jika gagal
            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(produkActivity.this, "Gagal Menghubungi Server : "+t.getMessage(), Toast.LENGTH_SHORT).show();

                pbProduk.setVisibility(View.INVISIBLE);
            }
        });
    }

    public void actionBack(View view) {
        Intent intent = new Intent ( produkActivity.this, DashbordActivity.class );
        intent.setFlags ( Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY );
        startActivity ( intent );
        finish ();
    }
}