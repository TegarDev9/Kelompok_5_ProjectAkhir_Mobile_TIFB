package com.risquna.risqunaridho;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Verify extends AppCompatActivity {
    private Button btn_verify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);
        
        btn_verify = findViewById(R.id.btn_verify);
        btn_verify.setOnClickListener(new View.OnClickListener() {
            @Override
            /**Membuat inten pindah */
            public void onClick(View v) {
                Intent pindah = new Intent(Verify.this, Menu.class);
                startActivity(pindah);

            }
        });

    }
}