package com.example.fitapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.fitapp.activity.FCMTestActivity;

public class MainActivity extends AppCompatActivity {

    Button fcmSwitch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Init();
    }

    private void Init() {
        fcmSwitch = (Button) findViewById(R.id.fcm_layout_btn);
        fcmSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToFCMTest();
            }
        });
    }

    private void goToFCMTest() {
        Intent myIntent = new Intent(this, FCMTestActivity.class);
        startActivity(myIntent);
    }
}