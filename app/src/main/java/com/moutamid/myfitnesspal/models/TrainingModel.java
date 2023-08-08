package com.moutamid.myfitnesspal.models;

import java.util.ArrayList;

public class TrainingModel {
    String name;
    String description;
    String gif;
    String focusAreas;

    public TrainingModel() {
    }

    public TrainingModel(String name, String description, String gif, String focusAreas) {
        this.name = name;
        this.description = description;
        this.gif = gif;
        this.focusAreas = focusAreas;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGif() {
        return gif;
    }

    public void setGif(String gif) {
        this.gif = gif;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFocusAreas(String focusAreas) {
        this.focusAreas = focusAreas;
    }

    public String getFocusAreas() {
        return focusAreas;
    }
}
