package com.example.cs489groupproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.StringBuffer;
import java.util.HashMap;
import java.util.Map;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


public class MainActivity extends AppCompatActivity {
    private RequestQueue reqQueue;
    private String response;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        reqQueue = Volley.newRequestQueue(this);

        Log.w("MA", "sending request");

        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization", "Bearer e5fe98a51ef89da9ea99abb405918cd51f92db4c");

        Map<String, String> params = new HashMap<String, String>();

        getRequest( "https://www.strava.com/api/v3/athlete", headers, params);



//            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
//            String inputLine;
//            StringBuffer content = new StringBuffer();
//            while ((inputLine = in.readLine()) != null) {
//                content.append(inputLine);
//            }
//            Log.w("MA", content.toString());


    }

    public void getRequest(String url, Map<String, String> headers, Map<String, String> params) {
        myStrRequest strReq = new myStrRequest(Request.Method.GET, url, new Response.Listener<String>(){
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






//    StringRequest strReq = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
//        @Override
//        public void onResponse(String response) {
//            Log.w("MA", "--"+response.substring(0,5000));
//
//        }
//    }, new Response.ErrorListener() {
//        @Override
//        public void onErrorResponse(VolleyError error) {
//        }
//    }
//    ) {
//        @Override
//        protected Map<String,String> getParams(){
//            Map<String,String> params = new HashMap<String, String>();
////                params.put("access_token", "ccc0f6d35e4936a43f433ccaa5e99fbbc242d46f");
//
//            return params;
//        }
//        @Override
//        public Map<String, String> getHeaders() throws AuthFailureError {
//            Map<String, String> params = new HashMap<String, String>();
//            params.put("Authorization", "Bearer ccc0f6d35e4936a43f433ccaa5e99fbbc242d46f");
//
//
//            return params;
//        }
//    };
//        reqQueue.add(strReq);
//                Log.w("MA", "Added");
//                }