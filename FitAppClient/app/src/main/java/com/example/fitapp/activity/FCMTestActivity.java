package com.example.fitapp.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fitapp.R;
import com.example.fitapp.api.APIClient;
import com.example.fitapp.api.APIInterface;
import com.example.fitapp.model.FCMRequest;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FCMTestActivity extends AppCompatActivity {
    private static final String TAG = FCMTestActivity.class.getName();
    APIInterface api;
    EditText txtTitle, txtBody, txtToken;
    Button btnPushNoti;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fcm_test);
        Init();
        PrintToken();
    }

    private void Init() {
        api = APIClient.getClient().create(APIInterface.class);

        txtTitle = (EditText) findViewById(R.id.txt_title);
        txtBody = (EditText) findViewById(R.id.txt_body);
        txtToken = (EditText) findViewById(R.id.txt_token);

        btnPushNoti = (Button) findViewById(R.id.btn_push_noti);

        btnPushNoti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PushNotification();
            }
        });
    }

    private void PrintToken() {
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();

                        // Log and toast
                        //String msg = getString(R.string.msg_tok, token);
                        Log.d(TAG, token);
                        Toast.makeText(FCMTestActivity.this, token, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void PushNotification() {
        FCMRequest req = new FCMRequest();
        req.setTitle(txtTitle.getText().toString());
        req.setBody(txtBody.getText().toString());
        req.setToken(txtToken.getText().toString());

        Log.d(TAG, "URL: " + req);

        Call<String> call = api.pushNotification(req);
        Log.d(TAG, "URL: " + call.request().url());
        Log.d(TAG, call.request().body().toString());
        call.enqueue(new Callback<String>() {

            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.code() == 200) {
                    Toast.makeText(FCMTestActivity.this, "Call success", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable throwable) {
                Log.d(TAG, "URL: " + throwable.getMessage());
                Toast.makeText(FCMTestActivity.this, "Push notification failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
