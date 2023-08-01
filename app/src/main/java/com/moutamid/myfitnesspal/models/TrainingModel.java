package com.moutamid.myfitnesspal.models;

import java.util.ArrayList;

public class TrainingModel {
    String name;
    String description;
    int gif;
    int time;
    boolean duration;
    ArrayList<String> focusAreas;

    public TrainingModel() {
    }

    public TrainingModel(String name, String description, int gif, int time, boolean duration, ArrayList<String> focusAreas) {
        this.name = name;
        this.description = description;
        this.gif = gif;
        this.time = time;
        this.duration = duration;
        this.focusAreas = focusAreas;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGif() {
        return gif;
    }

    public void setGif(int gif) {
        this.gif = gif;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public boolean isDuration() {
        return duration;
    }

    public void setDuration(boolean duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<String> getFocusAreas() {
        return focusAreas;
    }

    public void setFocusAreas(ArrayList<String> focusAreas) {
        this.focusAreas = focusAreas;
    }
}
