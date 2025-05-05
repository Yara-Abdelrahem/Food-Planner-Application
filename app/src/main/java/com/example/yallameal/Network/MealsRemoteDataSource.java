package com.example.yallameal.Network;

public interface MealsRemoteDataSource {
public void GetMealswithID(NetworkCallback networkCallback , String s);

    void GetMealswithName(NetworkCallback networkCallback, String s);

    void GetMealswithCategory(NetworkCallback networkCallback, String s);

    void GetMealswithIngredient(NetworkCallback networkCallback, String s);

    void GetMealswithArea(NetworkCallback networkCallback, String s);

//    public void GetMealsWithCategory(NetworkCallback networkCallback);
//
//    public void GetMealswithArea(NetworkCallback networkCallback);
    public void getAllArea(NetworkCallback networkCallback);

    public void GetMealRandom(NetworkCallback networkCallback);
    public void GetMealCategories(NetworkCallback networkCallback);

    //onSuccessIngredientResult
    void getAllIngredient(NetworkCallback networkCallback);
}
