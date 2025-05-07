package com.example.yallameal.HomeYallaMeal.Categories.Presenter;

import android.content.ContentValues;
import android.util.Log;

import com.example.yallameal.HomeYallaMeal.Meals.View.AllMealsView;
import com.example.yallameal.Model.CategoryResponse;
import com.example.yallameal.Model.CountryResponse;
import com.example.yallameal.Model.IngredientResponse;
import com.example.yallameal.Model.MealRepositry;
import com.example.yallameal.Model.MealsResponse;
import com.example.yallameal.Network.NetworkCallback;

public class CategoryPresenterImp implements CategoryPresenter, NetworkCallback  {

    private AllMealsView _view;
    private MealRepositry _repo;

    public CategoryPresenterImp(AllMealsView view, MealRepositry repo){
        _view=view;
        _repo = repo;
    }
    @Override
    public void getAllCategories() {
        _repo.getAllCategories_Network(this);
        
    }

    @Override
    public void getMealWithCategory(String s){
        _repo.getMeal_Category(this,s);
    }
    @Override
    public void onSuccessMealResutl(MealsResponse meals) {
//        Log.i(ContentValues.TAG , "Presenter Network :  "+meals.getMeals().get(0).getStrMeal());
//        _view.showData(meals.getMeals());
    }

    @Override
    public void onSuccessCategoryResutl(CategoryResponse categories) {
        Log.i(ContentValues.TAG , "Presenter Network :  "+categories.getCategories());
        _view.showCatregoriesData(categories.getCategories());

    }

    @Override
    public void onSuccessCountryResutl(CountryResponse countries) {
        _view.ShowCountriesData(countries.getCountries());

    }

    @Override
    public void onSuccessIngredientResult(IngredientResponse ingredients) {

    }

    @Override
    public void onFailure(String errormsg) {
        Log.i(ContentValues.TAG , "Failure msg : "+errormsg);
    }


}
