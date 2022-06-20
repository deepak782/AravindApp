package com.example.aravindapp4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SharedPrefrencesActivity extends AppCompatActivity {

    EditText name;
    TextView savedValue;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String nameStr,getNameStr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_prefrences);

        name=findViewById(R.id.name_Edt);
        savedValue=findViewById(R.id.saved_txt);

        sharedPreferences=getSharedPreferences("MODE",MODE_PRIVATE);
        editor=sharedPreferences.edit();


        findViewById(R.id.save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nameStr=name.getText().toString();


                editor.putString("username",nameStr);

                editor.commit();

                Toast.makeText(SharedPrefrencesActivity.this, "Saved", Toast.LENGTH_SHORT).show();

            }
        });

        findViewById(R.id.read).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sharedPreferences=getSharedPreferences("MODE",MODE_PRIVATE);
                editor=sharedPreferences.edit();

                getNameStr=sharedPreferences.getString("username","");
                savedValue.setText(""+getNameStr);


            }
        });

        findViewById(R.id.next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(SharedPrefrencesActivity.this,MainActivity2.class));
            }
        });
    }
}