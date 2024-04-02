package com.example.mdp_app;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Locale;

public class Fragment0 extends Fragment {
    ImageButton imageButton;
    Context context;
    String logTT = "[STT]";
    Integer SttIntent;
    private SpeechRecognizer speechRecognizer;

    Vibrator vr;
    int flag = 0;
    long[] pattern = {2000, 1000, 2000, 1000};
    long[] i = {0, 0, 0, 0};
    int[] amplitudes = {0, 100, 0, 100};
    int[] j = {0, 0, 0, 0};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_0, container, false);
        imageButton = viewGroup.findViewById(R.id.button2);
        vr = (Vibrator)getContext().getSystemService(Context.VIBRATOR_SERVICE);
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(getActivity());
        speechRecognizer.setRecognitionListener(new RecognitionListener() {
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
                Log.d("Speech", "onError: " + error);
            }

            @Override
            public void onResults(Bundle results) {
                Log.d("Speech", "onResults");
                ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                if (matches != null) {
                    for (String result : matches) {
                        if (result.toLowerCase(Locale.getDefault()).contains("안녕")) {
                            // Do something when "시리야" is detected
                            break;
                        }
                    }
                }
            }

            @Override
            public void onPartialResults(Bundle partialResults) {

            }

            @Override
            public void onEvent(int eventType, Bundle params) {

            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag ++;
                if (flag == 1) {
                    if (Build.VERSION.SDK_INT >= 26) {
                        vr.vibrate(VibrationEffect.createWaveform(pattern, amplitudes, 0));
                    } else {
                        vr.vibrate(1000);
                    }
                }
                else if(flag == 2){
                    vr.cancel();
                    flag = 0;
                }
            }
        });
        flag = 0;
        return viewGroup;
    }
    private void startListening() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "ko-KR"); // 언어 설정 (한국어)
        intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 1); // 인식 결과를 몇 개까지 반환할지 설정
        speechRecognizer.startListening(intent);
    }
    @Override
    public void onResume() {
        super.onResume();
        startListening(); // 음성 인식 시작
    }
    @Override
    public void onPause() {
        super.onPause();
        speechRecognizer.stopListening(); // 음성 인식 중지
    }
}