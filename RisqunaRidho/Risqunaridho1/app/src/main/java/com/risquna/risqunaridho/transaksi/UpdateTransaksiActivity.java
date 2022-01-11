package com.risquna.risqunaridho.transaksi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.risquna.risqunaridho.Admin.API.ApiClient;
import com.risquna.risqunaridho.Admin.API.ApiInterface;
import com.risquna.risqunaridho.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateTransaksiActivity extends AppCompatActivity {

    private TextView tvIdcart;
    private TextView tvKode;
    private TextView tvUserid;
    private TextView tvNama;
    private TextView tvTanggal;
    private TextView tvTotal;
    private TextView tvStatus;
    private Button btnSave;
    ApiInterface apiInterface;
    private  int xIdcart, xUserid, xTotalBelanja;
    private  String  xkodeOrder,xNama,xStatus, xTglBelanja;
    private List<DataTransaksi> listPemesanan = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_transaksi);



        // Menerima data
        Intent terima = getIntent();
        xIdcart = terima.getIntExtra("xIdcart", -1);
        xkodeOrder = terima.getStringExtra("xKodeOrder");
//        xUserid = terima.getIntExtra("xUserid", -1);
        xNama = terima.getStringExtra("xNama");
        xTotalBelanja = terima.getIntExtra("xTotalBelanja", -1);
        xTglBelanja = terima.getStringExtra("xTglBelanja");
        xStatus = terima.getStringExtra("xStatus");

//        tvIdcart = findViewById(R.id.tv_idcart);
        tvKode = findViewById(R.id.tv_kodeOrder);
//        tvUserid = findViewById(R.id.tv_userID);
        tvNama = findViewById(R.id.tv_namaPembeli);
        tvTanggal = findViewById(R.id.tv_tanggalBelanja);
        tvTotal = findViewById(R.id.tv_totalBelanja);
        tvStatus = findViewById(R.id.tv_status);
        btnSave = findViewById(R.id.btn_save);

        tvKode.setText(xkodeOrder);
        tvNama.setText(xNama);
        tvTotal.setText(String.valueOf(xTotalBelanja));
        tvTanggal.setText(xTglBelanja);
        tvStatus.setText(xStatus);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String status = tvStatus.getText().toString();


                apiInterface = ApiClient.getClient(UpdateTransaksiActivity.this).create(ApiInterface.class);
                Call<ResponseModel> call = apiInterface.ardGetUpdateSendingPackage( xIdcart, status);
                call.enqueue(new Callback<ResponseModel>() {
                    @Override
                    public void onResponse(Call<com.risquna.risqunaridho.transaksi.ResponseModel> call, Response<ResponseModel> response) {
                        int respon = response.body().getCode();
                        if(respon == 1){
                            Toast.makeText(UpdateTransaksiActivity.this, response.body().getStatus(), Toast.LENGTH_SHORT).show();
                            Intent inten = new Intent(UpdateTransaksiActivity.this, TransaksiActivity.class);
                            startActivity(inten);
                            finish();
                        } else {
                            Toast.makeText(UpdateTransaksiActivity.this, response.body().getStatus(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<com.risquna.risqunaridho.transaksi.ResponseModel> call, Throwable t) {
                        Toast.makeText(UpdateTransaksiActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }

                });


            }
        });

    }

    public void actionBack(View view) {
        Intent intent = new Intent ( UpdateTransaksiActivity.this, TransaksiActivity.class );
        startActivity ( intent ); }

}