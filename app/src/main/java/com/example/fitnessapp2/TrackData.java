package com.example.fitnessapp2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrackData extends AppCompatActivity {
    TextView textView;
    Button save_data;
    Button predict_data;
    DatabaseReference reference;
    private String datatype,value;
    private byte encryptionKey[] = {5,115,51,86,105,4,-31,-23,-60,88,17,20,3,-105,119,-53};
    private Cipher cipher,decipher;
    private SecretKeySpec secretKeySpec;

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

        reference = FirebaseDatabase.getInstance().getReference("null");

        try {
            cipher = Cipher.getInstance("AES");
            decipher.getInstance("AES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                datatype = snapshot.getValue().toString();
                value = snapshot.getValue().toString();

                String[] msg1Array = datatype.split(",");
                String[] msg2Array = value.split(",");

                String[] stringFinal1 = new String[msg1Array.length * 2];
                String[] stringFinal2 = new String[msg2Array.length * 2];

                for (int i = 0; i < msg1Array.length; i++) {
                    String[] stringKeyValue = msg1Array[i].split("", 2);
                    stringFinal1[2 * i] = (String) android.text.format.DateFormat.format("dd-MM-YYYY hh:mm:ss", Long.parseLong(stringKeyValue[0]));
                    stringFinal1[2 * i + 1] = AESDecryptionMethod(stringKeyValue[1]);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

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
    private String AESEncryptionMethod(String string)  {
        byte[] stringByte = string.getBytes();
        byte[] encryptedbyte = new byte[stringByte.length];
        try {
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            encryptedbyte = cipher.doFinal(stringByte);
        }catch (InvalidKeyException e){
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        String returnString = null;
        try {
            returnString = new String(encryptedbyte , "150-8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return returnString;
    }

    private String AESDecryptionMethod(String string) {
        byte[] EncryptedByte = new byte[0];
        try {
            EncryptedByte = string.getBytes("150-8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String decryptedString = string;

        byte[] decryption;
        try {
            decipher.init(cipher.DECRYPT_MODE,secretKeySpec);
            decryption = decipher.doFinal(EncryptedByte);
            decryptedString =new String(decryption);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return decryptedString;
    }


    private void openActivity() {
        Intent intent = new Intent(this,DiseaseInfo.class);
        startActivity(intent);
    }

    private void insertData() {
        Date date =new Date();
        reference.child(String.valueOf(date.getTime())).setValue(AESEncryptionMethod(textView.getText().toString()));
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