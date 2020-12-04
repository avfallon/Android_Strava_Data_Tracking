package com.example.cs489groupproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.StringBuffer;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            RequestQueue ExampleRequestQueue = Volley.newRequestQueue(this);
            String url = "https://developers.strava.com/docs/reference/#api-Athletes-getLoggedInAthlete";
            StringRequest ExampleStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.w("MA", ""+response.substring(0,500));
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                }
            });

            ExampleRequestQueue.add(ExampleStringRequest);

//            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
//            String inputLine;
//            StringBuffer content = new StringBuffer();
//            while ((inputLine = in.readLine()) != null) {
//                content.append(inputLine);
//            }
//            Log.w("MA", content.toString());

        }
        catch(Exception me) {
            System.out.println("Catch");
        }
    }
}