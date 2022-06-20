
package com.example.aravindapp4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class TestLoginActivity extends AppCompatActivity {
    
    EditText mobile,password;
    
    String mobileValue,passcodevalue,mobileStr,passcodeStr;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_login);
        mobile=findViewById(R.id.mobile_Edt);
        password=findViewById(R.id.pass_Edt);

        sharedPreferences=getSharedPreferences("LOGINMODE",MODE_PRIVATE);
        editor=sharedPreferences.edit();
        
        findViewById(R.id.Login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                mobileValue="9000733771";
                passcodevalue="123456";
                
                mobileStr=mobile.getText().toString();
                passcodeStr=password.getText().toString();
                
                if(mobileStr.trim().length()<10)
                {
                    Toast.makeText(TestLoginActivity.this, "min 10 digit", Toast.LENGTH_SHORT).show();
                }
                else  if(passcodeStr.trim().length()<6)
                {
                    Toast.makeText(TestLoginActivity.this, "min 6 char", Toast.LENGTH_SHORT).show();

                }
                
                else if(mobileStr.equals(mobileValue)&&passcodeStr.equals(passcodevalue))
                {
                    editor.putString("mobile",mobileStr);
                    editor.putBoolean("loginstatus",true);
                    editor.commit();
                    startActivity(new Intent(TestLoginActivity.this,HomeActivity.class));
                    finish();
                }
                else
                {
                    Toast.makeText(TestLoginActivity.this, "InValid Details", Toast.LENGTH_SHORT).show();
                }
                
                
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(sharedPreferences.getBoolean("loginstatus",false)==true)
        {
            startActivity(new Intent(TestLoginActivity.this,HomeActivity.class));
            finish();
        }
        else
        {
            Toast.makeText(this, "Please Login", Toast.LENGTH_SHORT).show();
        }
    }
}