package com.example.fitapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.fitapp.R;

public class HomeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        TextView title = (TextView) getActivity().findViewById(R.id.app_bar_title);
        title.setText("Home");
        View view = inflater.inflate(R.layout.home_layout, container, false);
        init();
        return view;


    }

    public void init() {

    }
}
