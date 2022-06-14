package com.example.aravindapp4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int CAMERA_REQUEST_CODE = 101;
    private static final int MULTIPLE_REQUEST_CODE =201 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        findViewById(R.id.single).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              
                singlePermissionMethod1();
            }
        });
        findViewById(R.id.multiple).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                multiplePermissionMethod1();

            }
        });
    }

    private void multiplePermissionMethod1() {

        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_CONTACTS)+
                ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_CALL_LOG)+
                ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.READ_CONTACTS,
            Manifest.permission.READ_CALL_LOG,Manifest.permission.READ_EXTERNAL_STORAGE},MULTIPLE_REQUEST_CODE);
        }
        else
        {
            Toast.makeText(this, "All open Code", Toast.LENGTH_SHORT).show();
        }
    }

    private void singlePermissionMethod1() {

        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.CAMERA},CAMERA_REQUEST_CODE);
        }
        else
        {
            Toast.makeText(this, "Camera open Code", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode)
        {
            case CAMERA_REQUEST_CODE:
                if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(this, "Camera open Code", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    Toast.makeText(this, "Camera Permission Denied", Toast.LENGTH_SHORT).show();

                }
                break;

            case MULTIPLE_REQUEST_CODE:

                if(grantResults.length>0&&grantResults[0]+grantResults[1]+grantResults[2]==PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(this, "All open Code", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    Toast.makeText(this, "All Permission Denied", Toast.LENGTH_SHORT).show();

                }
                break;
        }
    }
}