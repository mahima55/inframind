package com.example.fitnessapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class UserHelperClass extends AppCompatActivity {
    String bg,bp,bt,hr,ol,rl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_helper_class);
    }
    public UserHelperClass(String bg, String bp, String bt, String hr, String ol, String rl) {
        this.bg = bg;
        this.bp = bp;
        this.bt = bt;
        this.hr = hr;
        this.ol = ol;
        this.rl = rl;
    }

    public String getBg() {
        return bg;
    }

    public String getBp() {
        return bp;
    }

    public String getBt() {
        return bt;
    }

    public String getHr() {
        return hr;
    }

    public String getOl() {
        return ol;
    }

    public String getRl() {
        return rl;
    }

    public UserHelperClass() {

    }

}