package com.moutamid.myfitnesspal.models;

public class FoodItemModel {
    String cat, name;

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
