package com.example.andreas.salaryapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Main2Activity extends AppCompatActivity {
    public static final String EXTRA_NUMBER = "com.example.andreas.salaryapplication.EXTRA_NUMBER";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Button submitButton = (Button) findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity();
            }
        });
    }

    public void openMainActivity(){
        EditText editText1 = (EditText) findViewById(R.id.editText1);
        int number = Integer.parseInt(editText1.getText().toString());

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(EXTRA_NUMBER, number);
        startActivity(intent);
    }
}
