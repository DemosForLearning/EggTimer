package com.girtmobile.eggtimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SeekBar timerSeek;
    TextView timerTxtView;
    Button controllerBtn;
    Boolean counterIsActive = false;
    CountDownTimer countDwnTmr;

    public void updateTimer(int secondsLeft) {
        int minutes = (int) secondsLeft /60;
        int seconds = secondsLeft - minutes * 60;

        String secondString = Integer.toString(seconds);

        if (seconds <= 9) {
            secondString = "0" + secondString;
        }

        timerTxtView.setText(Integer.toString(minutes) + ":" + secondString);
    }

    public void resetTimer() {
        timerTxtView.setText("0:30");
        timerSeek.setProgress(30);
        countDwnTmr.cancel();
        controllerBtn.setText("Go!");
        timerSeek.setEnabled(true);
        counterIsActive = false;
    }

    public void controlTimer (View view) {

        if (counterIsActive == false) {
            counterIsActive = true;
            timerSeek.setEnabled(false);
            controllerBtn.setText("Stop");
            countDwnTmr = new CountDownTimer(timerSeek.getProgress() * 1000 + 100, 1000) {

                @Override
                public void onTick(long millisUntilFinished) {
                    updateTimer((int) millisUntilFinished / 1000);
                }

                @Override
                public void onFinish() {
                    resetTimer();
                    MediaPlayer mplayer = MediaPlayer.create(getApplicationContext(), R.raw.beep);
                    mplayer.start();
                }
            }.start();
        } else {
            resetTimer();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerSeek = (SeekBar) findViewById(R.id.timerSeek);
        timerTxtView = (TextView) findViewById(R.id.timerTV);
        controllerBtn = (Button) findViewById(R.id.controllerBtn);
        timerSeek.setMax(600);
        timerSeek.setProgress(30);

        timerSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateTimer(progress);
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
