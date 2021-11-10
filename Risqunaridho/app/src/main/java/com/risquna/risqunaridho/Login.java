package com.risquna.risqunaridho;

import android.os.Bundle;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {
    private Button btn_signin;
    EditText et_emailorphone, et_password;



    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_login );

        et_emailorphone = (EditText)findViewById(R.id.et_emailorphone);
        et_password = (EditText)findViewById(R.id.et_password);
        btn_signin = (Button)findViewById(R.id.btn_signin);



        btn_signin = findViewById(R.id.btn_signin);
        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            /**Membuat inten pindah */
            public void onClick(View v) {
                Intent pindah = new Intent(Login.this, registrasi.class);
                startActivity(pindah);
            }
        });


    }
}


