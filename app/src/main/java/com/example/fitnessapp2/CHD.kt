package com.example.fitnessapp2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class CHD : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_c_h_d)
        var button: Button = findViewById(R.id.button)
        button.setOnClickListener(View.OnClickListener {

            var ed1: EditText = findViewById(R.id.editText1)
            var ed2: EditText = findViewById(R.id.editText2)


            var tv : TextView = findViewById(R.id.textView)
            tv.setText("You are not likely to have coronary heart disease");


        })
    }
}