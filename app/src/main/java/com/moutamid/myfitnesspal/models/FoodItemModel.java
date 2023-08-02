package com.moutamid.myfitnesspal.models;

import java.util.ArrayList;

public class FoodItemModel {
    String cat, name, cal, fat, protein, carbon;
    ArrayList<String> directions;
    String ingredients;


    public FoodItemModel(String cat, String name) {
        this.cat = cat;
        this.name = name;
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
}
