package com.moutamid.myfitnesspal.models;

import java.util.ArrayList;

public class FoodItemModel {
    String cat, name, cal, fat, protein, carbon, totalServings;
    String directions;
    String ingredients;


    public FoodItemModel(String cat, String name) {
        this.cat = cat;
        this.name = name;
    }

    public FoodItemModel(String cat, String name, String cal, String fat, String protein, String carbon, String totalServings, String directions, String ingredients) {
        this.cat = cat;
        this.name = name;
        this.cal = cal;
        this.fat = fat;
        this.protein = protein;
        this.carbon = carbon;
        this.totalServings = totalServings;
        this.directions = directions;
        this.ingredients = ingredients;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCal() {
        return cal;
    }

    public void setCal(String cal) {
        this.cal = cal;
    }

    public String getFat() {
        return fat;
    }

    public void setFat(String fat) {
        this.fat = fat;
    }

    public String getProtein() {
        return protein;
    }

    public void setProtein(String protein) {
        this.protein = protein;
    }

    public String getCarbon() {
        return carbon;
    }

    public void setCarbon(String carbon) {
        this.carbon = carbon;
    }

    public String getDirections() {
        return directions;
    }

    public void setDirections(String directions) {
        this.directions = directions;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getTotalServings() {
        return totalServings;
    }

    public void setTotalServings(String totalServings) {
        this.totalServings = totalServings;
    }
}
