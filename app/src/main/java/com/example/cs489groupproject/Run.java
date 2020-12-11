package com.example.cs489groupproject;

public class Run {

    String name;
    double distance;
    double time_moving;
    double time_elapsed;
    double elevation_gain;

    public Run(String name, double distance, double time_moving, double time_elapsed, double elevation_gain) {
        this.name = name;
        this.distance = distance;
        this.time_moving = time_moving;
        this.time_elapsed = time_elapsed;
        this.elevation_gain = elevation_gain;
    }

    public double getDistance() {
        return distance;
    }

    public double getTimeMoving() {
        return time_moving;
    }

    public double getTimeElapsed() {
        return time_elapsed;
    }

    public double getElevationGain() {
        return elevation_gain;
    }

}
