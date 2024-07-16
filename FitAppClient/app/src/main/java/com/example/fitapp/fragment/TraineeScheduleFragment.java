package com.example.fitapp.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitapp.R;
import com.example.fitapp.activity.FCMTestActivity;
import com.example.fitapp.activity.UserLoginActivity;
import com.example.fitapp.api.APIClient;
import com.example.fitapp.api.interfaces.APIAuthInterface;
import com.example.fitapp.api.interfaces.APIScheduleInterface;
import com.example.fitapp.listitem.RecyclerData;
import com.example.fitapp.listitem.RecyclerScheduleData;
import com.example.fitapp.model.response.TokenResponse;
import com.example.fitapp.util.SharedPrefUtils;
import com.example.fitapp.util.UserUtils;
import com.example.fitapp.viewholder.RecyclerScheduleViewAdapter;
import com.example.fitapp.viewholder.RecyclerViewAdapter;
import com.example.fitapp.model.Schedule;
import com.google.android.material.progressindicator.CircularProgressIndicator;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TraineeScheduleFragment extends Fragment {
    private static final String TAG = TraineeScheduleFragment.class.getName();
    private APIScheduleInterface scheduleApi;

    private CalendarView calDatePicker;

    private CircularProgressIndicator cpi;

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
        calDatePicker = (CalendarView) view.findViewById(R.id.cal_date_picker);
        cpi = view.findViewById(R.id.progress_circular);
        calDatePicker.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, dayOfMonth);

                Date date = calendar.getTime();
                Instant instant = date.toInstant();
                LocalDateTime ldt = instant.atOffset(ZoneOffset.UTC).toLocalDateTime();
                DateTimeFormatter fmt = DateTimeFormatter.ISO_DATE;
                performGetScheduleByDate(view, ldt.format(fmt));
            }

        });

        scheduleApi = APIClient.getClient().create(APIScheduleInterface.class);
        LocalDateTime ldt = Calendar.getInstance().getTime().toInstant().atOffset(ZoneOffset.UTC).toLocalDateTime();
        DateTimeFormatter fmt = DateTimeFormatter.ISO_DATE;
        performGetScheduleByDate(view, ldt.format(fmt));
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

    private void regenerateRecycle(View view) {
        recyclerView= view.findViewById(R.id.gv_schedule_trainee);

        // created new array list..
        recyclerDataArrayList=new ArrayList<>();

        // added data to array list
        for (Schedule s : scheduleList) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
            LocalDateTime startTime = LocalDateTime.parse(s.getStartTime(), formatter);
            LocalDateTime endTime = LocalDateTime.parse(s.getEndTime(), formatter);
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
            String timeRange = startTime.format(timeFormatter) + " - " + endTime.format(timeFormatter);

            String fullName = s.getTrainer().getFirstName() + " " + s.getTrainer().getLastName();
            recyclerDataArrayList.add(new RecyclerScheduleData(s.getScheduleId(), timeRange, fullName));
        }
        // added data from arraylist to adapter class.
        RecyclerScheduleViewAdapter adapter=new RecyclerScheduleViewAdapter(recyclerDataArrayList,view.getContext());

        // setting grid layout manager to implement grid view.
        // in this method '2' represents number of columns to be displayed in grid view.
        GridLayoutManager layoutManager=new GridLayoutManager(view.getContext(),1);

        // at last set adapter to recycler view.
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void performGetScheduleByDate(View view, String date) {
        SharedPreferences sharedPref = getActivity().getSharedPreferences("com.example.fitapp", Context.MODE_PRIVATE);
        String userId = UserUtils.getUserToken(sharedPref).getId();
        Call<List<Schedule>> call = scheduleApi.getSchedulesOfDate(userId, date);
        cpi.setVisibility(View.VISIBLE);
        call.enqueue(new Callback<List<Schedule>>() {
            @Override
            public void onResponse(Call<List<Schedule>> call, Response<List<Schedule>> response) {
                cpi.setVisibility(View.GONE);
                if (response.code() == 200) {
                    scheduleList = response.body();
                    regenerateRecycle(view);
                    Log.d(TAG, "Response: " + response.body());
                } else {
                    Toast.makeText(view.getContext(), "Login failed", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "Login failed: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Schedule>> call, Throwable throwable) {
                cpi.setVisibility(View.GONE);
                Log.d(TAG, "Error: " + throwable.getMessage());
                Toast.makeText(view.getContext(), "API Execution failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
