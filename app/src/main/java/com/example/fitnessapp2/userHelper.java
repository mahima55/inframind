package com.example.fitnessapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class userHelper extends AppCompatActivity {
    String tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_helper);

    }
    public userHelper(String tv) {
        this.tv = tv;
    }

    public String getTv() {
        return tv;
    }
}