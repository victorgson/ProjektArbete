package com.example.projektarbete;

import android.annotation.SuppressLint;
import android.widget.TextView;

public class RestInfo {

    @SuppressLint("SetTextI18n")
    public void setRTKt(TextView text) {
        text.setText("Öppetider\nMån - Sön: 10.00 - 21.00\nLeveranstid: 30-40 min\nLeveranskostnad: 49 SEK");


    }

    @SuppressLint("SetTextI18n")
    public void setRTMax(TextView text) {

        text.setText("Öppetider\nMån - Sön: 10.00 - 22.00\nLeveranstid: 20-30 min\nLeveranskostnad: 59 SEK");


    }
    @SuppressLint("SetTextI18n")
    public void setRTBombay(TextView text) {

        text.setText("Öppetider\nMån - Sön: 10.00 - 22.30\nLeveranstid: 40-50 min\nLeveranskostnad: 79 SEK");


    }
    @SuppressLint("SetTextI18n")
    public void setRTKfc(TextView text) {

        text.setText("Öppetider\nMån - Sön: 10.00 - 23.00\nLeveranstid: 20-30 min\nLeveranskostnad: 39 SEK");


    }

}
