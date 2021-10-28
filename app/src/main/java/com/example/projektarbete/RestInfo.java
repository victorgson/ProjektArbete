package com.example.projektarbete;

import android.annotation.SuppressLint;
import android.widget.TextView;

public class RestInfo {

    @SuppressLint("SetTextI18n")
    public void setRTxt(TextView text) {
        text.setText("Öppetider\nMån - Tors. 10.00 - 21.00\n" +
                "Fredag 10.00 - 22.00 (Drive thru 21:30)\n" +
                "Lördag 10.00 - 22.00 (Drive thru 21:30)\n" +
                "Söndag 10.00 - 21.00");


    }

    @SuppressLint("SetTextI18n")
    public void setRTxt2(TextView text) {

        text.setText((R.string.Rest2Info));


    }

}
