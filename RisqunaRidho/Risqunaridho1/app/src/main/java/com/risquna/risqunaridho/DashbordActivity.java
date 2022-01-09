package com.risquna.risqunaridho;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.risquna.risqunaridho.Admin.API.ApiClient;
import com.risquna.risqunaridho.Admin.API.ApiInterface;
import com.risquna.risqunaridho.Admin.model.login.LoginActivity;
import com.risquna.risqunaridho.pelanggan.pelangganActivity;
import com.risquna.risqunaridho.pemesanan.AdapterPemesanan;
import com.risquna.risqunaridho.pemesanan.DataPemesanan;
import com.risquna.risqunaridho.pemesanan.ResponseModel;
import com.risquna.risqunaridho.petugas.petugasActivity;
import com.risquna.risqunaridho.produk.produkActivity;
import com.risquna.risqunaridho.session.SessionManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashbordActivity extends AppCompatActivity {
    TextView etUsername, etemail, tvJmluser, tvJmlPendapatan, tvJmlPesananBaru, tvJmlSendingPackage, tvJmlPesananSelesai;
    SessionManager sessionManager;
    String username, email;
    private RecyclerView rvData;
    private RecyclerView.Adapter adData;
    private RecyclerView.LayoutManager lmData;
    private List<DataPemesanan> listData = new ArrayList<DataPemesanan> ();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_dashbord );

        SessionManager sessionManager = new SessionManager(this);
        sessionManager.checkLogin();

        ImageButton btnproduk = findViewById ( R.id.id_produk );
        ImageButton btnpemesanan = findViewById ( R.id.id_pemesanan );
        ImageButton btnpelanggan = findViewById ( R.id.id_pelanggan );
        Button btnLogout = findViewById(R.id.btn_logout);

        rvData = findViewById ( R.id.rv_data );
        lmData = new LinearLayoutManager ( this, LinearLayoutManager.VERTICAL, false );
        rvData.setLayoutManager ( lmData );
        retrieveData ();
        getJumlahUser();
        getJumlahPendapatan();
        getJumlahPesananBaru();
        getJumlahSendingPackage();
        getJumlahPesananSelesai();

        btnproduk.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashbordActivity.this, produkActivity.class);
                startActivity(intent);
            }
        } );

        btnpemesanan.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (DashbordActivity.this, petugasActivity.class);
                startActivity ( intent );
            }
        } );

        btnpelanggan.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (DashbordActivity.this, pelangganActivity.class);
                startActivity ( intent );
            }
        } );

        SessionManager finalSessionManager = sessionManager;
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialogPesan = new AlertDialog.Builder(DashbordActivity.this);
                dialogPesan.setTitle("Perhatian!");
                dialogPesan.setIcon(R.drawable.ic_warning);
                dialogPesan.setMessage("Apakah anda yakin ingin keluar?");
                dialogPesan.setCancelable(true);

                dialogPesan.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        finalSessionManager.logoutSession();
                        Intent intent = new Intent (DashbordActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                        Toast.makeText(DashbordActivity.this, "Anda berhasil keluar!", Toast.LENGTH_SHORT).show();
                    }
                });
                dialogPesan.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                dialogPesan.show();
            }

        });

        sessionManager = new SessionManager ( DashbordActivity.this );
        if (!sessionManager.isLoggedIn ()) {
            moveToLogin ();
    }

        etUsername = findViewById ( R.id.username );
        etemail = findViewById ( R.id.email );
        tvJmluser = findViewById(R.id.tv_jmlUser);
        tvJmlPendapatan = findViewById(R.id.tv_jmlPendapatan);
        tvJmlPesananBaru = findViewById(R.id.tv_jmlPesananBaru);
        tvJmlSendingPackage = findViewById(R.id.tv_jmlsendingPackage);
        tvJmlPesananSelesai = findViewById(R.id.tv_jmlPesananSelesai);
        btnLogout = findViewById(R.id.btn_logout);


        username = sessionManager.getUserDetail ().get ( SessionManager.NAMA );
        System.out.println("HHHH : " + username );
        email = sessionManager.getUserDetail ().get ( SessionManager.EMAIL );

        etUsername.setText ( username );
        etemail.setText ( email );
    }

    private void moveToLogin() {
        Intent intent = new Intent ( DashbordActivity.this, LoginActivity.class );
        intent.setFlags ( Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY );
        startActivity ( intent );
        finish ();
    }

    public void retrieveData() {
        ApiInterface ardData = ApiClient.getClient ( DashbordActivity.this ).create ( ApiInterface.class );
        Call<ResponseModel> tampilData = ardData.ardRetrieveDataPemesanan ();


        tampilData.enqueue ( new Callback<ResponseModel> () {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                int kode = response.body ().getKode ();
                String pesan = response.body ().getPesan ();

                Toast.makeText ( DashbordActivity.this, "Kode:" + kode + "  |Pesan:" + pesan, Toast.LENGTH_SHORT ).show ();

                listData = response.body ().getData ();

                adData = new AdapterPemesanan ( DashbordActivity.this, listData );
                rvData.setAdapter ( adData );
                adData.notifyDataSetChanged ();
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText ( DashbordActivity.this, "gagal Menghubungkan Server" + t.getMessage (), Toast.LENGTH_SHORT ).show ();
            }
        } );
    }

    private void getJumlahUser(){
        ApiInterface ardProduk = ApiClient.getClient(DashbordActivity.this).create(ApiInterface.class);
        Call<String> jumlah = ardProduk.ardGetJumlahUser();
        jumlah.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                //Toast.makeText(getApplicationContext(), response.body(), Toast.LENGTH_SHORT).show();
                String total = response.body();
                tvJmluser.setText(total);

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });

    }

    private void getJumlahPendapatan(){
        ApiInterface ardPendapatan = ApiClient.getClient(DashbordActivity.this).create(ApiInterface.class);
        Call<String> jumlah = ardPendapatan.ardGetJumlahPendapatan();
        jumlah.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                //Toast.makeText(getApplicationContext(), response.body(), Toast.LENGTH_SHORT).show();
                String total = response.body();
                tvJmlPendapatan.setText(total);

            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });

    }

    private void getJumlahPesananBaru(){
        ApiInterface ardBaru = ApiClient.getClient(DashbordActivity.this).create(ApiInterface.class);
        Call<String> jumlah = ardBaru.ardGetJumlahPesananBaru();
        jumlah.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                //Toast.makeText(getApplicationContext(), response.body(), Toast.LENGTH_SHORT).show();
                String total = response.body();
                tvJmlPesananBaru.setText(total);

            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });

    }

    private void getJumlahSendingPackage(){
        ApiInterface ardSending = ApiClient.getClient(DashbordActivity.this).create(ApiInterface.class);
        Call<String> jumlah = ardSending.ardGetJumlahSendingPackage();
        jumlah.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                //Toast.makeText(getApplicationContext(), response.body(), Toast.LENGTH_SHORT).show();
                String total = response.body();
                tvJmlSendingPackage.setText(total);

            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });

    }

    private void getJumlahPesananSelesai(){
        ApiInterface ardSelesai = ApiClient.getClient(DashbordActivity.this).create(ApiInterface.class);
        Call<String> jumlah = ardSelesai.ardGetJumlahPesananComplate();
        jumlah.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                //Toast.makeText(getApplicationContext(), response.body(), Toast.LENGTH_SHORT).show();
                String total = response.body();
                tvJmlPesananSelesai.setText(total);

            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }




}