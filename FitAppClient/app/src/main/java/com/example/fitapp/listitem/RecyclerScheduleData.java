package com.example.fitapp.listitem;

public class RecyclerScheduleData {
    private int scheduleId;
    private String timeRange;
    private String trainer;

    public RecyclerScheduleData() {
    }

    public RecyclerScheduleData(int scheduleId, String timeRange, String trainer) {
        this.scheduleId = scheduleId;
        this.timeRange = timeRange;
        this.trainer = trainer;
    }

    public String getTimeRange() {
        return timeRange;
    }

    public void setTimeRange(String timeRange) {
        this.timeRange = timeRange;
    }

    public String getTrainer() {
        return trainer;
    }

    public void setTrainer(String trainer) {
        this.trainer = trainer;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }
}
