package com.example.fitnessapp2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DPrediction extends AppCompatActivity {
    public CardView tm,sm,ncm,um,am,asm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d_prediction);
        tm = (CardView) findViewById(R.id.track_module);
        sm = (CardView) findViewById(R.id.stress_module);
        ncm = (CardView) findViewById(R.id.nc_module);
        um = (CardView) findViewById(R.id.upload_module);
        am = (CardView) findViewById(R.id.manual_module);
        asm=  (CardView) findViewById(R.id.asthma_module);
        tm.setOnClickListener((View.OnClickListener) this);
        sm.setOnClickListener((View.OnClickListener) this);
        ncm.setOnClickListener((View.OnClickListener) this);
        um.setOnClickListener((View.OnClickListener) this);
        am.setOnClickListener((View.OnClickListener) this);
        asm.setOnClickListener((View.OnClickListener) this);
    }
    public void onClick(View view){
        Intent i;
        switch (view.getId()){
            case R.id.track_module:
                i = new Intent(this,Prediabetes.class);
                startActivity(i);
        }
        switch (view.getId()){
            case R.id.stress_module:
                i = new Intent(this,Diabetes.class);
                startActivity(i);
        }
        switch (view.getId()){
            case R.id.upload_module:
                i = new Intent(this,CHD.class);
                startActivity(i);
        }
        switch (view.getId()){
            case R.id.manual_module:
                i = new Intent(this,Bronchiectasis.class);
                startActivity(i);
        }
        switch (view.getId()){
            case R.id.nc_module:
                i = new Intent(this,Hypoxemia.class);
                startActivity(i);
        }
        switch (view.getId()){
            case R.id.asthma_module:
                i = new Intent(this,Asthma.class);
                startActivity(i);
        }
    }

}