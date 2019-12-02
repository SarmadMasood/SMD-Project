package com.example.smdproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class WorldClock extends AppCompatActivity {
    private TextView textCurrentTime;
    String time;
    private Date now = Calendar.getInstance().getTime();
    Calendar calendar = GregorianCalendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_world_clock);

        textCurrentTime = findViewById(R.id.text_currentTime);
        calendar.setTime(now);
        time = "";
        int h = calendar.get(Calendar.HOUR_OF_DAY), m = calendar.get(Calendar.MINUTE), s = calendar.get(Calendar.SECOND);
        if(h < 10) { time += "0"; } time += h + " : "; if(m < 10) { time += "0"; } time += m + " : "; if(s < 10) { time += "0"; } time += s;
        textCurrentTime.setText(time);

        Thread thread = new Thread() {

            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                updateTextView();
                            }
                        });
                    }
                } catch (InterruptedException e) {
                    Toast.makeText(getApplicationContext(), "Inturrepted", Toast.LENGTH_SHORT).show();
                }
            }
        };
        thread.start();
    }
    private void updateTextView() {
        String hrs, mins, secs;
        int hour, minute, second;

        now = Calendar.getInstance().getTime();
        calendar.setTime(now);
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);
        second = calendar.get(Calendar.SECOND);
        if (hour < 10) {
            hrs = "0" + hour;
        } else {
            hrs = Integer.toString(hour);
        }
        if (minute < 10) {
            mins = "0" + minute;
        } else {
            mins = Integer.toString(minute);
        }
        if (second < 10) {
            secs = "0" + second;
        } else {
            secs = Integer.toString(second);
        }
        time = hrs + " : " + mins + " : " + secs;
        textCurrentTime.setText(time);
    }
}
