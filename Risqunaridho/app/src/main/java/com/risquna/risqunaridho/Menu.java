package com.risquna.risqunaridho;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class Menu extends AppCompatActivity {
    BottomNavigationView bottomNavigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_menu );

        bottomNavigation = findViewById ( R.id.bottom_navigation );
        //berfungsi agar,fragment menjadi defauld ketika di jalankan pertama
        getSupportFragmentManager ().beginTransaction ().replace ( R.id.fragment_container, new HomeFragment () ).commit ();
        //memberikan action jika nanti di click apa yang akan terjadi
        bottomNavigation.setOnItemReselectedListener ( new NavigationBarView.OnItemReselectedListener () {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                //membuat objek dari sebuah fragment
                Fragment selectedFragment = null;

                switch ( (item.getItemId ())){

                    case R.id.nav_Home:
                        //Memanggil chat fragment yang di simpan di objek selected fragment
                        selectedFragment = new HomeFragment ();
                        break;

                    case R.id.nav_chart:
                        selectedFragment = new CartFragment ();
                        break;
                    case R.id.nav_Whiclist:
                        selectedFragment = new WhishlistFragment ();
                        break;

                    case R.id.nav_profil:
                        selectedFragment = new ProfileFragment ();
                        break;
                }
                //fungsi mengubah setiap fragment dengan sebuah fungsi get, replace itu bergantian sedangkan trancation menumpuk

                getSupportFragmentManager ().beginTransaction ().replace ( R.id.fragment_container, selectedFragment ).commit ();

                return ;



            }
        } );
    }
}
