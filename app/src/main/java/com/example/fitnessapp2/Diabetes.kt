package com.example.fitnessapp2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.fitnessapp2.ml.Modeldiabetes
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.nio.ByteBuffer

class Diabetes : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diabetes)
        var button: Button = findViewById(R.id.button)
        button.setOnClickListener(View.OnClickListener {
            var ed1: EditText = findViewById(R.id.editText)
            var v1: Int = ed1.text.toString().toInt()

            var byteBuffer : ByteBuffer = ByteBuffer.allocate(1*4)
            byteBuffer.putInt(v1)

            val model = Modeldiabetes.newInstance(this)

// Creates inputs for reference.
            val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, 1), DataType.FLOAT32)
            inputFeature0.loadBuffer(byteBuffer)

// Runs model inference and gets result.
            val outputs = model.process(inputFeature0)
            val outputFeature0 = outputs.outputFeature0AsTensorBuffer.intArray

            var tv : TextView = findViewById(R.id.textView)
            tv.setText("You are not likely to have Diabetes")

// Releases model resources if no longer used.
            model.close()


        })
    }
}