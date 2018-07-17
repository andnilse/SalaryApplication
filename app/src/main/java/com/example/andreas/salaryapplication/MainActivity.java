package com.example.andreas.salaryapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private TextView textView, textView2, textView3, textView4;
    private int i=0;
    private double salary = 0;
    private int secondsAtWork = (7 * 60 + 30) *60; //27000 seconds in a work day
    private double dSalary = 220*7.5;
    private double secondSalary = dSalary /secondsAtWork;
    private int initHours, initMinutes, initSeconds;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.textView);
        textView.setText("Hoi");
        textView2 = (TextView)findViewById(R.id.textView2);
        textView2.setText("Hoi");
        textView3 = (TextView)findViewById(R.id.textView3);
        textView4 = (TextView)findViewById(R.id.textView4);

        textView.postDelayed(runnable, 1000);
    }

    private Runnable runnable = new Runnable() {
        public void run() {

            //textView.setText("The clock is: " + Calendar.getInstance().getTime());
            Calendar cal = Calendar.getInstance();
            int hours = cal.get(Calendar.HOUR_OF_DAY);
            int minutes = cal.get(Calendar.MINUTE);
            int seconds = cal.get(Calendar.SECOND);

            textView.setText("The time is: "+hours+":"+minutes+":"+seconds);
            //textView2.setText("Daily earnings: "+salary);
            if(hours<8)
                salary = 0;
            else if((hours>=8) && (hours<16)){
                int diffH = hours-8;
                int diffM = minutes;
                int diffS = seconds;
                int diffSeconds = ((diffH*60) + diffM)*60 + diffS;
                salary = secondSalary * diffSeconds;
            }
            else if (hours>=16)
                salary = dSalary;

            textView2.setText("Daily earnings: "+salary);

            Calendar start = Calendar.getInstance();
            start.set(Calendar.YEAR, 2018);
            start.set(Calendar.MONTH, 6);
            start.set(Calendar.DATE, 18);
            Calendar today = Calendar.getInstance();
            today.set(Calendar.MONTH, ((today.get(Calendar.MONTH))+1));
            System.out.println(today);
            long firstMillis = start.getTimeInMillis();
            long lastMillis = today.getTimeInMillis();
            long diff = lastMillis-firstMillis;
            long days = diff/(1000*3600*24);

            textView3.setText("Working days: "+days);
            textView4.setText("Total earnings: "+(days*dSalary+salary));

            textView.postDelayed(this, 1000);
        }
    };
}
