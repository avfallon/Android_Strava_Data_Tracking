package com.example.cs489groupproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import com.android.volley.RequestQueue;


public class MainActivity extends AppCompatActivity {
    private RequestQueue reqQueue;
    private String refreshToken;
    private SharedPreferences preferences;
    private String athleteURL = "https://www.strava.com/api/v3/athlete";
    private String activitiesURL = "https://www.strava.com/api/v3/athlete/activities";
    private String authenticationURL = "https://www.strava.com/oauth/token";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        APIModel model = new APIModel(this);
    }
}