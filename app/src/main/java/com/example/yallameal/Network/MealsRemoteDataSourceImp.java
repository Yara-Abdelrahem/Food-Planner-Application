package com.example.yallameal.Network;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;

import com.example.yallameal.Model.CategoryResponse;
import com.example.yallameal.Model.CountryResponse;
import com.example.yallameal.Model.IngredientResponse;
import com.example.yallameal.Model.Meal;
import com.example.yallameal.Model.MealsResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MealsRemoteDataSourceImp implements MealsRemoteDataSource {

    private static MealsRemoteDataSourceImp instance;
    private MealService apiService;

    List<Meal> MealssList;
    //    https://themealdb.com/api.php
    public MealsRemoteDataSourceImp() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://themealdb.com/api/json/v1/1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(MealService.class);
    }
    public static MealsRemoteDataSourceImp getInstance(Context context){
        if (instance == null) {
            instance = new MealsRemoteDataSourceImp();
        }
        return instance;
    }

    @Override
    public void GetMealswithID(NetworkCallback networkCallback , String idValue) {
        // build your Call once
        Call<MealsResponse> call = apiService.getMealsWithID(idValue);
        // enqueue on that same Call object
        call.enqueue(new Callback<MealsResponse>() {
            @Override
            public void onResponse(Call<MealsResponse> call, Response<MealsResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    networkCallback.onSuccessMealResutl(response.body());
                    Log.i("Search with ID", response.body().getMeals().get(0).getStrMeal());
                } else {
                    networkCallback.onFailure("Empty or bad response");
                }
            }

            @Override
            public void onFailure(Call<MealsResponse> call, Throwable t) {
                networkCallback.onFailure(t.getMessage());
            }
        });
    }


    @Override
    public void GetMealswithName(NetworkCallback networkCallback, String s) {

        Call<MealsResponse> call = apiService.searchMealsByName(s);
        call.enqueue(new Callback<MealsResponse>() {
            @Override
            public void onResponse(Call<MealsResponse> call, Response<MealsResponse> response) {
                if (response.isSuccessful()){
                    Log.i(TAG , "on response : Callback"+response.raw()+response.body());
                    networkCallback.onSuccessMealResutl(response.body());

                }
            }

            @Override
            public void onFailure(Call<MealsResponse> call, Throwable t) {
                Log.i(TAG , "OnFailure Callback");
                networkCallback.onFailure("Erorrrrrrrrrrrrrrrr"+t.getMessage());
                t.printStackTrace();

            }
        });
    }

    @Override
    public void GetMealswithCategory(NetworkCallback networkCallback, String s) {

        Call<MealsResponse> call = apiService.filterMealsByCategory(s);
        call.enqueue(new Callback<MealsResponse>() {
            @Override
            public void onResponse(Call<MealsResponse> call, Response<MealsResponse> response) {
                if (response.isSuccessful()){
                    Log.i(TAG , "on response : Callback"+response.raw()+response.body());
                    networkCallback.onSuccessMealResutl(response.body());

                }
            }

            @Override
            public void onFailure(Call<MealsResponse> call, Throwable t) {
                Log.i(TAG , "OnFailure Callback");
                networkCallback.onFailure("Erorrrrrrrrrrrrrrrr"+t.getMessage());
                t.printStackTrace();

            }
        });
    }

    @Override
    public void GetMealswithIngredient(NetworkCallback networkCallback, String s) {

        Call<MealsResponse> call = apiService.filterMealsByIngredient(s);
        call.enqueue(new Callback<MealsResponse>() {
            @Override
            public void onResponse(Call<MealsResponse> call, Response<MealsResponse> response) {
                if (response.isSuccessful()){
                    Log.i(TAG , "on response : Callback"+response.raw()+response.body());
                    networkCallback.onSuccessMealResutl(response.body());

                }
            }

            @Override
            public void onFailure(Call<MealsResponse> call, Throwable t) {
                Log.i(TAG , "OnFailure Callback");
                networkCallback.onFailure("Erorrrrrrrrrrrrrrrr"+t.getMessage());
                t.printStackTrace();

            }
        });
    }

    @Override
    public void GetMealswithArea(NetworkCallback networkCallback, String s) {

        Call<MealsResponse> call = apiService.filterMealsByArea(s);
        call.enqueue(new Callback<MealsResponse>() {
            @Override
            public void onResponse(Call<MealsResponse> call, Response<MealsResponse> response) {
                if (response.isSuccessful()){
                    Log.i(TAG , "on response : Callback"+response.raw()+response.body());
                    networkCallback.onSuccessMealResutl(response.body());

                }
            }

            @Override
            public void onFailure(Call<MealsResponse> call, Throwable t) {
                Log.i(TAG , "OnFailure Callback");
                networkCallback.onFailure("Erorrrrrrrrrrrrrrrr"+t.getMessage());
                t.printStackTrace();

            }
        });
    }

//    public void GetMealsWithCategory(NetworkCallback networkCallback) {
//
//        Call<MealsResponse> call = apiService.getMealCategories();
//        apiService.getMealsWithID().enqueue(new Callback<MealsResponse>() {
//            @Override
//            public void onResponse(Call<MealsResponse> call, Response<MealsResponse> response) {
//                if (response.isSuccessful()){
//                    Log.i(TAG , "on response : Callback"+response.raw()+response.body());
//                    networkCallback.onSuccessMealResutl(response.body());
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<MealsResponse> call, Throwable t) {
//                Log.i(TAG , "OnFailure Callback");
//                networkCallback.onFailure("Erorrrrrrrrrrrrrrrr"+t.getMessage());
//                t.printStackTrace();
//
//            }
//        });
//    }
//
//    public void GetMealswithArea(NetworkCallback networkCallback) {
//
//        Call<MealsResponse> call = apiService.ge("");
//        apiService.getMealsWithID().enqueue(new Callback<MealsResponse>() {
//            @Override
//            public void onResponse(Call<MealsResponse> call, Response<MealsResponse> response) {
//                if (response.isSuccessful()){
//                    Log.i(TAG , "on response : Callback"+response.raw()+response.body());
//                    networkCallback.onSuccessMealResutl(response.body());
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<MealsResponse> call, Throwable t) {
//                Log.i(TAG , "OnFailure Callback");
//                networkCallback.onFailure("Erorrrrrrrrrrrrrrrr"+t.getMessage());
//                t.printStackTrace();
//
//            }
//        });
//    }

    public void GetMealRandom(NetworkCallback networkCallback) {

        Call<MealsResponse> call = apiService.getRandomMeal();
        apiService.getRandomMeal().enqueue(new Callback<MealsResponse>() {
            @Override
            public void onResponse(Call<MealsResponse> call, Response<MealsResponse> response) {
                if (response.isSuccessful()){
                    Log.i(TAG , "GetMealRandom on response : Callback"+response.raw()+response.body().getMeals().get(0).getStrMeal());
                    networkCallback.onSuccessMealResutl(response.body());

                }
            }

            @Override
            public void onFailure(Call<MealsResponse> call, Throwable t) {
                Log.i(TAG , "GetMealRandom OnFailure Callback");
                networkCallback.onFailure("Erorrrrrrrrrrrrrrrr"+t.getMessage());
                t.printStackTrace();

            }
        });
    }

    public void GetMealCategories(NetworkCallback networkCallback) {

        Call<CategoryResponse> call = apiService.getMealCategories();
        apiService.getMealCategories().enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                if (response.isSuccessful()){
                    Log.i(TAG , "on response : Callback"+response.raw()+response.body());
                    networkCallback.onSuccessCategoryResutl(response.body());

                }
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {
                Log.i(TAG , "OnFailure Callback");
                networkCallback.onFailure("Erorrrrrrrrrrrrrrrr"+t.getMessage());
                t.printStackTrace();

            }
        });
    }

    //<CategoryResponse> getAllArea();

    public void getAllArea(NetworkCallback networkCallback) {

        Call<CountryResponse> call = apiService.getAllArea();
        apiService.getAllArea().enqueue(new Callback<CountryResponse>() {
            @Override
            public void onResponse(Call<CountryResponse> call, Response<CountryResponse> response) {
                if (response.isSuccessful()){
                    Log.i(TAG , "on response : Callback"+response.raw()+response.body());
                    networkCallback.onSuccessCountryResutl(response.body());

                }
            }

            @Override
            public void onFailure(Call<CountryResponse> call, Throwable t) {
                Log.i(TAG , "OnFailure Callback");
                networkCallback.onFailure("Erorrrrrrrrrrrrrrrr"+t.getMessage());
                t.printStackTrace();

            }
        });
    }

    //onSuccessIngredientResult
    @Override
    public void getAllIngredient(NetworkCallback networkCallback){
        Call<IngredientResponse> call = apiService.getAllIngredient();
        if (call == null) {
            Log.e(TAG, "Call returned null from Retrofit");
            return;
        }
        apiService.getAllIngredient().enqueue(new Callback<IngredientResponse>() {
            @Override
            public void onResponse(Call<IngredientResponse> call, Response<IngredientResponse> response) {
                if (response.isSuccessful()){
                    networkCallback.onSuccessIngredientResult(response.body());
                }
            }
            @Override
            public void onFailure(Call<IngredientResponse> call, Throwable t) {
                Log.i(TAG , "OnFailure Callback");
                networkCallback.onFailure("Erorrrrrrrrrrrrrrrr"+t.getMessage());
                t.printStackTrace();

            }
        });
    }
}
