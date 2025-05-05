package com.example.yallameal.Network;

import com.example.yallameal.Model.CategoryResponse;
import com.example.yallameal.Model.CountryResponse;
import com.example.yallameal.Model.IngredientResponse;
import com.example.yallameal.Model.MealsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MealService {

    //www.themealdb.com/api/json/v1/1/lookup.php?i=52772
    @GET("lookup.php?i=52772")
    Call<MealsResponse> getMealsWithID();
    @GET("lookup.php")
    Call<MealsResponse> getMealsWithID(@Query("i") String id);

    // Search by name
    @GET("search.php")
    Call<MealsResponse> searchMealsByName(@Query("s") String name);

    // Filter by category
    @GET("filter.php")
    Call<MealsResponse> filterMealsByCategory(@Query("c") String category);
    @GET("filter.php")
    Call<MealsResponse> filterMealsByIngredient(@Query("i") String category);

    // Filter by area
    @GET("filter.php")
    Call<MealsResponse> filterMealsByArea(@Query("a") String area);

    @GET("random.php")
    Call<MealsResponse> getRandomMeal();

    @GET("categories.php")
    Call<CategoryResponse> getMealCategories();

    @GET("list.php?a=list")
    Call<CountryResponse> getAllArea();
    @GET("list.php?i=list")
    Call<IngredientResponse> getAllIngredient();

}
