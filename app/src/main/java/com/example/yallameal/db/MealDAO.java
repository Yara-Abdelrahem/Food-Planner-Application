package com.example.yallameal.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.yallameal.Model.Meal;
import com.example.yallameal.Model.MealSchedule;

import java.util.List;

@Dao
public interface MealDAO {
    @Query("SELECT*FROM favorite_meal_table")
    LiveData<List<Meal>> getAllMeals();
    @Insert
    void insertMeal(Meal prod);

    @Delete
    void deleteMeal(Meal prod);
    @Insert
    void insertMealSchedule(MealSchedule mealSchedule);

    @Delete
    void deleteMealSchedule(MealSchedule mealSchedule);

//    @Query("SELECT * FROM meal_schedule_table")
//   LiveData<List<MealSchedule>> getAllMealSchedules();

    @Query("SELECT * FROM meal_schedule_table WHERE date = :date")
    LiveData<List<MealSchedule>>  getMealSchedulesByDate(String date);
}
