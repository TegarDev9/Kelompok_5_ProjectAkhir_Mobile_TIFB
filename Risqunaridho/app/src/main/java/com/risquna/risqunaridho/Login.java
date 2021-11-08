package com.risquna.risqunaridho;

import android.os.Bundle;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {
    EditText et_emailorphone, et_password;
    Button btn_signin;
    DBHelper dbHelper;


    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_login );

        et_emailorphone = (EditText)findViewById(R.id.et_emailorphone);
        et_password = (EditText)findViewById(R.id.et_password);
        btn_signin = (Button)findViewById(R.id.btn_signin);

        dbHelper = new DBHelper(this);
        TextView tvCreateAccount = (TextView)findViewById(R.id.sigup);

        tvCreateAccount.setText(fromHtml("I don't have account yet. " +
                "</font><font color='#3b5998'>create one</font>"));

        tvCreateAccount.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, registrasi.class));
            }
        });

        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = et_emailorphone.getText().toString().trim();
                String password = et_password.getText().toString().trim();

                Boolean res = dbHelper.checkUser(email,password);
                if(res == true){
                    Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Login.this, Menu.class));
                }else {
                    Toast.makeText(Login.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public static Spanned fromHtml(String html){
        Spanned result;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            result = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
        }else {
            result = Html.fromHtml(html);
        }
        return result;
    }
}


