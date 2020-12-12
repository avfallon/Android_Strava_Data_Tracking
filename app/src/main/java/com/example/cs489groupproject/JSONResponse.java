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
        int limit;
        try {
            String s = HomeActivity.voiceResult;
            if(s.equals("")) {
                // run without limit
                JSONArray arr = new JSONArray(jsonString);
                Log.w("parseResponseForRuns", "arr.length() = " + arr.length());
                for (int i = 0; i < arr.length(); i++) {
                    JSONObject j = arr.getJSONObject(i);
                    String name = j.getString("name");
                    double distance = j.getDouble("distance");
                    double time_moving = j.getDouble("moving_time");
                    double time_elapsed = j.getDouble("elapsed_time");
                    double elevation_gain = j.getDouble("total_elevation_gain");
                    Run r = new Run(name, distance, time_moving, time_elapsed, elevation_gain);
                    Log.w("JSONR", "made Run object");
                    Log.w("JSONR", "inside onCreate, before declaring rd");
                    HomeActivity.rd = new RunData(HomeActivity.model.getActivityResponse());
                    HomeActivity.rd.addRun(r);
                    Log.w("JSONR", "inside onCreate, after declaring rd");
                }
            }
            else {
                limit = Integer.parseInt(s);
                JSONArray arr = new JSONArray(jsonString);
                Log.w("parseResponseForRuns", "voiceLimit = " + limit);
                Log.w("parseResponseForRuns", "arr.length() = " + arr.length());
                if (limit <= arr.length()) {
                    for (int i = 0; i < limit; i++) {
                        JSONObject j = arr.getJSONObject(i);
                        String name = j.getString("name");
                        double distance = j.getDouble("distance");
                        double time_moving = j.getDouble("moving_time");
                        double time_elapsed = j.getDouble("elapsed_time");
                        double elevation_gain = j.getDouble("total_elevation_gain");
                        Run r = new Run(name, distance, time_moving, time_elapsed, elevation_gain);
                        Log.w("JSONR", "made Run object");
                        Log.w("JSONR", "inside onCreate, before declaring rd");
                        HomeActivity.rd = new RunData(HomeActivity.model.getActivityResponse());
                        HomeActivity.rd.addRun(r);
                        Log.w("JSONR", "inside onCreate, after declaring rd");
                    }
                } else {
                    // too high
                }
            }
        } catch( Exception e) {
            Log.w("JSONR", "interger parse int: " + e.toString());
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
