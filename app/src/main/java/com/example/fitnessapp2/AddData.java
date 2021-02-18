package com.example.fitnessapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class AddData extends AppCompatActivity {

    EditText bloodglucose,bloodpressure,oxylevel,heartrate,resplevel,bodyTemp;

    DatabaseReference reference;
    Button firebase_data;
    Button server_data;

    private static final String DB_URL = "jdbc:mysql://192.168.43.2/webdatabase";
    private static final String USER = "zzz";
    private static final String PASS = "xyz";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);
        bloodglucose = findViewById(R.id.text1);
        bloodpressure = findViewById(R.id.text2);
        oxylevel = findViewById(R.id.text3);
        heartrate = findViewById(R.id.text4);
        resplevel = findViewById(R.id.text5);
        bodyTemp = findViewById(R.id.text6);
        firebase_data = findViewById(R.id.firebase_data);
        server_data = findViewById(R.id.server_data);
        reference = FirebaseDatabase.getInstance().getReference().child("Employees");

        firebase_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
            }

        });
        server_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData1();
            }

        });

    }

    private void insertData1() {
        Toast.makeText(AddData.this, "Data inserted", Toast.LENGTH_SHORT).show();
    }

    public void btnConn(View view){
        Send objSend = new Send();
        objSend.execute("");
    }
    private class Send extends AsyncTask<String,String,String> {
        String msg="";
        String bg1 = bloodglucose.getText().toString();
        String bp1 = bloodpressure.getText().toString();
        String bt1 = bodyTemp.getText().toString();
        String hr1 = heartrate.getText().toString();
        String ol1 = oxylevel.getText().toString();
        String rl1 = resplevel.getText().toString();

        @Override
        protected String doInBackground(String...strings){
            try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
                if(conn == null){
                    msg="Connection goes wrong";
                }
                else{
                    String query1 = "INSERT INTO employeeind (BloodGlucose) VALUES('"+bg1+"')";
                    String query2 = "INSERT INTO employeeind (BloodPressure) VALUES('"+bp1+"')";
                    String query3 = "INSERT INTO employeeind (BodyTemperature) VALUES('"+bt1+"')";
                    String query4 = "INSERT INTO employeeind (HeartRate) VALUES('"+hr1+"')";
                    String query5 = "INSERT INTO employeeind (OxygenLevel) VALUES('"+ol1+"')";
                    String query6 = "INSERT INTO employeeind (RespirationLevel) VALUES('"+rl1+"')";
                    Statement stmt = conn.createStatement();
                    stmt.executeUpdate(query1);
                    stmt.executeUpdate(query2);
                    stmt.executeUpdate(query3);
                    stmt.executeUpdate(query4);
                    stmt.executeUpdate(query5);
                    stmt.executeUpdate(query6);
                    msg="Inserting Succesfull";
                }
                conn.close();

            }
            catch(Exception e){
                msg="Connection goes Wrong";
                e.printStackTrace();
            }
            return msg;
        }
    }

    private void insertData() {
        String bg = bloodglucose.getText().toString();
        String bp = bloodpressure.getText().toString();
        String bt = bodyTemp.getText().toString();
        String hr = heartrate.getText().toString();
        String ol = oxylevel.getText().toString();
        String rl = resplevel.getText().toString();

        UserHelperClass employee = new UserHelperClass();
        reference.push().setValue(employee);
        Toast.makeText(AddData.this,"Data Inserted",Toast.LENGTH_SHORT).show();
    }

}