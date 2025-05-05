package com.example.yallameal.db;

import android.content.Context;
import android.os.Handler;

import androidx.lifecycle.LiveData;

import com.example.yallameal.Model.Meal;

import java.util.List;

public class MealsLocalDataSourceImp implements MealsLocalDataSource {

    AppDB appDB;
    MealDAO mealDAO;
    LiveData<List<Meal>> storedProduct;
    private static MealsLocalDataSourceImp instance;
    Handler handler;

    private MealsLocalDataSourceImp(Context ctx) {
        //create dao
        appDB =AppDB.getInstance(ctx);
        mealDAO = appDB.getProductDAO();
        storedProduct = mealDAO.getAllMeals();

    }
    public static MealsLocalDataSourceImp getInstance(Context ctx){
        if (instance == null) {
            instance = new MealsLocalDataSourceImp(ctx);
        }
        return instance;
    }


    @Override
    public void insertMeal(Meal m) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mealDAO.insertMeal(m);
            }
        }).start();
    }

    @Override
    public void deleteMeal(Meal m) {
        new Thread(new Runnable() {
            @Override
            public void run() {

                mealDAO.deleteMeal(m);
            }
        }).start();
    }

    @Override
    public LiveData<List<Meal>> getAllMealsStored() {
        return storedProduct;
    }
}
