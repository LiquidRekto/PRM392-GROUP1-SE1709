package com.example.fitapp.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fitapp.R;
import com.example.fitapp.util.SharedPrefUtils;

public class HomeActivity extends AppCompatActivity {
    TextView lblGreetings;
    Button btnLogout;

    SharedPreferences sharedPrefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);
        init();
    }

    private void init() {
        btnLogout = (Button) findViewById(R.id.btn_logout);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performLogout();
            }
        });

        sharedPrefs = getSharedPreferences("com.example.fitapp", Context.MODE_PRIVATE);
    }

    private void performLogout() {
        SharedPrefUtils.detachKey(sharedPrefs, "jwt_token");
        moveToLaunch();
    }

    private void moveToLaunch() {
        Intent myIntent = new Intent(this, LaunchActivity.class);
        startActivity(myIntent);
    }
}
