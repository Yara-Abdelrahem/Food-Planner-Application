package com.example.yallameal.AllMeals.Presenter;

import android.content.ContentValues;
import android.util.Log;

import com.example.yallameal.AllMeals.View.AllMealsView;
import com.example.yallameal.AllMeals.View.IallMealClickListener;
import com.example.yallameal.Model.Meal;
import com.example.yallameal.Model.MealRepositry;
import com.example.yallameal.Model.MealsResponse;
import com.example.yallameal.Network.NetworkCallback;

public class AllMealsPresenterImp implements AllMealsPresenter  , NetworkCallback , IallMealClickListener {

    private AllMealsView _view;
    private MealRepositry _repo;

    public AllMealsPresenterImp(AllMealsView view,  MealRepositry repo){
        _view=view;
        _repo = repo;
    }

    @Override
    public void getAllMeals() {
        _repo.getAllMeals_Network(this);

    }

    @Override
    public void addToFav(Meal meal) {
        AddMealToFav(meal);
    }

    @Override
    public void onSuccessResutl(MealsResponse meals) {
        //this.meals=meals.getMeals();
        Log.i(ContentValues.TAG , "Networrrrk :  "+meals.getMeals().get(0).getStrMeal());
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
