package com.example.fitnessapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrackData extends AppCompatActivity {
    TextView textView;
    Button save_data;
    Button predict_data;
    DatabaseReference reference;
    private String datatype,value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_data);
        save_data = findViewById(R.id.save_data);
        predict_data = findViewById(R.id.predict_data);
        textView = findViewById(R.id.textView);
        reference = FirebaseDatabase.getInstance().getReference().child("EmployeesRecorded");
        try{
            JSONObject obj =new JSONObject(LoadJsonFromAssest());
            JSONArray array =obj.getJSONArray("Datasource");
            for(int i=0;i<array.length();i++){
                JSONObject o =array.getJSONObject(i);
                datatype =o.getString("dataType");
                value = o.getString("value");
                textView.append(datatype+"   "+value+"\n\n");
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        save_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData();
            }
        });


        predict_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity();

            }
        });
        getData();
    }

    private void openActivity() {
        Intent intent = new Intent(this,DiseaseInfo.class);
        startActivity(intent);
    }

    private void insertData() {
        Toast.makeText(TrackData.this,"Data Inserted",Toast.LENGTH_SHORT).show();
    }

    private String LoadJsonFromAssest() {
        String json = null;
        try {
            InputStream in = this.getAssets().open("datasource.json");
            int size = in.available();
            byte[] bbuffer = new byte[size];
            in.read(bbuffer);
            in.close();
            json = new String(bbuffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return json;
    }
    private void getData(){
        Call<PostList> postList = API.getService().getPostList();
        postList.enqueue(new Callback<PostList>() {
            @Override
            public void onResponse(Call<PostList> call, Response<PostList> response) {
                PostList list = response.body();
                Toast.makeText(TrackData.this,"Success",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<PostList> call, Throwable t) {
                Toast.makeText(TrackData.this,"Error Occurred",Toast.LENGTH_SHORT).show();
            }
        });
    }

}