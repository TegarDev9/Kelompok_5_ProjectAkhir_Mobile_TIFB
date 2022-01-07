package com.risquna.risqunaridho.produk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.risquna.risqunaridho.Admin.API.ApiClient;
import com.risquna.risqunaridho.Admin.API.ApiInterface;
import com.risquna.risqunaridho.DashbordActivity;
import com.risquna.risqunaridho.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class tambahProdukActivity extends AppCompatActivity {


    private EditText etNama, etDeskripsi, etRating, etBefore, etAfter, etTanggal, etStok;
    private String namaproduk, picture, deskripsi, tgl;
    private String rating = "";
    private String hargabefore;
    private String hargaafter;
    private String stok;
    private int id_kategori;
    private Spinner sp_idkategori;
    private String[] kategori = {"kerudung", "gamis"};
    //    private CircleImageView;
    private ApiInterface apiInterface;
    private Button btn_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_produk);

        etNama = (EditText) findViewById(R.id.et_namaProduk);
        etDeskripsi = (EditText) findViewById(R.id.et_deskripsi);
        etRating = (EditText) findViewById(R.id.et_rating);
        etBefore = (EditText) findViewById(R.id.et_hargaBefore);
        etAfter = (EditText) findViewById(R.id.et_hargaAfter);
//        etTanggal = (EditText) findViewById ( R.id.et_tgl );
        etStok = (EditText) findViewById(R.id.et_stok);



        btn_submit = findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(v -> tambahProduk());

        sp_idkategori = findViewById(R.id.sp_idkategori);
        System.out.println("data spinner : " + sp_idkategori);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, kategori);
        sp_idkategori.setAdapter(adapter);

        sp_idkategori.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView adapterView, View view, int i, long l) {
                if (adapter.getItem(i) == kategori[0]) {
                    id_kategori = 1;
                    Toast.makeText(tambahProdukActivity.this, Integer.toString(id_kategori), Toast.LENGTH_SHORT).show();
                } else if (adapter.getItem(i) == kategori[1]) {
                    id_kategori = 2;
                    Toast.makeText(tambahProdukActivity.this, Integer.toString(id_kategori), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(tambahProdukActivity.this, "Kategori wajib diisi!", Toast.LENGTH_SHORT).show();
                }
//                Toast.makeText(tambahProdukActivity.this, adapter.getItem(i), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView adapterView) {
            }
        });
    }

    private void tambahProduk() {
        namaproduk = etNama.getText().toString();
        deskripsi = etDeskripsi.getText().toString();
        rating = etRating.getText().toString();
        hargabefore = etBefore.getText().toString();
        hargaafter = etAfter.getText().toString();
        stok = etStok.getText().toString();

        System.out.println(id_kategori + ", " + namaproduk + ", " + deskripsi + "," +
                rating + ", " + hargabefore + "," + hargaafter + "," + stok);

        apiInterface = ApiClient.getClient(tambahProdukActivity.this).create(ApiInterface.class);
        Call<ResponseModel> call = apiInterface.ardCreateProduk(id_kategori, namaproduk, deskripsi, rating, hargabefore, hargaafter, stok);
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                int respon = response.body().getCode();
                if (respon == 1) {
                    Toast.makeText(tambahProdukActivity.this, response.body().getStatus(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(tambahProdukActivity.this, DashbordActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(tambahProdukActivity.this, response.body().getStatus(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(tambahProdukActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}