package com.example.fitapp.viewholder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitapp.R;
import com.example.fitapp.listitem.RecyclerScheduleData;

import java.util.ArrayList;

public class RecyclerScheduleViewAdapter extends RecyclerView.Adapter<RecyclerScheduleViewAdapter.RecyclerScheduleViewHolder> {

    private ArrayList<RecyclerScheduleData> scheduleDataArrayList;
    private Context mcontext;
    private OnClickListener onClickListener;

    public RecyclerScheduleViewAdapter(ArrayList<RecyclerScheduleData> recyclerDataArrayList, Context mcontext) {
        this.scheduleDataArrayList = recyclerDataArrayList;
        this.mcontext = mcontext;
    }
 
    @NonNull
    @Override
    public RecyclerScheduleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate Layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rc_schedule_grid_card, parent, false);
        return new RecyclerScheduleViewHolder(view);
    }
 
    @Override
    public void onBindViewHolder(@NonNull RecyclerScheduleViewHolder holder, int position) {
        // Set the data to textview and imageview.
        RecyclerScheduleData recyclerData = scheduleDataArrayList.get(position);
        holder.timeRangeTV.setText(recyclerData.getTimeRange());
        holder.trainerTV.setText("Trainer: "+ recyclerData.getTrainer());


    }
 
    @Override
    public int getItemCount() {
        // this method returns the size of recyclerview
        return scheduleDataArrayList.size();
    }

    public interface OnClickListener {
        void onClick(int position);
    }

    // Setter for the click listener
    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
 
    // View Holder Class to handle Recycler View.
    public class RecyclerScheduleViewHolder extends RecyclerView.ViewHolder {
 
        private TextView timeRangeTV;
        private TextView trainerTV;
 
        public RecyclerScheduleViewHolder(@NonNull View itemView) {
            super(itemView);
            timeRangeTV = itemView.findViewById(R.id.lbl_timeRange);
            trainerTV = itemView.findViewById(R.id.lbl_trainer);

            // Set click listener on the ViewHolder's item view
            itemView.setOnClickListener(view -> {
                if (onClickListener != null) {
                    onClickListener.onClick(getAdapterPosition());
                }
            });
        }



        public void bind(final RecyclerScheduleData item, final AdapterView.OnItemClickListener listener) {

        }
    }
}