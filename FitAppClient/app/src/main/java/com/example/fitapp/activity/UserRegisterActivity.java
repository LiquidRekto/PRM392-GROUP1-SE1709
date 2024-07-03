package com.example.fitapp.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fitapp.R;
import com.example.fitapp.api.APIClient;
import com.example.fitapp.api.interfaces.APIAuthInterface;
import com.example.fitapp.api.interfaces.APIUserRoleInterface;
import com.example.fitapp.model.User;
import com.example.fitapp.model.UserRole;
import com.example.fitapp.model.request.RegisterRequest;
import com.example.fitapp.model.response.TokenResponse;
import com.example.fitapp.util.SharedPrefUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRegisterActivity extends AppCompatActivity {
    private static final String TAG = FCMTestActivity.class.getName();
    APIAuthInterface authApi;
    APIUserRoleInterface userRoleApi;
    EditText txtUsername, txtPassword, txtFirstName, txtLastName, txtEmail;
    Button btnRegister;

    Spinner spnRoles;
    ArrayAdapter<UserRole> rolesAdapter;
    List<UserRole> rolesSrc = new ArrayList<>();

    SharedPreferences sharedPrefs;
    int roleId = 1; // default
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);
        init();
    }

    private void init() {
        authApi = APIClient.getClient().create(APIAuthInterface.class);
        userRoleApi = APIClient.getClient().create(APIUserRoleInterface.class);

        txtUsername = (EditText) findViewById(R.id.txt_rg_username);
        txtPassword = (EditText) findViewById(R.id.txt_rg_password);
        txtFirstName = (EditText) findViewById(R.id.txt_rg_firstname);
        txtLastName = (EditText) findViewById(R.id.txt_rg_lastname);
        txtEmail = (EditText) findViewById(R.id.txt_rg_email);
        btnRegister = (Button) findViewById(R.id.btn_register);

        sharedPrefs = getSharedPreferences("com.example.fitapp", Context.MODE_PRIVATE);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performRegister();
            }
        });

        spnRoles = (Spinner) findViewById(R.id.spn_roles);

        spnRoles.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                roleId = rolesSrc.get(position).getUserRoleId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        loadUserRoles();
    }

    private void performRegister() {
        RegisterRequest registerReq = new RegisterRequest(
                txtUsername.getText().toString(),
                txtPassword.getText().toString(),
                txtFirstName.getText().toString(),
                txtLastName.getText().toString(),
                txtEmail.getText().toString(),
                roleId
        );

        Call<TokenResponse> call = authApi.register(registerReq);

        call.enqueue(new Callback<TokenResponse>() {
            @Override
            public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                if (response.code() == 200) {
                    Toast.makeText(UserRegisterActivity.this, "Register success", Toast.LENGTH_SHORT).show();
                    TokenResponse tokenObj = response.body();
                    SharedPrefUtils.attachKey(sharedPrefs, "jwt_token", tokenObj.getToken());
                    moveToHome();
                } else {
                    Toast.makeText(UserRegisterActivity.this, "Register failed", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "Register failed: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<TokenResponse> call, Throwable throwable) {
                Log.d(TAG, "Error: " + throwable.getMessage());
                Toast.makeText(UserRegisterActivity.this, "API Execution failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadUserRoles() {
        Call<List<UserRole>> call = userRoleApi.getAllUserRoles();

        call.enqueue(new Callback<List<UserRole>>() {
            @Override
            public void onResponse(Call<List<UserRole>> call, Response<List<UserRole>> response) {
                Log.d(TAG, "code: " + response.code());
                if (response.code() == 200) {
                    Toast.makeText(UserRegisterActivity.this, "Load success", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "data: " + response.body().size());
                    rolesSrc = response.body();
                    reloadUserRoleSpinner();
                } else {
                    Toast.makeText(UserRegisterActivity.this, "Load failed", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "Load failed: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<UserRole>> call, Throwable throwable) {

                Log.d(TAG, "Error: " + throwable.getMessage());
                Toast.makeText(UserRegisterActivity.this, "API Execution failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void reloadUserRoleSpinner() {
        Log.d(TAG, "data: " + rolesSrc.size());
        rolesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, rolesSrc);
        rolesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnRoles.setAdapter(rolesAdapter);
    }

    private void moveToHome() {
        Intent homeIntent = new Intent(this, HomeActivity.class);
        startActivity(homeIntent);
    }

}
