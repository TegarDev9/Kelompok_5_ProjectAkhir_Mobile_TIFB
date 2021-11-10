package com.risquna.risqunaridho;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class registrasi extends AppCompatActivity {
    EditText et_emailorphoneUp1, et_passwordUp1;
    TextView tv_noteUp3, tv_noteUp4;
    Button btn_loginUp1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_registrasi );

        et_emailorphoneUp1 = (EditText) findViewById ( R.id.et_passwordUp1 );
        et_passwordUp1 = (EditText) findViewById ( R.id.et_passwordUp1 );
        tv_noteUp3 = (TextView) findViewById ( R.id.tv_noteUp3 );
        tv_noteUp4 = (TextView) findViewById ( R.id.tv_noteUp4 );
        btn_loginUp1 = (Button) findViewById ( R.id.btn_loginUp1 );


        btn_loginUp1 = findViewById(R.id.btn_loginUp1);
        btn_loginUp1.setOnClickListener(new View.OnClickListener() {
            @Override
            /**Membuat inten pindah */
            public void onClick(View v) {
                Intent pindah = new Intent(registrasi.this, Verify.class);
                startActivity(pindah);
            }
        });


    }
}