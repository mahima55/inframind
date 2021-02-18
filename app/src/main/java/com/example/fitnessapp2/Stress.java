package com.example.fitnessapp2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Stress extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stress);
        CardView yoga_data = (CardView) findViewById(R.id.yoga_data);
        yoga_data.setOnClickListener((View.OnClickListener) this);
    }

    public void onClick(View view) {
        Intent i;
        switch (view.getId()) {
            case R.id.yoga_data:
                i = new Intent(this, YogaInfo.class);
                startActivity(i);
        }
    }
}
