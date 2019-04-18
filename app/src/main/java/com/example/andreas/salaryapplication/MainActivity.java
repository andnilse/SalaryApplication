package com.example.andreas.salaryapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {
    private TextView textView, textView2, textView3, textView4, textView5;
    private double salary = 0;
    private int secondsAtWork = (8 * 60 ) *60; //27000 seconds in a work day
    private int hSalary = 0;
    private double dSalary = 0;
    private double secondSalary = 0;
    private Calendar start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        int number = intent.getIntExtra(Main2Activity.EXTRA_NUMBER, 0);
        hSalary = number;
        dSalary = hSalary*7.5;
        secondSalary = dSalary/secondsAtWork;

        textView = (TextView)findViewById(R.id.textView);
        textView.setText("Hoi");
        textView2 = (TextView)findViewById(R.id.textView2);
        textView2.setText("Hoi");
        textView3 = (TextView)findViewById(R.id.textView3);
        textView4 = (TextView)findViewById(R.id.textView4);
        textView5 = (TextView)findViewById(R.id.textView5);

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
            int days = calculateDays();
            textView3.setText("Working days: "+days);

            if(start.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && start.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY){
                if(hours<8)
                    salary = 0;
                else if((hours>=8) && (hours<16)){
                    int diffH = hours-8;
                    int diffM = minutes;
                    int diffS = seconds;

                    int diffSeconds = ((diffH*60) + diffM)*60 + diffS;
                    salary = secondSalary * diffSeconds;
                    System.out.println("Hours: "+hours+" Minutes "+ minutes+" seconds "+seconds+" diffSeconds "+diffSeconds +" salary "+salary+" secondsalary "+secondSalary);
                }
                else if (hours>=16)
                    salary = dSalary;

                textView4.setText("Total earnings: "+String.format("%.4f", ((days-1)*dSalary+salary)));

            }else{
                salary = 0;
                textView4.setText("Total yearly earnings: "+String.format("%.4f", (days*dSalary)));
            }
            textView2.setText("Daily earnings: "+String.format("%.4f", salary));
            textView5.setText(getDay()+" "+cal.get(Calendar.DATE)+" "+getMonth()+" "+cal.get(Calendar.YEAR));


            textView.postDelayed(this, 1000);
        }
    };

    private int calculateDays(){
        start = Calendar.getInstance();
        //System.out.println("The year is "+start.get(Calendar.YEAR));
        int currentYear = start.get(Calendar.YEAR);
        start.set(currentYear, 0, 1);//6-1 for juni
        //start.set(Calendar.DAY_OF_WEEK, 2);
        Calendar today = Calendar.getInstance();
        int diffDays = today.get(Calendar.DAY_OF_YEAR)-start.get(Calendar.DAY_OF_YEAR)+1;
        //System.out.println(diffDays);
        //start.set(Calendar.DAY_OF_YEAR, (start.get(Calendar.DAY_OF_YEAR)+1));

        String bool = "true";
        int i = 0, workingDays = 0;
        int initDay = start.get(Calendar.DAY_OF_YEAR);
        while(i<diffDays){
            start.set(Calendar.DAY_OF_YEAR, (initDay+i));
            bool="true";
            if(start.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && start.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                workingDays++;
                bool="false";
            }
            System.out.println("Day of week: "+start.get(Calendar.DAY_OF_WEEK)+" Weekend:"+bool+" working days: "+workingDays+" i: "+i);
            i++;
        }

        //System.out.println(diffDays+" "+i+" "+workingDays);
        //System.out.println(Calendar.DAY_OF_WEEK);

        return workingDays;
    }

    private int calculateDaysNew(){

        Calendar start = Calendar.getInstance();
        start.set(Calendar.YEAR, 2019);
        start.set(Calendar.MONTH, 1);
        start.set(Calendar.DAY_OF_MONTH, 18);
        Calendar today = Calendar.getInstance();
        today.set(Calendar.MONTH, ((today.get(Calendar.MONTH))+1));
        System.out.print(today.get(Calendar.DAY_OF_YEAR)-start.get(Calendar.DAY_OF_YEAR));

        int initDay = start.get(Calendar.DAY_OF_YEAR);
        int day = today.get(Calendar.DAY_OF_YEAR);
        int diffDays = day-initDay;
        start.set(Calendar.DAY_OF_WEEK, 2);
        System.out.println("Days: "+ diffDays);

        int totalWorkingDays = 0;
        for(int i = 0;i<= diffDays; i++){
            start.set(Calendar.DAY_OF_YEAR, (initDay+i));
            if(start.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && start.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                totalWorkingDays++;
                System.out.println(start.get(Calendar.DAY_OF_WEEK));
            }

        }
        //while(i<=diffDays)
        System.out.println(totalWorkingDays);
        return totalWorkingDays;
    }

    private String getDay(){
        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DAY_OF_WEEK);
        if(day==1)
            return "Sunday";
        if(day==2)
            return "Monday";
        if(day==3)
            return "Thuesday";
        if(day==4)
            return "Wednesday";
        if(day==5)
            return "Thursday";
        if(day==6)
            return "Friday";
        if(day==7)
            return "Saturday";
        return "";
    }
    private String getMonth(){
        Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH);
        if(month==Calendar.JANUARY)
            return "January";
        if(month==Calendar.FEBRUARY)
            return "February";
        if(month==Calendar.MARCH)
            return "March";
        if(month==Calendar.APRIL)
            return "April";
        if(month==Calendar.MAY)
            return "May";
        if(month==Calendar.JUNE)
            return "June";
        if(month==Calendar.JULY)
            return "July";
        if(month==Calendar.AUGUST)
            return "August";
        if(month==Calendar.SEPTEMBER)
            return "September";
        if(month==Calendar.OCTOBER)
            return "October";
        if(month==Calendar.NOVEMBER)
            return "November";
        if(month==Calendar.DECEMBER)
            return "December";
        return "";
    }
}
