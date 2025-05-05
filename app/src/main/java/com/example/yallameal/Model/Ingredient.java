package com.example.yallameal.Model;

public class Ingredient {
    private String idIngredient;
    private String strIngredient;
    private String strDescription;
    private String strType;

    // Getters
    public String getIdIngredient() {
        return idIngredient;
    }

    public String getStrIngredient() {
        return strIngredient;
    }

    public String getStrDescription() {
        return strDescription;
    }

    public String getStrType() {
        return strType;
    }

    // Setters
    public void setIdIngredient(String idIngredient) {
        this.idIngredient = idIngredient;
    }

    public void setStrIngredient(String strIngredient) {
        this.strIngredient = strIngredient;
    }

    public void setStrDescription(String strDescription) {
        this.strDescription = strDescription;
    }

    public void setStrType(String strType) {
        this.strType = strType;
    }

    // Optional: toString
    @Override
    public String toString() {
        return "Ingredient{" +
                "idIngredient='" + idIngredient + '\'' +
                ", strIngredient='" + strIngredient + '\'' +
                ", strDescription='" + strDescription + '\'' +
                ", strType='" + strType + '\'' +
                '}';
    }
}
