package com.example.fitapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitapp.R;
import com.example.fitapp.listitem.RecyclerData;
import com.example.fitapp.listitem.RecyclerScheduleData;
import com.example.fitapp.viewholder.RecyclerScheduleViewAdapter;
import com.example.fitapp.viewholder.RecyclerViewAdapter;
import com.example.fitapp.model.Schedule;

import java.util.ArrayList;
import java.util.List;

public class TraineeScheduleFragment extends Fragment {

    private List<Schedule> scheduleList;
    private RecyclerView recyclerView;
    private ArrayList<RecyclerScheduleData> recyclerDataArrayList;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        TextView title = (TextView) getActivity().findViewById(R.id.app_bar_title);
        title.setText("Schedule");
        View view = inflater.inflate(R.layout.trainee_schedule, container, false);
        init(view);
        return view;


    }

    public void init(View view) {
        createRecycle(view);
    }

    private void createRecycle(View view) {
        recyclerView= view.findViewById(R.id.gv_schedule_trainee);

        // created new array list..
        recyclerDataArrayList=new ArrayList<>();

        // added data to array list
        recyclerDataArrayList.add(new RecyclerScheduleData(1, "10:00 - 12:00", "Nguyen Van A"));
        recyclerDataArrayList.add(new RecyclerScheduleData(2, "10:50 - 12:00", "Nguyen Van B"));
        recyclerDataArrayList.add(new RecyclerScheduleData(3, "10:00 - 12:50", "Nguyen Van C"));
        // added data from arraylist to adapter class.
        RecyclerScheduleViewAdapter adapter=new RecyclerScheduleViewAdapter(recyclerDataArrayList,view.getContext());

        // setting grid layout manager to implement grid view.
        // in this method '2' represents number of columns to be displayed in grid view.
        GridLayoutManager layoutManager=new GridLayoutManager(view.getContext(),1);

        // at last set adapter to recycler view.
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
