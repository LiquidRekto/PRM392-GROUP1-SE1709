package com.example.fitapp.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fitapp.R;
import com.example.fitapp.api.APIClient;
import com.example.fitapp.api.interfaces.APIAuthInterface;
import com.example.fitapp.model.request.LoginRequest;
import com.example.fitapp.model.response.TokenResponse;
import com.example.fitapp.util.SharedPrefUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserLoginActivity extends AppCompatActivity {
    private static final String TAG = FCMTestActivity.class.getName();
    APIAuthInterface authApi;
    EditText txtUsername, txtPassword;

    SharedPreferences sharedPrefs;
    Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        init();
    }

    private void init() {
        authApi = APIClient.getClient().create(APIAuthInterface.class);

        txtUsername = (EditText) findViewById(R.id.txt_username);
        txtPassword = (EditText) findViewById(R.id.txt_password);
        btnLogin = (Button) findViewById(R.id.btn_login);

        sharedPrefs = getSharedPreferences("com.example.fitapp", Context.MODE_PRIVATE);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performLogin();
            }
        });
    }

    private void performLogin() {
        LoginRequest loginReq = new LoginRequest(
                txtUsername.getText().toString(),
                txtPassword.getText().toString()
        );

        Call<TokenResponse> call = authApi.login(loginReq);
        
        call.enqueue(new Callback<TokenResponse>() {
            @Override
            public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                if (response.code() == 200) {
                    Toast.makeText(UserLoginActivity.this, "Login success", Toast.LENGTH_SHORT).show();
                    TokenResponse tokenObj = response.body();
                    SharedPrefUtils.attachKey(sharedPrefs, "jwt_token", tokenObj.getToken());
                    moveToHome();
                } else {
                    Toast.makeText(UserLoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "Login failed: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<TokenResponse> call, Throwable throwable) {
                Log.d(TAG, "Error: " + throwable.getMessage());
                Toast.makeText(UserLoginActivity.this, "API Execution failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void moveToHome() {
        Intent homeIntent = new Intent(this, HomeActivity.class);
        startActivity(homeIntent);
    }

}
