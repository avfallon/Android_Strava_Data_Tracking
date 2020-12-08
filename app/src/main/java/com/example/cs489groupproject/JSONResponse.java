package com.example.cs489groupproject;

import android.util.Log;
import org.json.*;

public class JSONResponse {

    public String response;
    public String accessToken;

    public JSONResponse(String response) {
        this.response = response;
        try {
            parseResponse();
        } catch(JSONException j) {
            Log.w("JSONR", "JSON Exception: " + j);
        }
    }

    public void parseResponse() throws JSONException {
        Log.w("JSONR", "" + response);
        String jsonString = this.response ; //assign your JSON String here
        JSONObject obj = new JSONObject(jsonString);
        this.accessToken = obj.getString("access_token");
        Log.w("JSONR", ""+ accessToken);
    }

    public String getAccessToken() {
        return accessToken;
    }
}
