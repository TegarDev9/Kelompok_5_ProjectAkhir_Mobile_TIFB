package com.risquna.risqunaridho;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.risquna.risqunaridho.Admin.registrasi;


public class Login extends AppCompatActivity {
    private Button btn_signin;
    EditText et_email, et_password;
    String email, password;
    TextView tv_footer2;



    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_login );

        et_email = (EditText)findViewById( R.id.et_email);
        et_password = (EditText)findViewById( R.id.et_password);
        btn_signin = (Button)findViewById( R.id.btn_signin);
        tv_footer2 = (TextView) findViewById( R.id.tv_footer2);



        btn_signin = findViewById( R.id.btn_signin);
        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            /**Membuat inten pindah */
            public void onClick(View v) {
                Intent pindah = new Intent(Login.this, registrasi.class);
                startActivity(pindah);
            }
        });
   }

    public void signUp(View view) {
        Intent intent = new Intent(Login.this, registrasi.class);
        startActivity(intent);
    }

    //    private void userLogin() {
//        email = et_email.getText().toString().trim();
//        password = et_password.getText().toString().trim();
//
//        if (email.isEmpty()) {
//            et_email.setError("Nomor telepon masih kosong!");
//            et_email.requestFocus();
//            return;
//        } else if (password.isEmpty()) {
//            et_password.setError("Password masih kosong!");
//            et_password.requestFocus();
//            return;
//        }
//
//        Call<ResponseModelAdmin> userLogin = RetroServer.getInstance().getAPI.ardUserLogin(email, password);
//        userLogin.enqueue(new Callback<ResponseModelAdmin>() {
//            @Override
//            public void onResponse(Call<ResponseModelAdmin> call, Response<ResponseModelAdmin> response) {
//                int kode = response.body().getCode();
//                String pesan = response.body().getStatus();
//
//                if (kode == 200) {
//                    Toast.makeText(Login.this, "Kode: " +kode +" | Pesan: " +pesan, Toast.LENGTH_SHORT).show();
//                    Intent i = new Intent(LoginActivity.this, MenuActivity.class);
//                    startActivity(i);
//                    finish();
//                } else {
//                    Toast.makeText(LoginActivity.this, "Kode: " +kode +" | Pesan: " +pesan, Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseModelAdmin> call, Throwable t) {
//                Toast.makeText(LoginActivity.this, "Gagal login: " +t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
}


