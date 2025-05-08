package com.example.yallameal.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.yallameal.Model.Meal;
import com.example.yallameal.Model.MealSchedule;

@Database(entities = {Meal.class, MealSchedule.class}, version = 1)
public abstract class AppDB extends RoomDatabase {
    private static AppDB instance = null;

    public abstract MealDAO getMealDAO();
    public abstract MealScheduleDAO getMealScheduleDAO();

    public static synchronized AppDB getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDB.class, "FoodPlannerDB")
                    .build();
        }
        return instance;
    }
}