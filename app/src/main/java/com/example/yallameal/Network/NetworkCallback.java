package com.example.yallameal.Network;

import com.example.yallameal.Model.CategoryResponse;
import com.example.yallameal.Model.CountryResponse;
import com.example.yallameal.Model.IngredientResponse;
import com.example.yallameal.Model.MealsResponse;

public interface NetworkCallback {
    public void onSuccessMealResutl(MealsResponse meals);
    public void onSuccessCategoryResutl(CategoryResponse categories);
    public void onSuccessCountryResutl(CountryResponse countries);
    public void onSuccessIngredientResult(IngredientResponse ingredients);
    public void onFailure(String errormsg);
}
