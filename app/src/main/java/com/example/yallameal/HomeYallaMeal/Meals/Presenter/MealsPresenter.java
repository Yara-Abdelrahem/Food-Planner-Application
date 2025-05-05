package com.example.yallameal.HomeYallaMeal.Meals.Presenter;

import com.example.yallameal.Model.Meal;

public interface MealsPresenter {
    void getMealWithID(String s);
    void getMealWithName(String s);

    void getMealWithCategory(String s);

    void getMealWithIngredient(String s);

    void getMealWithArea(String s);

    void getRandomMeal();

    void getAllCategories();
    void getAllIngredient();

    void addToFav(Meal meal);
}
