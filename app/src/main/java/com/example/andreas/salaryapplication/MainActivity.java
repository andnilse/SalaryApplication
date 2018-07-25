package com.example.andreas.salaryapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private TextView textView, textView2, textView3, textView4;
    private double salary = 0;
    private int secondsAtWork = (8 * 60 ) *60; //27000 seconds in a work day
    private double dSalary = 220*7.5;
    private double secondSalary = dSalary /secondsAtWork;


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

            textView2.setText("Daily earnings: "+String.format("%.4f", salary));

            long days = calculateDaysNew();
            textView3.setText("Working days: "+days);
            textView4.setText("Total earnings: "+String.format("%.4f", ((days-1)*dSalary+salary)));

            textView.postDelayed(this, 1000);
        }
    };

    private long calcuclateDays(){
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

        int initDay = start.get(Calendar.DAY_OF_WEEK);
        System.out.println(initDay);

        int day = Calendar.DAY_OF_WEEK;
        System.out.println(day);
        return days;
    }
    
    private int calculateDaysNew(){
        
        Calendar start = Calendar.getInstance();
        start.set(Calendar.YEAR, 2018);
        start.set(Calendar.MONTH, 6);
        start.set(Calendar.DATE, 18);
        Calendar today = Calendar.getInstance();
        today.set(Calendar.MONTH, ((today.get(Calendar.MONTH))+1));
        
        int initDay = start.get(Calendar.DAY_OF_YEAR);
        //System.out.println("Initday: "+initDay);

        int day = today.get(Calendar.DAY_OF_YEAR);
        //System.out.println("Day of week: "+day);
        int diffDays = day-initDay;
        //System.out.println("Days: "+ diffDays);
        
        int totalWorkingDays = 0;
        for(int i = 0;i< diffDays; i++){
        	start.set(Calendar.DAY_OF_YEAR, (initDay+i));
        	if(start.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && start.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
        		totalWorkingDays++;
        		//System.out.println(start.get(Calendar.DAY_OF_WEEK));
        	}
        	
        }
        //System.out.println(totalWorkingDays);
        return totalWorkingDays;
    }
}
