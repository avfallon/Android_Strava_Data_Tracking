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
    }

    public void viewData(View v) {
        Button b = findViewById(R.id.data_button);
        b.setVisibility(View.INVISIBLE);

        TextView averageDistance = findViewById(R.id.average_distance);
        averageDistance.setText("Your average run distance is: " + HomeActivity.rd.getAverageDistance());
        averageDistance.setVisibility(View.VISIBLE);

        TextView averageInactive = findViewById(R.id.average_inactive_time);
        averageInactive.setText("On average, you spend " + HomeActivity.rd.getAverageInactiveTime() + "% of your runs inactive");
        averageInactive.setVisibility(View.VISIBLE);

        TextView averageElevationGain = findViewById(R.id.average_elevation_gain);
        averageElevationGain.setText("Your average elevation gain is: " + HomeActivity.rd.getAverageElevationGain());
        averageElevationGain.setVisibility(View.VISIBLE);
    }


}