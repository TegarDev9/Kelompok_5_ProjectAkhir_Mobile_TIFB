package com.risquna.risqunaridho.Admin.model.login;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.risquna.risqunaridho.Admin.API.ApiClient;
import com.risquna.risqunaridho.Admin.API.ApiInterface;
import com.risquna.risqunaridho.Admin.model.register.RegistrasiActivity;

import com.risquna.risqunaridho.DashbordActivity;
import com.risquna.risqunaridho.R;
import com.risquna.risqunaridho.session.SessionManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {
    private String email, password;
    private ApiInterface apiInterface;
    private SessionManager sessionManager;
    private ArrayList<LoginData> listlogindata = new ArrayList();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView ( R.layout.activity_login );

        EditText et_email = findViewById(R.id.et_email);
        EditText et_password = findViewById(R.id.et_password);

        Button btn_signin = findViewById(R.id.btn_signin);
        TextView tv_footer2 = findViewById(R.id.tv_footer2);

        sessionManager = new SessionManager(LoginActivity.this);

        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = et_email.getText().toString();
                password = et_password.getText().toString();
                login(email,password);
            }
        });


        tv_footer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegistrasiActivity.class);
                startActivity(intent);

            }
        });
   }

    private void login(String email, String password) {
        apiInterface = ApiClient.getClient(LoginActivity.this).create(ApiInterface.class);
        Call<Login> loginCall = apiInterface.ardLogin(email,password);
        loginCall.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                if(response.body() != null && response.isSuccessful() && response.body().isCode()){

                    // Ini untuk menyimpan sesi

                    listlogindata.addAll(response.body().getUser_list());
                    Log.d("JSONapi", listlogindata.toString());
                    sessionManager.createLoginSession(listlogindata.get(0));

                    //Ini untuk pindah
                    //Toast.makeText(LoginActivity.this, (CharSequence) response.body().getUser_list(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, DashbordActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, response.body().getStatus(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.getLocalizedMessage() +t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}


