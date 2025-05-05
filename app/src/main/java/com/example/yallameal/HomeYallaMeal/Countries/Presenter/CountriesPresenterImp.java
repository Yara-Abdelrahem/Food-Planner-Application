package com.example.yallameal.HomeYallaMeal.Countries.Presenter;

import android.content.ContentValues;
import android.util.Log;

import com.example.yallameal.HomeYallaMeal.Meals.View.AllMealsView;
import com.example.yallameal.Model.CategoryResponse;
import com.example.yallameal.Model.CountryResponse;
import com.example.yallameal.Model.IngredientResponse;
import com.example.yallameal.Model.MealRepositry;
import com.example.yallameal.Model.MealsResponse;
import com.example.yallameal.Network.NetworkCallback;

public class CountriesPresenterImp implements CountriesPresenter, NetworkCallback  {

    private AllMealsView _view;
    private MealRepositry _repo;

    public CountriesPresenterImp(AllMealsView view, MealRepositry repo){
        _view=view;
        _repo = repo;
    }
    @Override
    public void getAllCountries(){
        _repo.getAllCountries_Network(this);
    }
    @Override
    public void getMealWithArea(String s){
        _repo.getMeal_Area(this,s);
    }

    @Override
    public void onSuccessMealResutl(MealsResponse meals) {
//        Log.i(ContentValues.TAG , "Presenter Network :  "+meals.getMeals().get(0).getStrMeal());
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

    }

    @Override
    public void onFailure(String errormsg) {
        Log.i(ContentValues.TAG , "Failure msg : "+errormsg);
    }


}
