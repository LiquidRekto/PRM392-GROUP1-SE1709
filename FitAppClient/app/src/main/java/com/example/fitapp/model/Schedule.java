package com.example.fitapp.model;

import java.time.LocalDateTime;
import java.util.List;

public class Schedule {
    private int scheduleId;
    private String startTime;
    private String endTime;
    private User trainee;
    private User trainer;
    private List<Exercise> exercises;

    public Schedule() {
    }

    public Schedule(int scheduleId, String startTime, String endTime, User trainee, User trainer, List<Exercise> exercises) {
        this.scheduleId = scheduleId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.trainee = trainee;
        this.trainer = trainer;
        this.exercises = exercises;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public User getTrainee() {
        return trainee;
    }

    public void setTrainee(User trainee) {
        this.trainee = trainee;
    }

    public User getTrainer() {
        return trainer;
    }

    public void setTrainer(User trainer) {
        this.trainer = trainer;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }
}
