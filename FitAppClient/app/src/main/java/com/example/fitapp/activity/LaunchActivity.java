package com.example.fitapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fitapp.R;

public class LaunchActivity extends AppCompatActivity {
    Button btnLoginScreen, btnRegisterScreen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launch_layout);
        init();
    }

    private void init() {
        btnLoginScreen = (Button) findViewById(R.id.btn_login_screen);
        btnRegisterScreen = (Button) findViewById(R.id.btn_register_screen);

        btnLoginScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToLoginScreen();
            }
        });
        btnRegisterScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToRegisterScreen();
            }
        });
    }

    private void goToLoginScreen() {
        Intent myIntent = new Intent(this, UserLoginActivity.class);
        startActivity(myIntent);
    }

    private void goToRegisterScreen() {
        Intent myIntent = new Intent(this, UserRegisterActivity.class);
        startActivity(myIntent);
    }
}
