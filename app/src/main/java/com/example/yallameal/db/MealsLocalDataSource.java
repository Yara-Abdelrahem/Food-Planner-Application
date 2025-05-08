package com.example.yallameal.db;

import androidx.lifecycle.LiveData;

import com.example.yallameal.Model.Meal;
import com.example.yallameal.Model.MealSchedule;

import java.util.List;

public interface MealsLocalDataSource {
    void insertMeal(Meal m);

    void deleteMeal(Meal m);

    LiveData<List<Meal>> getAllMealsStored();
    LiveData<List<MealSchedule>>getAllMealsSchedDate(String date);
    void insertschedulemeal(MealSchedule mealSchedule);
}
