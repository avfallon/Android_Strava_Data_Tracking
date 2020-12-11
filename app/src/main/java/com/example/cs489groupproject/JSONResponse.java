package com.example.cs489groupproject;

import android.util.Log;
import org.json.*;

import java.util.ArrayList;

public class JSONResponse {

    public String response;
    public String accessToken;


    public JSONResponse(String response) {
        this.response = response;
    }

    public JSONResponse(String response, String accessToken) {
        this.response = response;
        this.accessToken = accessToken;
    }

    public void parseResponseForAccessToken() throws JSONException {
        Log.w("JSONR", "in parseResponseForAccessToken: " + response);
        String jsonString = this.response ; //assign your JSON String here
        JSONObject obj = new JSONObject(jsonString);
        this.accessToken = obj.getString("access_token");
        Log.w("JSONR", " "+ accessToken);
    }

    public void parseResponseForRuns() throws JSONException {
        Log.w("JSONR", "in parseResponseForRuns: " + response);
        String jsonString = this.response ; //assign your JSON String here
        JSONArray arr = new JSONArray(jsonString);
        for(int i = 0; i < arr.length(); i++) {
            JSONObject j = arr.getJSONObject(i);
            Log.w("JSONR", "jsonobject: " + j);
            String name = j.getString("name");
            Log.w("JSONR", "name: " + name);
            double distance = j.getDouble("distance");
            Log.w("JSONR", "distance: " + distance);
            double time_moving = j.getDouble("moving_time");
            Log.w("JSONR", "moving_time: " + time_moving);
            double time_elapsed = j.getDouble("elapsed_time");
            Log.w("JSONR", "elapsed_time: " + time_elapsed);
            double elevation_gain = j.getDouble("total_elevation_gain");
            Log.w("JSONR", "elevation_gain: " + elevation_gain);
            Run r = new Run(name, distance, time_moving, time_elapsed, elevation_gain);
            Log.w("JSONR", "made Run object");

            Log.w("DA", "inside onCreate, before declaring rd");
            HomeActivity.rd = new RunData(HomeActivity.model.getActivityResponse());
            HomeActivity.rd.addRun(r);
            Log.w("DA", "inside onCreate, after declaring rd");
        }
        //this.accessToken = obj.getString("access_token");
        //Log.w("JSONR", ""+ accessToken);
    }

    public String getResponse() {
        return response;
    }

    public String getAccessToken() {
        return accessToken;
    }
}
