package com.example.andreas.salaryapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.textView);
        textView.setText("Hoi");
        textView.postDelayed(mUpdate, 1000);
    }

    private Runnable mUpdate = new Runnable() {
        public void run() {

            textView.setText("The clock is: " + Calendar.getInstance().getTime());
            i++;
            textView.postDelayed(this, 1000);
        }
    };
}
