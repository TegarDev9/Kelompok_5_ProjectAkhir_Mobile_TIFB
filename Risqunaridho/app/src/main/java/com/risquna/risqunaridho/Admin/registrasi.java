package com.risquna.risqunaridho.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.risquna.risqunaridho.R;
import com.risquna.risqunaridho.Verify;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class registrasi extends AppCompatActivity {
    private EditText etNama, etEmail, etPassword, etTelp, etRole;
    private Button btnSubmit;
    private String nama, email, password, no_telp;
    private int role; //variable penampung


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_registrasi );

        etNama = (EditText) findViewById ( R.id.et_nameUp );
        etEmail = (EditText) findViewById ( R.id.et_emailUp );
        etPassword = (EditText) findViewById ( R.id.et_passwordUp );
        etTelp = (EditText) findViewById ( R.id.et_telpUp );
        etRole = (EditText) findViewById ( R.id.et_roleUp);


        btnSubmit = findViewById( R.id.btn_submit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            /**Membuat inten pindah */
            public void onClick(View v) {
                //menangkap data yang diisikan
                nama = etNama.getText().toString();
                email = etEmail.getText().toString();
                password = etPassword.getText().toString();
                no_telp = etTelp.getText().toString();
                role = Integer.parseInt(etRole.getText().toString());

                //validasi
                if (nama.trim().equals("")) {
                    etNama.setError("Wajib diisi!");
                    etNama.requestFocus();
                    return;
                } else if (email.trim().equals("")) {
                    etEmail.setError("Wajib diisi!");
                    etEmail.requestFocus();
                    return;
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    etEmail.setError("Masukkan email yang benar!");
                    etEmail.requestFocus();
                    return;
                } else if (password.trim().equals("")) {
                    etPassword.setError("Wajib diisi!");
                    etPassword.requestFocus();
                    return;
                } else if (no_telp.trim().equals("")) {
                    etTelp.setError("Wajib diisi!");
                    etTelp.requestFocus();
                    return;
//                } else if (role.trim().equals("")) {
//                    etRole.setError("Wajib diisi!");
//                    etRole.requestFocus();
//                    return;
                } else {
                    createData();
                }
            }
        });
    }
    private void createData() {
        APIRequestDataAdmin ardData = RestroServerAdmin.konekRetrofit().create(APIRequestDataAdmin.class);
        Call<ResponseModelAdmin> simpanData = ardData.ardRegister(nama, email, password, no_telp, role);

        simpanData.enqueue(new Callback<ResponseModelAdmin>() {
            @Override
            public void onResponse(Call<ResponseModelAdmin> call, Response<ResponseModelAdmin> response) {
                int code = response.body().getCode();
                String status = response.body().getStatus();

                if (code == 201) {
                    Toast.makeText(registrasi.this, "Code: " + code + "| Status: " + status, Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(registrasi.this, "Code: " + code + "| Status: " + status, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseModelAdmin> call, Throwable t) {
                Toast.makeText(registrasi.this, "Registration Failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}