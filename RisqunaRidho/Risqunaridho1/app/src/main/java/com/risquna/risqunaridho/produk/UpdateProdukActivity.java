package com.risquna.risqunaridho.produk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.risquna.risqunaridho.Admin.API.ApiClient;
import com.risquna.risqunaridho.Admin.API.ApiInterface;
import com.risquna.risqunaridho.Admin.model.login.LoginActivity;
import com.risquna.risqunaridho.DashbordActivity;
import com.risquna.risqunaridho.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateProdukActivity extends AppCompatActivity {

    private int xIdproduk, xIdkategori, xRating, xHargabefore, xHargaafter, xStok;
    private int id_kategori;
    private String xNamaproduk, xImgproduk, xDeskripsi, xTgl, varFoto;
    private EditText etNama, etDeskripsi, etRating, etBefore, etAfter, etTanggal, etStok;
    private Spinner sp_idkategori;
    private String[] kategori = {"Kerudung", "Gamis"};
    private Button btn_save;
    private ApiInterface apiInterface;
//
//    private int varIdproduk, varIdkategori, varRating, varHargabefore, varHargaafter, varStok;
//    private String varNamaproduk, varImgproduk, varDeskripsi, varTgl;
//    private Context ctx;

    private FloatingActionButton fab_gambar;
    private List<DataProduk> listProduk = new ArrayList<>();
    private ImageView picture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_produk);

        // Menerima data
        Intent terima = getIntent();
        xIdproduk = terima.getIntExtra("xIdproduk", -1);
        xIdkategori = terima.getIntExtra("xIdkategori", -1);
        xNamaproduk = terima.getStringExtra("xNamaproduk");
        xImgproduk = terima.getStringExtra("xImgproduk");
        xDeskripsi = terima.getStringExtra("xDeskripsi");
        xRating = terima.getIntExtra("xRating", -1);
        xHargabefore = terima.getIntExtra("xHargabefore", -1);
        xHargaafter = terima.getIntExtra("xHargaafter", -1);
        xTgl = terima.getStringExtra("xTgl");
        xStok = terima.getIntExtra("xStok", -1);


//                    Toast.makeText(ctx, "Data : "
//                            +"1" +xIdproduk +"\n"
//                                    +"2"+xIdkategori +"\n"
//                                    +"3"+xNamaproduk +"\n"
//                                    +"4"+xImgproduk +"\n"
//                                    +"5"+xDeskripsi +"\n"
//                                    +"6"+xRating +"\n"
//                                    +"7"+xHargabefore +"\n"
//                                    +"8"+xHargaafter +"\n"
//                                    +9+xTgl+"\n"
//                                    +"10"+xStok+"\n"
//                    ,        , Toast.LENGTH_SHORT).show();
//
        sp_idkategori = findViewById(R.id.sp_idkategori);
        etNama = findViewById(R.id.et_namaProduk);
        etDeskripsi = findViewById(R.id.et_deskripsi);
        etRating = findViewById(R.id.et_rating);
        etBefore = findViewById(R.id.et_hargaBefore);
        etAfter = findViewById(R.id.et_hargaAfter);
        etStok = findViewById(R.id.et_stok);
        btn_save = findViewById(R.id.btn_save);
        picture = findViewById(R.id.picture);


        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, kategori);
        sp_idkategori.setAdapter(arrayAdapter);

        etNama.setText(xNamaproduk);
        etDeskripsi.setText(xDeskripsi);
        etRating.setText(String.valueOf(xRating));
        etBefore.setText(String.valueOf(xHargabefore));
        etAfter.setText(String.valueOf(xHargaafter));
        etStok.setText(String.valueOf(xStok));

        getData();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, kategori);
        sp_idkategori.setAdapter(adapter);

        sp_idkategori.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView adapterView, View view, int i, long l) {
                if (adapter.getItem(i) == kategori[0]) {
                    id_kategori = 1;
//                    Toast.makeText(UpdateProdukActivity.this, Integer.toString(id_kategori), Toast.LENGTH_SHORT).show();
                } else if (adapter.getItem(i) == kategori[1]) {
                    id_kategori = 2;
//                    Toast.makeText(UpdateProdukActivity.this, Integer.toString(id_kategori), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(UpdateProdukActivity.this, "Kategori wajib diisi!", Toast.LENGTH_SHORT).show();
                }
//                Toast.makeText(tambahProdukActivity.this, adapter.getItem(i), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView adapterView) {
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = etNama.getText().toString();
                String deskripsi = etDeskripsi.getText().toString();
                String rating = etRating.getText().toString();
                String before = etBefore.getText().toString();
                String after = etAfter.getText().toString();
                String stok = etStok.getText().toString();

                System.out.println(nama + deskripsi + rating +before + after + stok);

                apiInterface = ApiClient.getClient(UpdateProdukActivity.this).create(ApiInterface.class);
                Call<ResponseModel> call = apiInterface.ardUpdateProduk( xIdproduk,id_kategori, nama, deskripsi, rating,before,after,stok);
                call.enqueue(new Callback<ResponseModel>() {
                    @Override
                    public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                        int respon = response.body().getCode();
                        if(respon == 1){
                            Toast.makeText(UpdateProdukActivity.this, response.body().getStatus(), Toast.LENGTH_SHORT).show();
                            Intent inten = new Intent(UpdateProdukActivity.this, produkActivity.class);
                            startActivity(inten);
                            finish();
                        } else {
                            Toast.makeText(UpdateProdukActivity.this, response.body().getStatus(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseModel> call, Throwable t) {
                        Toast.makeText(UpdateProdukActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        fab_gambar = findViewById(R.id.fab_gambae);
        fab_gambar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent kirim = new Intent(UpdateProdukActivity.this, UpdateGambarProdukActivity.class);
                kirim.putExtra("idproduk", xIdproduk);
                startActivity(kirim);
            }
        });
    }

    private void getData() {
        ApiInterface ardData = ApiClient.koneksi().create(ApiInterface.class);
        Call<ResponseModel> getData = ardData.ardGetProduk(xIdproduk);
        getData.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                int kode = response.body().getCode();
                if (kode == 200) {
                    listProduk = response.body().getProduk_list();



                    varFoto = listProduk.get(0).getGambar();

                    String urlGambar = ApiClient.BASE_URL + "risqunastore/" + varFoto;
                    Glide.with(UpdateProdukActivity.this)
                            .load(urlGambar)// load gambar
                            .placeholder(R.drawable.logo)// sebelum load gambar dari data
                            .error(R.drawable.ic_warning) // load error
                            .into(picture);
                    //Toast.makeText(ctx, "gambar :    " + ApiClient.BASE_URL1 +urlGambar, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {

            }
        });

    }

    public void actionBack(View view) {
        Intent intent = new Intent ( UpdateProdukActivity.this, produkActivity.class );
        intent.setFlags ( Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY );
        startActivity ( intent );
        finish ();
    }
}