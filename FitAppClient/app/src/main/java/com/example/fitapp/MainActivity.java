package com.example.fitapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import com.example.fitapp.activity.FCMTestActivity;
import com.example.fitapp.activity.HomeActivity;
import com.example.fitapp.activity.LaunchActivity;
import com.example.fitapp.util.SharedPrefUtils;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPref;
    //Button fcmSwitch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Init();
    }

    private void Init() {
        /*
        fcmSwitch = (Button) findViewById(R.id.fcm_layout_btn);
        fcmSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToFCMTest();
            }
        });

         */

        sharedPref = getSharedPreferences("com.example.fitapp", Context.MODE_PRIVATE);
        String token = SharedPrefUtils.retrieveKeyValue(sharedPref, "jwt_token");
        if (token != "") {
            (new Handler()).postDelayed(this::goToHome, 1000);
        } else {
            (new Handler()).postDelayed(this::goToLaunch, 1000);
        }
    }

    private void goToLaunch() {
        Intent myIntent = new Intent(this, LaunchActivity.class);
        startActivity(myIntent);
    }

    private void goToHome() {
        Intent myIntent = new Intent(this, HomeActivity.class);
        startActivity(myIntent);
    }
    private void goToFCMTest() {
        Intent myIntent = new Intent(this, FCMTestActivity.class);
        startActivity(myIntent);
    }
}