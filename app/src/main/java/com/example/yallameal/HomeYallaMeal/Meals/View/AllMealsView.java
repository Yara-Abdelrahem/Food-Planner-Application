package com.example.yallameal.HomeYallaMeal.Meals.View;

import com.example.yallameal.Model.Country;
import com.example.yallameal.Model.Ingredient;
import com.example.yallameal.Model.Meal;
import com.example.yallameal.Model.Category;

import java.util.List;

public interface AllMealsView {
    public void showData(List<Meal> meals);
    public void showCatregoriesData(List<Category> categories);
    public void ShowCountriesData(List<Country>countries);
    public void ShowIngredientData(List<Ingredient>ingredients);
}
