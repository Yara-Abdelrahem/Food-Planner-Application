package com.example.yallameal.Model;

import androidx.lifecycle.LiveData;

import com.example.yallameal.Network.MealsRemoteDataSource;
import com.example.yallameal.Network.NetworkCallback;
import com.example.yallameal.db.MealsLocalDataSource;

import java.util.List;

public class MealRepositryImp implements MealRepositry{
    MealsRemoteDataSource remoteDataSource;
    MealsLocalDataSource mealLocalDataSource;
    private static MealRepositryImp repo = null;

    public MealRepositryImp(MealsRemoteDataSource remoteDataSource, MealsLocalDataSource productsLocalDataSource) {
        this.mealLocalDataSource = productsLocalDataSource;
        this.remoteDataSource = remoteDataSource;
    }

    public static MealRepositryImp getInstance(MealsRemoteDataSource remoteDataSource, MealsLocalDataSource mealLocalDataSource){
        if (repo==null){
            repo = new MealRepositryImp(remoteDataSource, mealLocalDataSource);
        }
        return repo;
    }
    @Override
    public LiveData<List<Meal>> getStoredMeals() {
        // return from
        return mealLocalDataSource.getAllMealsStored();
    }

    @Override
    public void getMealID_Network(NetworkCallback networkCallback , String s) {
        remoteDataSource.GetMealswithID(networkCallback ,s);
    }

    @Override
    public void getMeal_Name_Network(NetworkCallback networkCallback, String s) {
            remoteDataSource.GetMealswithName(networkCallback,s);
    }
    @Override
    public void getMeal_Category(NetworkCallback networkCallback, String s){
        remoteDataSource.GetMealswithCategory(networkCallback ,s);
    }

    @Override
    public void getMeal_Ingredient(NetworkCallback networkCallback, String s){
        remoteDataSource.GetMealswithIngredient(networkCallback,s);
    }
    @Override
    public void getMeal_Area(NetworkCallback networkCallback, String s) {
        remoteDataSource.GetMealswithArea(networkCallback , s);
    }

    @Override
    public void getRandomMeal_Network(NetworkCallback networkCallback) {
        remoteDataSource.GetMealRandom(networkCallback);
    }

    @Override
    public void getAllCategories_Network(NetworkCallback networkCallback) {
        remoteDataSource.GetMealCategories(networkCallback);
    }

    @Override
    public void getAllCountries_Network(NetworkCallback networkCallback) {
        remoteDataSource.getAllArea(networkCallback);
    }
    @Override
    public void getAllIngredient_Network(NetworkCallback networkCallback) {
        remoteDataSource.getAllIngredient(networkCallback);
    }
    @Override
    public void insertMeal(Meal meal) {
        mealLocalDataSource.insertMeal(meal);
    }

    @Override
    public void deleteMeal(Meal meal) {
        mealLocalDataSource.deleteMeal(meal);
    }

    @Override
    public LiveData<List<MealSchedule>> getAllMealsSchedDate(String date) {
        return mealLocalDataSource.getAllMealsSchedDate(date);
    }

    @Override
    public void insertschedulemeal(MealSchedule mealSchedule) {
        mealLocalDataSource.insertschedulemeal(mealSchedule);
    }
}
