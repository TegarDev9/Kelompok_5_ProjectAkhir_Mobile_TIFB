package com.risquna.risqunaridho.produk;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.risquna.risqunaridho.Admin.API.ApiClient;
import com.risquna.risqunaridho.Admin.API.ApiInterface;
import com.risquna.risqunaridho.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateGambarProdukActivity extends AppCompatActivity {

    private String gambar, varFoto;
    private int IMG_REQUEST = 21;
    private int idproduk;
    private Bitmap bitmap;
    private ImageView ivProduk;
    private Button btnSelectImage, btnUploadImage;
    private List<DataProduk> listProduk = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_gambar_produk);


        Intent terima = getIntent();
        idproduk = terima.getIntExtra("idproduk", -1);

        ivProduk = findViewById(R.id.iv_produk);
        btnSelectImage = findViewById(R.id.btnSelectImage);
        btnUploadImage = findViewById(R.id.btnUploadImage);

        getData();
        btnSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, IMG_REQUEST);
            }
        });

        btnUploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    uploadImage();
                    Intent intent = new Intent ( UpdateGambarProdukActivity.this, produkActivity.class );
                    intent.setFlags ( Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY );
                    startActivity ( intent );
                } catch (Exception e) {
                    // e.printStackTrace();
                    Toast.makeText(UpdateGambarProdukActivity.this, "Gagal memperbarui gambar produk", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    //
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == IMG_REQUEST && resultCode == RESULT_OK && data != null) {

            Uri path = data.getData();

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), path);
                ivProduk.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void uploadImage() {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,75, byteArrayOutputStream);
        byte[] imageInByte = byteArrayOutputStream.toByteArray();
        String encodedImage =  Base64.encodeToString(imageInByte,Base64.DEFAULT);

        ApiInterface ardData = ApiClient.getInstance().getApi();
        Call<ResponseModel> call = ardData.ardUploadImage(idproduk, encodedImage);
//        int id = 1;
//        Call<ResponsePOJO> call = RetroClient.getInstance().getApi().uploadImage(id, encodedImage);
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {


                if(response.body().getCode() == 201){
                    Toast.makeText(UpdateGambarProdukActivity.this, response.body().getStatus(), Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(UpdateGambarProdukActivity.this, response.body().getStatus(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(UpdateGambarProdukActivity.this, "Network Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getData() {
        ApiInterface ardData = ApiClient.koneksi().create(ApiInterface.class);
        Call<ResponseModel> getData = ardData.ardGetProduk(idproduk);
        getData.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                int kode = response.body().getCode();
                if (kode == 200) {
                    listProduk = response.body().getProduk_list();



                    varFoto = listProduk.get(0).getGambar();

                    String urlGambar = ApiClient.BASE_URL + "risqunastore/" + varFoto;
                    Glide.with(UpdateGambarProdukActivity.this)
                            .load(urlGambar)// load gambar
                            .placeholder(R.drawable.logo)// sebelum load gambar dari data
                            .error(R.drawable.ic_warning) // load error
                            .into(ivProduk);
                    //Toast.makeText(ctx, "gambar :    " + ApiClient.BASE_URL1 +urlGambar, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {

            }
        });

    }

    public void actionBack(View view) {
        Intent intent = new Intent ( UpdateGambarProdukActivity.this, produkActivity.class );
        startActivity ( intent );
    }
}