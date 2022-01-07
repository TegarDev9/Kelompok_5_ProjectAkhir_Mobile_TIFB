package com.risquna.risqunaridho.session;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.risquna.risqunaridho.Admin.model.login.LoginData;
import com.risquna.risqunaridho.SplashScreen;

import java.util.HashMap;
import java.util.List;

public class SessionManager {
    private Context _context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public static final String IS_LOGGED_IN = "isLoggedIn";
    public static final String ID_PETUGAS = "idpetugas";
    public static final String EMAIL = "email";
    public static final String NAMA = "namapetugas";

    public SessionManager (Context context){
        this._context = context;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor = sharedPreferences.edit();
    }

    public void createLoginSession(LoginData user) {
        editor.putBoolean(IS_LOGGED_IN, true);
        editor.putString(ID_PETUGAS, user.getIdpetugas());
        editor.putString(EMAIL, user.getEmail());
        editor.putString(NAMA, user.getNama());
        editor.commit();
    }

    public HashMap<String,String> getUserDetail(){
        HashMap<String,String> user = new HashMap<>();
        user.put(String.valueOf((ID_PETUGAS)), sharedPreferences.getString(String.valueOf((ID_PETUGAS)),null));
        user.put((EMAIL), sharedPreferences.getString(EMAIL,null));
        user.put(NAMA, sharedPreferences.getString(NAMA,null));
        return user;
    }

    public void checkLogin(){
        if (!this.IS_LOGIN()){
            Intent i = new Intent(_context, SplashScreen.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            _context.startActivity(i);
        }
    }

    public boolean IS_LOGIN() {
        return sharedPreferences.getBoolean(IS_LOGGED_IN, false);
    }


    public void logoutSession(){
        editor.clear();
        editor.commit();
    }

    public boolean isLoggedIn(){
        return sharedPreferences.getBoolean(IS_LOGGED_IN, false);
    }


}
