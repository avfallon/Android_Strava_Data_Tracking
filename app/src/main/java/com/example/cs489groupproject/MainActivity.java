package com.example.cs489groupproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.StringBuffer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            URL url = new URL("https://developers.strava.com/docs/reference/#api-Athletes-getLoggedInAthlete");
            HttpURLConnection con =  (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            Log.w("MA", "Here");
            int status = con.getResponseCode();
            Log.w("MA", ""+status);

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            System.out.println(content.toString());

            // System.out.println(content);
            // in.close();
            // con.disconnect();
        }
        catch(Exception me) {
            System.out.println("Catch");
        }
    }
}