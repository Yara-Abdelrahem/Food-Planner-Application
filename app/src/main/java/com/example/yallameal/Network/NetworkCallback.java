package com.example.yallameal.Network;

import com.example.yallameal.Model.Meal;

import java.util.List;

public interface NetworkCallback {
    public void onSuccessResutl(List<Meal> meals);
    public void onFailure(String errormsg);
}
