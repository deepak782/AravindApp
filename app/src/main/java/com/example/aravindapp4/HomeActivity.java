package com.example.aravindapp4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    TextView mobile;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    String mobStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mobile=findViewById(R.id.mobile_txt);
        sharedPreferences=getSharedPreferences("LOGINMODE",MODE_PRIVATE);
        editor=sharedPreferences.edit();

        mobStr=sharedPreferences.getString("mobile","");
        mobile.setText(""+mobStr);

        findViewById(R.id.logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.clear();
                editor.commit();

                finish();
                startActivity(new Intent(HomeActivity.this,TestLoginActivity.class));



            }
        });
    }
}