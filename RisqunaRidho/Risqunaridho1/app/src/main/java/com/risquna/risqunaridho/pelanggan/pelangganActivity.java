package com.risquna.risqunaridho.pelanggan;

import static com.risquna.risqunaridho.R.id.rv_data;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.risquna.risqunaridho.R;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.risquna.risqunaridho.Admin.API.ApiClient;
import com.risquna.risqunaridho.Admin.API.ApiInterface;
import com.risquna.risqunaridho.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class pelangganActivity extends AppCompatActivity {

    private RecyclerView rvData;
    private RecyclerView.Adapter adData;
    private RecyclerView.LayoutManager lmData;
    private List<DataModel> listData = new ArrayList<> ();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_pelanggan );

        rvData = findViewById ( rv_data );
        lmData = new LinearLayoutManager ( this, LinearLayoutManager.VERTICAL,false );
        rvData.setLayoutManager ( lmData );
        retrieveData ();
    }

    public void retrieveData() {
        ApiInterface ardData = ApiClient.getClient (pelangganActivity.this).create ( ApiInterface.class );
        Call<ResponseModel> tampilData = ardData.ardRetrieveData ();


        tampilData.enqueue ( new Callback<ResponseModel> () {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                int kode = response.body ().getKode ();
                String pesan = response.body ().getPesan ();

                Toast.makeText ( pelangganActivity.this, "Kode:" + kode + "  |Pesan:" + pesan, Toast.LENGTH_SHORT ).show ();

                listData = response.body ().getData ();

                adData = new AdapterData ( pelangganActivity.this, listData );
                rvData.setAdapter ( adData );
                adData.notifyDataSetChanged ();
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText ( pelangganActivity.this, "gagal Menghubungkan Server" + t.getMessage (), Toast.LENGTH_SHORT ).show ();
            }
        } );


    }
}