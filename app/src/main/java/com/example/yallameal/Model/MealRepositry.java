package com.example.yallameal.Model;

import androidx.lifecycle.LiveData;

import com.example.yallameal.Network.NetworkCallback;

import java.util.List;

public interface MealRepositry {
    LiveData<List<Meal>> getStoredMeals();

    void getMealID_Network(NetworkCallback networkCallback, String s);
    void getMeal_Name_Network(NetworkCallback networkCallback ,String s);

    void getMeal_Category(NetworkCallback networkCallback, String s);

    void getMeal_Ingredient(NetworkCallback networkCallback, String s);

    void getMeal_Area(NetworkCallback networkCallback, String s);

    void getRandomMeal_Network(NetworkCallback networkCallback);
    void getAllCategories_Network(NetworkCallback networkCallback);
    void getAllCountries_Network(NetworkCallback networkCallback);

    void getAllIngredient_Network(NetworkCallback networkCallback);

    void insertMeal(Meal meal);

    void deleteMeal(Meal meal);
}
