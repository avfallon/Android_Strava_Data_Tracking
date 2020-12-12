package com.example.cs489groupproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Log;
import android.app.Activity;
import android.view.View;

import androidx.activity.ComponentActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Time;
import java.util.HashMap;
import java.util.Map;

public class APIModel {

    private Context context;
    private RequestQueue reqQueue;
    public static JSONResponse jsonResponse;

    //"https://www.strava.com/api/v3/athlete/activities?before=&after=&page=&per_page=" "Authorization: Bearer [[token]]"

    private String athleteURL = "https://www.strava.com/api/v3/athlete";
    private String activitiesURL = "https://www.strava.com/api/v3/athlete/activities";
    private String authenticationURL = "https://www.strava.com/oauth/token";

    private static final String client_id = "56866";
    private static final String client_secret = "1532b3527c5e995e845bb6ac8860d09f4ee63aaa";
    private String accessCode = "";
    private String voiceLimit;
    private String activityResponse;


    public APIModel(Context context, String authURL){
        reqQueue = Volley.newRequestQueue(context);
        this.context = context;
        authorizeAccount(authURL);
    }

    // This method takes the URL returned after login in Chrome, gets the authorization code,
    // and makes a POST request to obtain the access code and refresh code
    public void authorizeAccount(String url) {
        String authCode = url.split("&")[1].substring(5);

        Map<String, String> postHeaders = new HashMap<String, String>();
        Map<String, String> postParams = new HashMap<String, String>();
        postParams.put("client_id", client_id);
        postParams.put("client_secret", client_secret);
        postParams.put("code", authCode);
        postParams.put("grant_type", "authorization_code");

        Response.Listener<String> listener = new Response.Listener<String>(){
            // This code receives the API response
            @Override
            public void onResponse(String response) {
                Log.w("AM", "Response: "+response);
                JSONResponse json = new JSONResponse(response);
                try {
                    json.parseResponseForAccessToken();
                    accessCode = json.getAccessToken();
                } catch (Exception e) {
                    Log.w("APIModel", "exception: " + e.toString());
                }
                Log.w("AM", "Access Code: "+accessCode);
            }
        };

        request( authenticationURL, Request.Method.POST, postHeaders, postParams, listener);
    }

    // THIS WORKS AND IS USED
    public void getActivities() {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization", "Bearer "+accessCode);
//        headers.put("accept", "application/json");
        Map<String, String> params = new HashMap<String, String>();
        params.put("after", "10");

        Response.Listener<String> listener = new Response.Listener<String>(){
            // This code receives the API response
            @Override
            public void onResponse(String response) {
                Log.w("APIModel", "Activity Response: "+response);
                jsonResponse = new JSONResponse(response);
                activityResponse = response;
                //json.parseResponseForRuns();
            }
        };

        Log.w("AM", "activites request   "+accessCode);
        request( activitiesURL, Request.Method.GET, headers, params, listener);
    }

    public String getActivityResponse() {
        return activityResponse;
    }


    // This method actually creates the GET or POST request and adds it to the request queue
    public void request(String url, int requestMethod, Map<String, String> headers, Map<String, String> params, Response.Listener<String> listener) {
        myStrRequest strReq = new myStrRequest(requestMethod, url, listener, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {}
        }, headers, params);
        Log.w("AM", "Request Received");

        reqQueue.add(strReq);
    }

    public void setVoiceLimit(String s) {
        Log.w("AM", "setVoiceLimit: " + s);
        this.voiceLimit = s;
    }

    // This private class is just the StringRequest class that I overrode to allow for headers/params
    private class myStrRequest extends StringRequest {
        private Map<String, String> headers;
        private Map<String, String> params;

        public myStrRequest(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener, Map<String, String> headers, Map<String, String> params) {
            super(method, url, listener, errorListener);
            this.headers = headers;
            this.params = params;
        }

        @Override
        public Map<String, String> getHeaders() {
            return headers;
        }
        @Override
        protected Map<String,String> getParams() {
            return params;
        }
    }
}
