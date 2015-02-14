package com.example.carlosrenato.searchy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by carlosrenato on 02-10-15.
 */
public class LoginActivity extends ActionBarActivity {

    private EditText username, password;
    private Button btnLogin;

    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_login);
        //
        username = (EditText) findViewById(R.id.etUsername);
        password = (EditText) findViewById(R.id.etPass);
        btnLogin = (Button) findViewById(R.id.btnSubmit);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username.getText().toString().equals("admin") && password.getText().toString().equals("admin")) {
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(i);
                } else {
                    Log.d("layout", "Error to load credentials");
                }
            }
        });
    }
}