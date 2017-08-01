package com.girtmobile.eggtimer;

import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SeekBar timerSeek = (SeekBar) findViewById(R.id.timerSeek);
        final TextView timerTxtView = (TextView) findViewById(R.id.timerTV);
        timerSeek.setMax(600);
        timerSeek.setProgress(30);

        timerSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int minutes = (int) progress /60;
                int seconds = progress - minutes * 60;

                String secondString = Integer.toString(seconds);

                if (secondString == "0") {
                    secondString = "00";
                }

                timerTxtView.setText(Integer.toString(minutes) + ":" + secondString);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
