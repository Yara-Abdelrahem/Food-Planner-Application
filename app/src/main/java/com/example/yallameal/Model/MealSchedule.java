package com.example.yallameal.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "meal_schedule_table")
public class MealSchedule extends Meal {
    @PrimaryKey(autoGenerate = true)
    private int scheduleId;

    public String date;

    public MealSchedule() {
        // Default constructor
    }
    public MealSchedule(Meal meal, String date) {
        super(meal);
        this.date = date;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}