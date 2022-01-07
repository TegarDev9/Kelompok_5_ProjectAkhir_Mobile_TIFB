package com.risquna.risqunaridho.petugas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.risquna.risqunaridho.Admin.API.ApiClient;
import com.risquna.risqunaridho.Admin.API.ApiInterface;
import com.risquna.risqunaridho.Admin.model.login.LoginActivity;
import com.risquna.risqunaridho.Admin.model.register.Register;
import com.risquna.risqunaridho.DashbordActivity;
import com.risquna.risqunaridho.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class updatepetugasActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etNama, etEmail, etPassword, etTelp, etrole;
    private Button btnSubmit;
    ApiInterface apiInterface;
    private String nama, email, password, notelp, role;
    private  int xIdpetugas,xrole;
    private  String xNamapetugas,xNotelp,xEmail,xPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_updatepetugas );


        // Menerima data
        Intent terima = getIntent();
        xIdpetugas = terima.getIntExtra("xIdpetugas", -1);
        xNamapetugas = terima.getStringExtra("xNamapetugas");
        xNotelp = terima.getStringExtra("xNotelp");
        xEmail = terima.getStringExtra("xEmail");
        xPassword = terima.getStringExtra("xPassword");
        xrole = terima.getIntExtra("xRole", -1);
        System.out.println("terima id : " + xIdpetugas);
        etNama = (EditText) findViewById ( R.id.et_nameUp );
        etTelp = (EditText) findViewById ( R.id.et_telpUp );
        etEmail = (EditText) findViewById ( R.id.et_emailUp );
        etPassword = (EditText) findViewById ( R.id.et_passwordUp );
        etrole = (EditText) findViewById ( R.id.et_role );


        btnSubmit = findViewById ( R.id.btn_submit );



        etNama.setText(xNamapetugas);
        etTelp.setText(xNotelp);
        etEmail.setText(xEmail);
        etPassword.setText(xPassword);
        etrole.setText(String.valueOf(xrole));


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = etNama.getText().toString();
                String email = etEmail.getText().toString();
                String notelp = etTelp.getText().toString();
                String password = etPassword.getText().toString();
                int role = Integer.parseInt ( etrole.getText().toString() );

                System.out.println(nama + email +  notelp + password + role);


                apiInterface = ApiClient.getClient(updatepetugasActivity.this).create(ApiInterface.class);
                Call<com.risquna.risqunaridho.petugas.ResponseModel> call = apiInterface.ardupdatepetugas (  xIdpetugas, nama, email,notelp,password,role);
                call.enqueue(new Callback<com.risquna.risqunaridho.petugas.ResponseModel>() {
                    @Override

                    public void onResponse(Call<com.risquna.risqunaridho.petugas.ResponseModel> call, Response<com.risquna.risqunaridho.petugas.ResponseModel> response) {
                        int respon = response.body().getKode ();
                        if(respon == 1){
                            Toast.makeText(updatepetugasActivity.this, response.body().getPesan (), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(updatepetugasActivity.this, DashbordActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(updatepetugasActivity.this, response.body().getPesan (), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseModel> call, Throwable t) {
                        Toast.makeText(updatepetugasActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

    private void updateData() {
    }

    @Override
    public void onClick(View v) {


    }

}