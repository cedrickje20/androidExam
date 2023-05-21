package com.example.practice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class AfterSubmit extends AppCompatActivity {
    TextView answer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_submit);

        answer = findViewById(R.id.recAnswer);

        Intent intent = getIntent();
        ArrayList<String> recAnswer = intent.getStringArrayListExtra("answer");
        ArrayList<String> finAnswer = new ArrayList<>();
        if(recAnswer != null){
            for(String answer: recAnswer){
                finAnswer.add(answer);
            }
        }
       //answer.setText(finAnswer);
    }
}