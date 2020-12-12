package com.example.cs489groupproject;

public class Run {

    String name;
    double distance;
    double time_moving;
    double time_elapsed;
    double elevation_gain;
    double max_speed;
    double average_speed;

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

    public double getMaxSpeed() { return max_speed; }

    public double getAvgSpeed() { return average_speed; }

    public void setSpeeds(double max, double average) {
        max_speed = max;
        average_speed = average;
    }

}
