package com.example.cs489groupproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
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

}