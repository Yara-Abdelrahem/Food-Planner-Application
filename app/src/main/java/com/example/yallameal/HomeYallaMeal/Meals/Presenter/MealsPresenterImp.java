package com.example.yallameal.HomeYallaMeal.Meals.Presenter;

import android.content.ContentValues;
import android.util.Log;

import com.example.yallameal.HomeYallaMeal.Meals.View.AllMealsView;
import com.example.yallameal.HomeYallaMeal.Meals.View.IallMealClickListener;
import com.example.yallameal.Model.CountryResponse;
import com.example.yallameal.Model.IngredientResponse;
import com.example.yallameal.Model.Meal;
import com.example.yallameal.Model.MealRepositry;
import com.example.yallameal.Model.MealsResponse;
import com.example.yallameal.Model.CategoryResponse;
import com.example.yallameal.Network.NetworkCallback;

public class MealsPresenterImp implements MealsPresenter, NetworkCallback , IallMealClickListener {

    private AllMealsView _view;
    private MealRepositry _repo;

    public MealsPresenterImp(AllMealsView view, MealRepositry repo){
        _view=view;
        _repo = repo;
    }

    @Override
    public void getMealWithID(String s) {
        _repo.getMealID_Network(this ,s);

    }

    @Override
    public void getMealWithName(String s) {
        _repo.getMeal_Name_Network(this,s);
    }

    @Override
    public void getMealWithCategory(String s){
        _repo.getMeal_Category(this,s);
    }
    @Override
    public void getMealWithIngredient(String s){
        _repo.getMeal_Ingredient(this,s);
    }
    @Override
    public void getMealWithArea(String s){
        _repo.getMeal_Area(this,s);
    }

    @Override
    public void getRandomMeal() {
        _repo.getRandomMeal_Network(this);
    }
    @Override
    public void getAllCategories() {
        _repo.getAllCategories_Network(this);
    }

    @Override
    public void getAllIngredient() {
        _repo.getAllIngredient_Network(this);
    }
    @Override
    public void addToFav(Meal meal) {
        AddMealToFav(meal);
    }

    @Override
    public void onSuccessMealResutl(MealsResponse meals) {
        _view.showData(meals.getMeals());
    }

    @Override
    public void onSuccessCategoryResutl(CategoryResponse categories) {
        Log.i(ContentValues.TAG , "Presenter Network :  "+categories.getCategories().get(0).getStrCategory());
        _view.showCatregoriesData(categories.getCategories());

    }

    @Override
    public void onSuccessCountryResutl(CountryResponse countries) {
        _view.ShowCountriesData(countries.getCountries());
    }

    @Override
    public void onSuccessIngredientResult(IngredientResponse ingredients) {
        Log.d("Ingredients size: " , ""+ingredients.getIngredients().size());
        _view.ShowIngredientData(ingredients.getIngredients());
    }

    @Override
    public void onFailure(String errormsg) {
        Log.i(ContentValues.TAG , "Failure msg : "+errormsg);
    }

    @Override
    public void AddMealToFav(Meal meal) {
        _repo.insertMeal(meal);

    }

}
