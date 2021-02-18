package com.example.fitnessapp2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    public CardView tm,sm,ncm,um,am,cb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tm = (CardView) findViewById(R.id.track_module);
        sm = (CardView) findViewById(R.id.stress_module);
        ncm = (CardView) findViewById(R.id.nc_module);
        um = (CardView) findViewById(R.id.upload_module);
        am = (CardView) findViewById(R.id.manual_module);
        cb = (CardView) findViewById(R.id.chatbot_module);
        tm.setOnClickListener((View.OnClickListener) this);
        sm.setOnClickListener((View.OnClickListener) this);
        ncm.setOnClickListener((View.OnClickListener) this);
        um.setOnClickListener((View.OnClickListener) this);
        am.setOnClickListener((View.OnClickListener) this);
        cb.setOnClickListener((View.OnClickListener) this);
    }

    public void onClick(View view) {
        Intent i;
        switch (view.getId()){
            case R.id.track_module:
                i = new Intent(this,TrackData.class);
                startActivity(i);
        }
        switch (view.getId()){
            case R.id.stress_module:
                i = new Intent(this,Stress.class);
                startActivity(i);
        }
        switch (view.getId()){
            case R.id.upload_module:
                i = new Intent(this,UploadFile.class);
                startActivity(i);
        }
        switch (view.getId()){
            case R.id.manual_module:
                i = new Intent(this,AddData.class);
                startActivity(i);
        }
        switch (view.getId()){
            case R.id.nc_module:
                i = new Intent(this,ViewDetails.class);
                startActivity(i);
        }
        switch (view.getId()){
            case R.id.chatbot_module:
                i = new Intent(this,Chatbot.class);
                startActivity(i);
        }
    }
}