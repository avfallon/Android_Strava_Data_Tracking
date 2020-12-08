package com.example.cs489groupproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.android.volley.RequestQueue;
import com.android.volley.Response;

public class MainActivity extends AppCompatActivity {

    private RequestQueue reqQueue;
    private String refreshToken;
    private SharedPreferences preferences;
    private String athleteURL = "https://www.strava.com/api/v3/athlete";
    private String activitiesURL = "https://www.strava.com/api/v3/athlete/activities";
    private String authenticationURL = "https://www.strava.com/oauth/token";

    public static APIModel model;
    // added to try and parse through response
    public static JSONResponse response;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        model = new APIModel(this);
    }

    // changes to login activity
    public void onClick(View v) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}