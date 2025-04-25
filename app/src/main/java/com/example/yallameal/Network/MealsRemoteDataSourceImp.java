package com.example.yallameal.Network;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;

import com.example.yallameal.Model.Meal;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MealsRemoteDataSourceImp implements MealsRemoteDataSource {

    private static MealsRemoteDataSourceImp instance;
    private MealService apiService;
    List<Meal> productsList;
    //    https://themealdb.com/api.php

    public MealsRemoteDataSourceImp() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://themealdb.com/api/")
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
    public void GetMealswithID(NetworkCallback networkCallback) {

        productsList=new ArrayList<Meal>();
        Call<List<Meal>> call = apiService.getProducts();
        apiService.getProducts().enqueue(new Callback<List<Meal>>() {
            @Override
            public void onResponse(Call<List<Meal>> call, Response<List<Meal>> response) {
                if (response.isSuccessful()){
                    Log.i(TAG , "on response : Callback"+response.raw()+response.body().getMeal());
                    productsList.addAll(response.body().getMeal());
                    networkCallback.onSuccessResutl(productsList);

                }
            }

            @Override
            public void onFailure(Call<List<Meal>> call, Throwable t) {
                Log.i(TAG , "OnFailure Callback");
                networkCallback.onFailure(t.getMessage());
                t.printStackTrace();

            }
        });
    }
}
