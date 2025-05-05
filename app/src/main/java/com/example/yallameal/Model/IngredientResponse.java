package com.example.yallameal.Model;

import java.util.List;

public class IngredientResponse {
    private List<Ingredient> meals;

    // Getter
    public List<Ingredient> getIngredients() {
        return meals;
    }

    // Setter
    public void setIngredients(List<Ingredient> i) {
        this.meals = i;
    }

}
