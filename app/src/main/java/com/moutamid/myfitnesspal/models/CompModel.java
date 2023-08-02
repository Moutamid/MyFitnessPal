package com.moutamid.myfitnesspal.models;

public class CompModel {
    String id;
    double squart, deadLift, bench;
    String videoUrl;

    public CompModel() {
    }

    public CompModel(String id, double squart, double deadLift, double bench, String videoUrl) {
        this.id = id;
        this.squart = squart;
        this.deadLift = deadLift;
        this.bench = bench;
        this.videoUrl = videoUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getDeadLift() {
        return deadLift;
    }

    public void setDeadLift(double deadLift) {
        this.deadLift = deadLift;
    }

    public double getSquart() {
        return squart;
    }

    public void setSquart(double squart) {
        this.squart = squart;
    }

    public double getBench() {
        return bench;
    }

    public void setBench(double bench) {
        this.bench = bench;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
}
