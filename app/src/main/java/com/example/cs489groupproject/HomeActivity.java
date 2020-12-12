package com.example.cs489groupproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    public static final int VOICE_CODE = 1;
    private static final String PREF_NAME = "StravaGroup";

    public static String voiceResult;
    public static APIModel model;
    public static RunData rd;
    protected Intent speechRecognizerIntent;
    protected SharedPreferences sharedPreferences;
    protected PackageManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        manager = getPackageManager();
        sharedPreferences = this.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);

        speechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH );
        List<ResolveInfo> list = manager.queryIntentActivities(speechRecognizerIntent, 0 );
        if( list.size() > 0 ) {
            Log.w( "MA", "System supports voice recognition" );
        } else {
            Log.w( "MA", "System does not natively support voice recognition, use another input" );
        }

        voiceResult = "";

        connectToApi();
    }

    public void connectToApi() {
        if (getIntent().getData() != null) {
            model = new APIModel(this, getIntent().getData().toString());
        } else {
            Log.w("MA", "no data");
        }
    }

    public void askPermission( ) {
        if(ContextCompat.checkSelfPermission( this, Manifest.permission.RECORD_AUDIO ) != PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions( this, new String [] { Manifest.permission.RECORD_AUDIO}, VOICE_CODE );
            Log.w( "MA", "Permission to record audio is granted" );
            listen();
        } else {
            Log.w( "MA", "Permission to record audio has been previously granted" );
            listen();
        }
    }

    public void listen() {
        speechRecognizerIntent.putExtra( RecognizerIntent.EXTRA_PROMPT, "Say a number" );
        speechRecognizerIntent.putExtra( RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM );
        startActivityForResult( speechRecognizerIntent, VOICE_CODE );
    }

    public void viewData(View v) {
        try {
            model.jsonResponse.parseResponseForRuns();
        } catch (JSONException e) {
            Log.w("HA", "inside viewData: " + e.toString());
        }
        Intent intent = new Intent( this, DataActivity.class );
        Log.w("MA", "viewData");
        startActivity(intent);
        overridePendingTransition( R.anim.fade_in_and_scale, 0 );
    }

    public void activitiesConnect(View v) {
        model.getActivities();
        Toast.makeText(this, "Connected activities",
                Toast.LENGTH_LONG).show();
    }

    public void onClick(View v) {
        askPermission();
    }

    protected void onActivityResult( int requestCode, int resultCode, Intent data ) {
        super.onActivityResult( requestCode, resultCode, data );
        Log.w("MA", "MA: inside onActivityResult, request: " + requestCode + "; resultCode: " + resultCode
                + ";data = " + data );
        try {
            if (requestCode == VOICE_CODE && resultCode == RESULT_OK && data != null) {
                ArrayList<String> returnedWords = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                float[] scores = data.getFloatArrayExtra(RecognizerIntent.EXTRA_CONFIDENCE_SCORES);
                int i = 0;
                for (String word : returnedWords) {
                    if (word != null && i < scores.length) {
                        Log.w("MA", "MA: " + word + "; " + scores[i] + " index: " + i);
                        i++;
                    }
                }
                SharedPreferences.Editor editor = getSharedPreferences(PREF_NAME, MODE_PRIVATE).edit();
                voiceResult = returnedWords.get(0);
                editor.putString("voiceResult", voiceResult);
                editor.apply();
                Log.w("HA", "voiceResult = " + voiceResult);
            }
        } catch (NullPointerException e) {
            Toast.makeText(getApplicationContext(), "Sorry! There was an error processing your speech. Please try again.", Toast.LENGTH_SHORT).show();
            Log.w("HA", "Received NPE while processing speech for int: " + e.toString());
        }
    }

    protected void onStart( ) {
        super.onStart( );
        Log.w( "MA", "Inside MainActivity::onStart" );
    }

    protected void onRestart( ) {
        super.onRestart( );
        Log.w( "MA", "Inside MainActivity::onRestart" );
    }

    protected void onResume( ) {
        super.onResume( );
        Log.w( "MA", "Inside MainActivity::onResume" );
    }

    protected void onPause( ) {
        super.onPause( );
        Log.w( "MA", "Inside MainActivity::onPause" );
    }

    protected void onStop( ) {
        super.onStop( );
        Log.w( "MA", "Inside MainActivity::onStop" );
    }

    protected void onDestroy( ) {
        super.onDestroy( );
        Log.w( "MA", "Inside MainActivity::onDestroy" );
    }

}