package com.example.cs489groupproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    protected SpeechRecognizer speechRecognizer;
    protected APIModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        connectToApi();
    }

    public void listen() {
        if(this.speechRecognizer != null)
            speechRecognizer.destroy();
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);

        SpeechListener speechListener = new SpeechListener();
        Intent speechIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        speechRecognizer.startListening(speechIntent);
    }

    public class SpeechListener implements RecognitionListener {

        @Override
        public void onReadyForSpeech(Bundle bundle) {
            Log.w( "MA", "MA: inside onReadyForSpeech" );
        }

        @Override
        public void onBeginningOfSpeech() {
            Log.w( "MA", "MA: inside onBeginningOfSpeech" );
        }

        @Override
        public void onRmsChanged(float v) {
            Log.w( "MA", "MA: inside onRmsChanged" );
        }

        @Override
        public void onBufferReceived(byte[] bytes) {
            Log.w( "MA", "MA: inside onBufferReceived" );
        }

        @Override
        public void onEndOfSpeech() {
            Log.w( "MA", "MA: inside onEndOfSpeech" );
        }

        @Override
        public void onError(int i) {
            Log.w( "MA", "MA: inside onError, error is " + i  );
            listen( );
        }

        @Override
        public void onResults(Bundle bundle) {
            ArrayList<String> words = bundle.getStringArrayList( SpeechRecognizer.RESULTS_RECOGNITION );
            float [] scores = bundle.getFloatArray( SpeechRecognizer.CONFIDENCE_SCORES );

            // idk what we are doing with the words
        }

        @Override
        public void onPartialResults(Bundle bundle) {
            Log.w( "MA", "MA: inside onPartialResults" );
        }

        @Override
        public void onEvent(int i, Bundle bundle) {
            Log.w( "MA", "MA: inside onEvent" );
        }
    }

    public void connectToApi() {
        if (getIntent().getData() != null) {
            model = new APIModel(this, getIntent().getData().toString());
        } else {
            Log.w("MA", "no data");
        }
    }
}