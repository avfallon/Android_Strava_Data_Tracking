package com.example.cs489groupproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class MainActivity extends AppCompatActivity {

    private RequestQueue reqQueue;
    private String refreshToken;
    private InterstitialAd mInterstitialAd;
    private Button goToNextLevel;

    private String athleteURL = "https://www.strava.com/api/v3/athlete";
    private String activitiesURL = "https://www.strava.com/api/v3/athlete/activities";
    private String authenticationURL = "https://www.strava.com/oauth/token";

    // added to try and parse through response

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mInterstitialAd = newInterstitialAd();
        loadInterstitial();
        goToNextLevel = findViewById(R.id.go_to_login);
        goToNextLevel.setEnabled(false);
        goToNextLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showInterstitial();
                login();
            }
        });
    }

    // changes to login activity
    public void login() {
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

    private InterstitialAd newInterstitialAd() {
        Log.w("LA", "newInterstitialAd");
        InterstitialAd interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                goToNextLevel.setEnabled(true);
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                goToNextLevel.setEnabled(true);
            }

            @Override
            public void onAdClosed() {
                // Proceed to the next level.
                Log.w( "MA", "MA: inside onAdClosed" );
                login();
            }
        });
        return interstitialAd;
    }

    private void showInterstitial() {
        // Show the ad if it"s ready. Otherwise toast and reload the ad.
        Log.w("LA", "showInterstitial");

        if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Toast.makeText(this, "Ad did not load", Toast.LENGTH_SHORT).show();
        }

    }

    private void loadInterstitial() {
        Log.w("LA", "loadInterstitial");
        AdRequest adRequest = new AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template").build();
        mInterstitialAd.loadAd(adRequest);
    }

}