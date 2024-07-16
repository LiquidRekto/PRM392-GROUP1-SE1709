package com.example.fitapp.model;

import java.time.LocalDateTime;
import java.util.List;

public class Schedule {
    private int scheduleId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private User trainee;
    private User trainer;
    private List<Exercise> exercises;

    public Schedule() {
    }

    public Schedule(int scheduleId, LocalDateTime startTime, LocalDateTime endTime, User trainee, User trainer, List<Exercise> exercises) {
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

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
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
