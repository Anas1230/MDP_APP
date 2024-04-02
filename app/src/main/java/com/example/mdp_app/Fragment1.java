package com.example.mdp_app;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;

public class Fragment1 extends Fragment {
    ImageButton imageButton;
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
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_1, container, false);
        imageButton = viewGroup.findViewById(R.id.button3);
        vr = (Vibrator)getContext().getSystemService(Context.VIBRATOR_SERVICE);
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
}