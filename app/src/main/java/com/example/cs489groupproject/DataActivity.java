package com.example.cs489groupproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class DataActivity extends AppCompatActivity {

    private static final String TOAST_TEXT = "Test ads are being shown. "
            + "To show live ads, replace the ad unit ID in res/values/strings.xml with your own ad unit ID.";

    private static final int START_LEVEL = 1;
    private int mLevel;
    private Button mNextLevelButton;
    private InterstitialAd mInterstitialAd;
    private TextView mLevelTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        Log.w("MA", "DataActivity");

        // Create the next level button, which tries to show an interstitial when clicked.
        mNextLevelButton = ((Button) findViewById(R.id.data_button));
        mNextLevelButton.setEnabled(false);
        mNextLevelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showInterstitial();
            }
        });

        // Create the text view to show the level number.
        mLevelTextView = (TextView) findViewById(R.id.level);
        mLevel = START_LEVEL;

        // Create the InterstitialAd and set the adUnitId (defined in values/strings.xml).
        mInterstitialAd = newInterstitialAd();
        loadInterstitial();

        viewData();
    }

    public void viewData() {
        TextView averageDistance = findViewById(R.id.average_distance);
        averageDistance.setText("Your average run distance is: " + HomeActivity.rd.getAverageDistance());
        averageDistance.setVisibility(View.VISIBLE);

        TextView averageInactive = findViewById(R.id.average_inactive_time);
        averageInactive.setText("You were inactive " + HomeActivity.rd.getAverageInactiveTime() + "% of the time");
        averageInactive.setVisibility(View.VISIBLE);

        TextView averageElevationGain = findViewById(R.id.average_elevation_gain);
        averageElevationGain.setText("Your average elevation gain is: " + HomeActivity.rd.getAverageElevationGain());
        averageElevationGain.setVisibility(View.VISIBLE);

        ProgressBar averageSpeed = findViewById(R.id.average_speed_bar);
        ProgressBar maxSpeed = findViewById(R.id.max_speed_bar);

        int avg_speed = (int) HomeActivity.rd.getAverageSpeed();
        int max_speed = (int) HomeActivity.rd.getMaxSpeed();
        averageSpeed.setMax(max_speed);
        maxSpeed.setMax(max_speed+15);
        averageSpeed.setProgress(avg_speed);
        maxSpeed.setProgress(avg_speed);
    }

    protected void onStart( ) {
        super.onStart( );
        Log.w( "MA", "Inside DataActivity::onStart" );
    }

    protected void onRestart( ) {
        super.onRestart( );
        Log.w( "MA", "Inside DataActivity::onRestart" );
    }

    protected void onResume( ) {
        super.onResume( );
        Log.w( "MA", "Inside DataActivity::onResume" );
    }

    protected void onPause( ) {
        super.onPause( );
        Log.w( "MA", "Inside DataActivity::onPause" );
    }

    protected void onStop( ) {
        super.onStop( );
        Log.w( "MA", "Inside DataActivity::onStop" );
    }

    protected void onDestroy( ) {
        super.onDestroy( );
        Log.w( "MA", "Inside DataActivity::onDestroy" );
    }

    public void goBack( View v ) {
        finish();
        overridePendingTransition( R.anim.slide_from_left, 0 );
    }

    private InterstitialAd newInterstitialAd() {
        Log.w("DA", "newInterstitialAd");
        InterstitialAd interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                mNextLevelButton.setEnabled(true);
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                mNextLevelButton.setEnabled(true);
            }

            @Override
            public void onAdClosed() {
                // Proceed to the next level.
                Log.w( "DA", "DA: inside onAdClosed" );
                goToNextLevel();
            }
        });
        return interstitialAd;
    }

    private void showInterstitial() {
        // Show the ad if it"s ready. Otherwise toast and reload the ad.
        Log.w("DA", "showInterstitial");

        if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Toast.makeText(this, "Ad did not load", Toast.LENGTH_SHORT).show();
            goToNextLevel();
        }
    }

    private void loadInterstitial() {
        Log.w("DA", "loadInterstitial");
        // Disable the next level button and load the ad.
        mNextLevelButton.setEnabled(false);
        AdRequest adRequest = new AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template").build();
        mInterstitialAd.loadAd(adRequest);
    }

    private void goToNextLevel() {
        Log.w("DA", "goToNextLevel");
        // Show the next level and reload the ad to prepare for the level after.
        // mLevelTextView.setText("Level " + (++mLevel));
        mInterstitialAd = newInterstitialAd();
        loadInterstitial();

        Intent intent = new Intent( this, HomeActivity.class );
        startActivity( intent );
    }

}