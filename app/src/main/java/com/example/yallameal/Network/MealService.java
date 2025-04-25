package com.example.yallameal.Network;

import com.example.yallameal.Model.Meal;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MealService {

    //www.themealdb.com/api/json/v1/1/lookup.php?i=52772
    @GET("json/v1/1/lookup.php?i=52772")
    Call<List<Meal>> getProducts();
}
