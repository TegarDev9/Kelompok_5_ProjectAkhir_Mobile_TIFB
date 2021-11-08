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
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_registrasi );
        dbHelper = new DBHelper ( this );

        et_emailorphoneUp1 = (EditText) findViewById ( R.id.et_passwordUp1 );
        et_passwordUp1 = (EditText) findViewById ( R.id.et_passwordUp1 );
        tv_noteUp3 = (TextView) findViewById ( R.id.tv_noteUp3 );
        tv_noteUp4 = (TextView) findViewById ( R.id.tv_noteUp4 );
        btn_loginUp1 = (Button) findViewById ( R.id.btn_loginUp1 );

        TextView tv_signup2 = (TextView) findViewById ( R.id.tv_signup2 );
        tv_signup2.setText ( fromHtml ( "Back to " +
                "</font><font color='#3b5998'>Login</font>" ) );

        tv_signup2.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                startActivity ( new Intent ( registrasi.this, Login.class ) );
            }
        } );

        btn_loginUp1.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                String email = et_emailorphoneUp1.getText ().toString ().trim ();
                String password = et_passwordUp1.getText ().toString ().trim ();
                String contain = tv_noteUp3.getText ().toString ().trim ();

                ContentValues values = new ContentValues ();


                if (!password.equals ( contain )) {
                    Toast.makeText ( registrasi.this, "atleast 6 karakter", Toast.LENGTH_SHORT ).show ();
                } else if (password.equals ( "" ) || email.equals ( "" )) {
                    Toast.makeText ( registrasi.this, "contain a number", Toast.LENGTH_SHORT ).show ();
                } else {
                    values.put ( DBHelper.row_email, email );
                    values.put ( DBHelper.row_password, password );
                    dbHelper.insertData ( values );

                    Toast.makeText ( registrasi.this, "Register successful", Toast.LENGTH_SHORT ).show ();
                    finish ();
                }
            }
        } );


    }

    public static Spanned fromHtml(String html) {
        Spanned result;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            result = Html.fromHtml ( html, Html.FROM_HTML_MODE_LEGACY );
        } else {
            result = Html.fromHtml ( html );
        }
        return result;

    }
}
