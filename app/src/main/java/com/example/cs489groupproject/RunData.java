package com.example.cs489groupproject;

import android.util.Log;

import org.json.JSONException;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class RunData {

    ArrayList<Run> runs;
    JSONResponse jr;

    public RunData(String response) {
        runs = new ArrayList<Run>();
        jr = new JSONResponse(response);
    }

    public void addRun(Run r) {
        runs.add(r);
    }

    public double getAverageDistance() {
        double total = 0;
        int i = 0;
        while(i < runs.size()) {
            total += runs.get(i).getDistance();
            i++;
        }
        return total/i;
    }

    public double getAverageInactiveTime() {
        double total = 0;
        int i = 0;
        while(i < runs.size()) {
            if(runs.get(i).getTimeMoving() != 0) {
                double ratio = runs.get(i).getTimeMoving() / runs.get(i).getTimeElapsed();
                total += ratio;
            }
            i++;
        }
        return total/i;
    }

    public double getAverageElevationGain() {
        double total = 0;
        int i = 0;
        while(i < runs.size()) {
            total += runs.get(i).getElevationGain();
            i++;
        }
        return total/i;
    }

}
