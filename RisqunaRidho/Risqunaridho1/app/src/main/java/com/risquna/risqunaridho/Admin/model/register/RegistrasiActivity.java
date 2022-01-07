package com.risquna.risqunaridho.Admin.model.register;

import static java.lang.Integer.parseInt;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.risquna.risqunaridho.Admin.API.ApiClient;
import com.risquna.risqunaridho.Admin.API.ApiInterface;
import com.risquna.risqunaridho.Admin.model.login.LoginActivity;
import com.risquna.risqunaridho.DashbordActivity;
import com.risquna.risqunaridho.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RegistrasiActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etNama, etEmail, etPassword, etTelp,etrole;
    private Button btnSubmit;
    ApiInterface apiInterface;
    private String nama, email, password, notelp,role;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_registrasi );

        etNama = (EditText) findViewById ( R.id.et_nameUp );
        etTelp = (EditText) findViewById ( R.id.et_telpUp );
        etEmail = (EditText) findViewById ( R.id.et_emailUp );
        etPassword = (EditText) findViewById ( R.id.et_passwordUp );
        etrole = (EditText) findViewById ( R.id.et_role );



        btnSubmit = findViewById ( R.id.btn_submit );
        btnSubmit.setOnClickListener ( this );


    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_submit:
                nama = etNama.getText().toString();
                notelp = etTelp.getText().toString();
                email = etEmail.getText().toString();
                password = etPassword.getText().toString();
                role = etrole.getText ().toString ();

                register(nama, notelp, email, password, Integer.parseInt ( role ) );
                break;
                
            default:

        }
    }

    private void register(String nama, String notelp, String email, String password, int role) {
        apiInterface = ApiClient.getClient(RegistrasiActivity.this).create(ApiInterface.class);
        Call<Register> call = apiInterface.ardRegister(nama, notelp, email, password, role);
        call.enqueue(new Callback<Register>() {
            @Override
            public void onResponse(Call<Register> call, Response<Register> response) {
                if(response.body() != null && response.isSuccessful() && response.body().isCode()){
                    Toast.makeText(RegistrasiActivity.this, response.body().getStatus(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegistrasiActivity.this, DashbordActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(RegistrasiActivity.this, response.body().getStatus(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Register> call, Throwable t) {
                Toast.makeText(RegistrasiActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}
