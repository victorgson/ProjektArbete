package com.example.projektarbete;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projektarbete.R;

public class TimerActivity extends AppCompatActivity {

    TextView timerTextView;
    TextView deliveredFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_timer);

        animation();


        new CountDownTimer(10000 + 100,1000){

            public void onTick(long millisecondsUntillDone) {
                updateTimer((int) millisecondsUntillDone /1000);
            }

            public void onFinish(){
                MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.doorbell);
                mediaPlayer.start();
                timerTextView.setText("Delivered!");
                deliveredFood = findViewById(R.id.foodArriveTV);
                deliveredFood.setText("");
            }
        }.start();
    }
    public void animation(){

        ImageView movingVamos = (ImageView) findViewById(R.id.imageViewVamosText);

        movingVamos.animate().translationXBy(1000).setDuration(10000);

    }

    public void updateTimer(int secondsLeft) {
        timerTextView = findViewById(R.id.timeLeftTV);
        int minutes = secondsLeft / 60;
        int seconds = secondsLeft - (minutes * 60);

        String minuteString = Integer.toString(minutes);
        String secondString = Integer.toString(seconds);

        if (seconds <= 9) {
            secondString = "0" + secondString;
        }

        if (secondString.equals("0")) {
            secondString = "00";
        }
        if (minutes <= 9) {
            minuteString = "0" + minuteString;
        }

        if (minuteString.equals("0")) {
            minuteString = "00";
        }

        timerTextView.setText(minuteString + ":" + secondString);

    }

}