package com.example.aravindapp4;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.List;

public class MainActivity2 extends AppCompatActivity {


    TextView textView;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String getNameStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        textView=findViewById(R.id.txt);

        sharedPreferences=getSharedPreferences("MODE",MODE_PRIVATE);
        editor=sharedPreferences.edit();

        getNameStr=sharedPreferences.getString("username","");
        textView.setText(""+getNameStr);

        findViewById(R.id.single).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                singlePermissionMethod();
            }
        });
        findViewById(R.id.multiple).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                multiplePermissionMethod();

            }
        });
    }

    private void singlePermissionMethod() {
        Dexter.withContext(this)
                .withPermission(Manifest.permission.CAMERA)
                .withListener(new PermissionListener() {
                    @Override public void onPermissionGranted(PermissionGrantedResponse response) {
                        Toast.makeText(MainActivity2.this, "Granted\n"+response.getPermissionName(), Toast.LENGTH_SHORT).show();}
                    @Override public void onPermissionDenied(PermissionDeniedResponse response) {
                        Toast.makeText(MainActivity2.this, "Denied\n"+response.getPermissionName(), Toast.LENGTH_SHORT).show();}
                    @Override public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {/* ... */
                    token.continuePermissionRequest();
                    }
                }).check();
    }

    private void multiplePermissionMethod() {

        Dexter.withContext(this)
                .withPermissions(
                        Manifest.permission.READ_CALL_LOG,
                        Manifest.permission.READ_CONTACTS,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.ACCESS_FINE_LOCATION
                ).withListener(new MultiplePermissionsListener() {
            @Override public void onPermissionsChecked(MultiplePermissionsReport report) {
            report.areAllPermissionsGranted();
            }
            @Override public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                token.continuePermissionRequest();
            }
        }).check();

    }
}