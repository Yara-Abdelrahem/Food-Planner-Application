package com.example.yallameal.Model;

import java.util.List;

public class CountryResponse {
    private List<Country> meals;

    public List<Country> getCountries() {
        return meals;
    }

    public void setCountries(List<Country> meals) {
        this.meals = meals;
    }
}
