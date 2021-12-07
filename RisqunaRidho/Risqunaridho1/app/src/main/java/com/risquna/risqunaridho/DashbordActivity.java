package com.risquna.risqunaridho;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.risquna.risqunaridho.pelanggan.pelangganActivity;
import com.risquna.risqunaridho.pemesanan.pemesananActivity;
import com.risquna.risqunaridho.produk.produkActivity;

public class DashbordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_dashbord );


        ImageButton btnproduk = findViewById ( R.id.id_produk );
        ImageButton btnpemesanan = findViewById ( R.id.id_pemesanan );
        ImageButton btnpelanggan = findViewById ( R.id.id_pelanggan );


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
                Intent intent = new Intent (DashbordActivity.this, pemesananActivity.class);
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
    }
}