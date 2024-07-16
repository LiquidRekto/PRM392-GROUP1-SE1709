package com.example.fitapp.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitapp.R;
import com.example.fitapp.RecyclerData;
import com.example.fitapp.RecyclerViewAdapter;
import com.example.fitapp.activity.LaunchActivity;
import com.example.fitapp.util.SharedPrefUtils;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    TextView lblGreetings;
    Button btnLogout;
    SharedPreferences sharedPrefs;
    private RecyclerView recyclerView;
    private ArrayList<RecyclerData> recyclerDataArrayList;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        TextView title = (TextView) getActivity().findViewById(R.id.app_bar_title);
        title.setText("Home");
        View view = inflater.inflate(R.layout.home_layout, container, false);
        init(view);
        return view;


    }

    public void init(View view) {
        btnLogout = (Button) view.findViewById(R.id.btn_logout);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performLogout();
            }
        });

        sharedPrefs = getActivity().getSharedPreferences("com.example.fitapp", Context.MODE_PRIVATE);
        createRecycle(view);
    }

    private void performLogout() {
        SharedPrefUtils.detachKey(sharedPrefs, "jwt_token");
        moveToLaunch();
    }

    private void moveToLaunch() {
        Intent myIntent = new Intent(getActivity(), LaunchActivity.class);
        startActivity(myIntent);
    }

    private void createRecycle(View view) {
        recyclerView= view.findViewById(R.id.gv_home_dashboard);

        // created new array list..
        recyclerDataArrayList=new ArrayList<>();

        // added data to array list
        recyclerDataArrayList.add(new RecyclerData("DSA",R.drawable.ic_baseline_access_time_24));
        recyclerDataArrayList.add(new RecyclerData("JAVA",R.drawable.ic_baseline_access_time_24));
        recyclerDataArrayList.add(new RecyclerData("C++",R.drawable.ic_baseline_access_time_24));
        recyclerDataArrayList.add(new RecyclerData("Python",R.drawable.ic_baseline_access_time_24));
        recyclerDataArrayList.add(new RecyclerData("Node Js",R.drawable.ic_baseline_access_time_24));

        // added data from arraylist to adapter class.
        RecyclerViewAdapter adapter=new RecyclerViewAdapter(recyclerDataArrayList,view.getContext());

        // setting grid layout manager to implement grid view.
        // in this method '2' represents number of columns to be displayed in grid view.
        GridLayoutManager layoutManager=new GridLayoutManager(view.getContext(),2);

        // at last set adapter to recycler view.
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
