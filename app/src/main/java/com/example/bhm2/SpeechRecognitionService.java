package com.example.bhm2;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class SpeechRecognitionService {
    private SpeechRecognizer mSpeechRecognizer;
    private Intent mSpeechRecognizerIntent;
    private String result;
    private Context context;
    private TextToSpeech myTTS;
    public SpeechRecognitionService(Context mContext){
        context = mContext;
        mSpeechRecognizer = SpeechRecognizer.createSpeechRecognizer(context);
        mSpeechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, true);
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 100);
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Stop");
        SetRecognitionListener();
        initializeTextToSpeech();
    }

    private void SetRecognitionListener(){
        mSpeechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle params) {

            }

            @Override
            public void onBeginningOfSpeech() {
            }

            @Override
            public void onRmsChanged(float rmsdB) {

            }

            @Override
            public void onBufferReceived(byte[] buffer) {

            }

            @Override
            public void onEndOfSpeech() {

            }

            @Override
            public void onError(int error) {
                Log.d(" VOCAL EROARE", Integer.toString(error));
                SpeechRecognitionService.this.StartListening();
            }

            @Override
            public void onResults(Bundle bundle) {
                ArrayList<String> matches = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);

                for (String asd: matches) {
                    Log.d("raspuns22", asd);
                }
                Log.d("raspuns22", matches.get(0));
                //processResult(matches.get(0));
            }

            @Override
            public void onPartialResults(Bundle partialResults) {
                ArrayList<String> matches = partialResults.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                partialResults.clear();
                Log.d("raspuns", matches.get(0));
                String input = matches.get(0);
                String output = "repeta";
                if (input.contains("băieții")|| input.contains("băieți")){
                    output = "Cine, Tuni si serak ?";
                    StopListening();
                    speak(output);
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    StartListening();
                }
                else if (input.contains("da") || input.contains("Da")){
                    output = "Ii luam in sabie da-i dracului de martalogi.";
                    StopListening();
                    speak(output);
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    StartListening();
                }
                else if (input.contains("bere")){
                    output = "merita dar nu ii dam ca trebuie sa dea burta jos";
                    StopListening();
                    speak(output);
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    StartListening();
                }
                else if (input.contains("mancare")){
                    output = "cipri e ocupat nu il deranja";
                    StopListening();
                    speak(output);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    StartListening();
                }
                matches = null;
            }

            @Override
            public void onEvent(int eventType, Bundle params) {

            }
        });
    }
    public SpeechRecognizer GetSpeechRecognizer(){
        return mSpeechRecognizer;
    }

    public void StartListening(){
        mSpeechRecognizer.startListening(mSpeechRecognizerIntent);
    }
    public void StopListening(){
        mSpeechRecognizer.stopListening();
    }


    private void speak(String message) {
        myTTS.setLanguage(Locale.getDefault());
        if (Build.VERSION.SDK_INT >= 21) {
            myTTS.speak(message, TextToSpeech.QUEUE_FLUSH, null, null);
//            myTTS.playSilentUtterance(1000, TextToSpeech.QUEUE_ADD,null);
        } else {
            myTTS.speak(message, TextToSpeech.QUEUE_FLUSH, null);
//            myTTS.playSilentUtterance(1000, TextToSpeech.QUEUE_ADD,null);
        }
    }

    private void initializeTextToSpeech() {
        myTTS = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (myTTS.getEngines().size() == 0) {
                    Toast.makeText(context, "There is no TTS engine on device", Toast.LENGTH_SHORT).show();
                } else {
                    myTTS.setLanguage(Locale.getDefault());
//                    speak("Start");
                }
            }
        });
    }
}