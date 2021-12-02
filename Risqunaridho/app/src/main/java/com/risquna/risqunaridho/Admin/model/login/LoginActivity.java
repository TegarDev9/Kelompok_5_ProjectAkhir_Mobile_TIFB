package com.risquna.risqunaridho.Admin.model.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_signin;
    EditText et_email, et_password;
    String email, password;
    TextView tv_footer2;
    ApiInterface apiInterface;
    SessionManager sessionManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_login);

        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);

        btn_signin = findViewById(R.id.btn_signin);
        btn_signin.setOnClickListener(this);

        tv_footer2 = findViewById(R.id.tv_footer2);
        tv_footer2.setOnClickListener(this);
   }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_signin:
                email = et_email.getText().toString();
                password = et_password.getText().toString();
                login(email,password);
                break;
            case R.id.tv_footer2:
                Intent intent = new Intent(this, RegistrasiActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void login(String email, String password) {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Login> loginCall = apiInterface.ardLogin(email,password);
        loginCall.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                if(response.body() != null && response.isSuccessful() && response.body().isCode()){

                    // Ini untuk menyimpan sesi
                    sessionManager = new SessionManager(LoginActivity.this);
                    List<LoginData> loginData = response.body().getUser_list();
                    sessionManager.createLoginSession(loginData);

                    //Ini untuk pindah
                    //Toast.makeText(LoginActivity.this, (CharSequence) response.body().getUser_list(), Toast.LENGTH_SHORT).show();
                    Toast.makeText(LoginActivity.this, ((LoginData) response.body().getUser_list()).getNama(), Toast.LENGTH_SHORT).show();
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


