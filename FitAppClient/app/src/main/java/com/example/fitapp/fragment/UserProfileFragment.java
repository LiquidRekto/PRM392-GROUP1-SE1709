package com.example.fitapp.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.fitapp.R;
import com.example.fitapp.activity.LaunchActivity;
import com.example.fitapp.util.SharedPrefUtils;

public class UserProfileFragment extends Fragment {

    LinearLayout logOutSection;
    SharedPreferences sharedPrefs;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        TextView title = (TextView) getActivity().findViewById(R.id.app_bar_title);
        title.setText("Profile");
        View view = inflater.inflate(R.layout.profile_layout, container, false);
        init(view);
        return view;


    }

    public void init(View view) {
        sharedPrefs = getActivity().getSharedPreferences("com.example.fitapp", Context.MODE_PRIVATE);

        logOutSection = view.findViewById(R.id.sc_log_out);
        logOutSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performLogout();
            }
        });
    }

    private void performLogout() {
        SharedPrefUtils.detachKey(sharedPrefs, "jwt_token");
        moveToLaunch();
    }

    private void moveToLaunch() {
        Intent myIntent = new Intent(getActivity(), LaunchActivity.class);
        startActivity(myIntent);
    }
}
