package com.example.cs489groupproject;

import androidx.activity.ComponentActivity;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.RequestQueue;
import com.android.volley.Response;

public class MainActivity extends AppCompatActivity {

    private RequestQueue reqQueue;
    private String refreshToken;

    private String athleteURL = "https://www.strava.com/api/v3/athlete";
    private String activitiesURL = "https://www.strava.com/api/v3/athlete/activities";
    private String authenticationURL = "https://www.strava.com/oauth/token";

    // added to try and parse through response
    public static JSONResponse response;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // changes to login activity
    public void login(View v) {
        Uri intentUri = Uri.parse("https://www.strava.com/oauth/mobile/authorize")
                .buildUpon()
                .appendQueryParameter("client_id", "56866")
                .appendQueryParameter("redirect_uri", "http://localhost")
                .appendQueryParameter("response_type", "code")
                .appendQueryParameter("approval_prompt", "auto")
                .appendQueryParameter("scope", "activity:read_all")
                .build();

        Intent intent = new Intent(Intent.ACTION_VIEW, intentUri);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
        else {
            Log.w("MA", "Can't find intent");
        }
    }
}