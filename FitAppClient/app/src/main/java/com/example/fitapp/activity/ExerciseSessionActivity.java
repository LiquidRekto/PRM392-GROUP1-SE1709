package com.example.fitapp.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fitapp.R;
import com.example.fitapp.model.ExerciseStep;

import java.util.List;

public class ExerciseSessionActivity extends AppCompatActivity {
    List<ExerciseStep> exerciseStepList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise_session);
        init();
    }

    private void init() {

    }
}
