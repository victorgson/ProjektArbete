package com.example.projektarbete;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projektarbete.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class TimerActivity extends AppCompatActivity {

    TextView timerTextView;
    TextView deliveredFood;

    int timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_timer);

        TextView tf = (TextView) findViewById(R.id.thanksForOrderTV);
        TextView td = (TextView) findViewById(R.id.timeLeftTV);
        TextView ts = (TextView) findViewById(R.id.foodArriveTV);


        String timerSett = getIntent().getStringExtra("Timer");
        try {
            if (timerSett.equals("KÖR")) {
                tf.setText("Thanks for your order!");
                timer = 10000;
                if (Build.VERSION.SDK_INT >= 21) {
                    Window window = this.getWindow();
                    ((Window) window).addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                    window.setStatusBarColor(this.getResources().getColor(R.color.support_bar));
                }
                new CountDownTimer(timer + 100, 1000) {

                    public void onTick(long millisecondsUntillDone) {
                        updateTimer((int) millisecondsUntillDone / 1000);
                    }

                    public void onFinish() {
               /* MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.doorbell);
                mediaPlayer.start();*/
                        timerTextView.setText("Delivered!");
                        deliveredFood = findViewById(R.id.foodArriveTV);
                        deliveredFood.setText("");
                    }
                }.start();
            }
            if (timerSett.equals("NORUN")) {
               // if(shared pref solutions){}else {
                    tf.setText("Lets Try again!");
                    td.setVisibility(View.INVISIBLE);
                    ts.setVisibility(View.INVISIBLE);

            }
        } catch (Exception e) {
            System.out.println("no null pls");
        }


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.order);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.basket:
                        startActivity(new Intent(getApplicationContext(), CartActivity.class));
                        overridePendingTransition(0, 0);
                        return true;


                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(), Profile.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.order:
                        Intent intent = new Intent();
                        intent.putExtra("Timer", "NORUN");
                        intent.setClass(TimerActivity.this, TimerActivity.class);
                        startActivity(intent);
                        //startActivity(new Intent(getApplicationContext(), TimerActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0, 0);
                        return true;


                }


                return false;
            }
        });


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