package com.example.yallameal.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.yallameal.Model.Meal;

import java.util.List;

@Dao
public interface MealScheduleDAO {
    @Query("SELECT*FROM favorite_meal_table")
    LiveData<List<Meal>> getAllMeals();
    @Insert
    void insertMeal(Meal prod);

    @Delete
    void deleteMeal(Meal prod);
}
