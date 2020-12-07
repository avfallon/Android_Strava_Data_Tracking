package com.example.cs489groupproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class APIModel {

    private RequestQueue reqQueue;
    private String refreshToken;
    private SharedPreferences preferences;
    private String athleteURL = "https://www.strava.com/api/v3/athlete";
    private String activitiesURL = "https://www.strava.com/api/v3/athlete/activities";
    private String authenticationURL = "https://www.strava.com/oauth/token";

    public APIModel(Context context){
        reqQueue = Volley.newRequestQueue(context);
        connect();
    }

    public void connect() {

//        preferences = PreferenceManager.getDefaultSharedPreferences(this);
//        SharedPreferences.Editor editor = preferences.edit();
//        editor.putString("refreshToken", "edf4895d17c3c590c7fee640a3c3c27665ef9b24");
//        editor.commit();


        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization", "Bearer e5fe98a51ef89da9ea99abb405918cd51f92db4c");
        headers.put("accept", "application/json");
        Map<String, String> params = new HashMap<String, String>();
        params.put("after", "10");
        params.put("per_page", "30");

        request( activitiesURL, Request.Method.GET, headers, params);

        refreshToken = "edf4895d17c3c590c7fee640a3c3c27665ef9b24";
        refreshAccessCode();
    }

    // This method is not finished, it will be used to automatically generate a valid access code
    public void refreshAccessCode() {
        Map<String, String> postHeaders = new HashMap<String, String>();
        Map<String, String> postParams = new HashMap<String, String>();
        postParams.put("grant_type", "refresh_token");
        postParams.put("refresh_token", refreshToken);
        postParams.put("client_id", "56866");
        postParams.put("client_secret", "1532b3527c5e995e845bb6ac8860d09f4ee63aaa");

        request( authenticationURL, Request.Method.POST, postHeaders, postParams);
    }

    // This method actually creates the GET or POST request and adds it to the request queue
    public void request(String url, int requestMethod, Map<String, String> headers, Map<String, String> params) {
        myStrRequest strReq = new myStrRequest(requestMethod, url, new Response.Listener<String>(){
            // This is the code that is actually run when the get request is fulfilled
            @Override
            public void onResponse(String response) {
                Log.w("MA", response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //This code is executed if there is an error.
            }
        }, headers, params);
        Log.w("MA", "strReq");

        reqQueue.add(strReq);
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
            Log.w("MA", "getHeaders");
            return headers;
        }
        @Override
        protected Map<String,String> getParams() {
            return params;
        }
    }
}
