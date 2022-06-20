package com.example.aravindapp4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class TestSpinnerActivity extends AppCompatActivity {

    Spinner StateSpinner,DistrictSpinner;
    String[] states={"--Select State--","Andhra Pradesh","Telangana"};
    String[] apdist={"-- Select District","Krishna","Guntur","East","West","Vishaka"};
    String[] tsdist={"-- Select District","RangaReddy","Medak","Warangal","Khammam"};

    ArrayAdapter<String> stateAdapter,apAdapter,tsadapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_spinner);

        StateSpinner=findViewById(R.id.stateSpinner);
        DistrictSpinner=findViewById(R.id.DistSpinner);

        stateAdapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,states);
        StateSpinner.setAdapter(stateAdapter);

        StateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if(states[i].equals("Andhra Pradesh"))
                {
                    apAdapter=new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,apdist);
                    DistrictSpinner.setAdapter(apAdapter);
                }

                else  if(states[i].equals("Telangana"))
                {
                    tsadapter=new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,tsdist);
                    DistrictSpinner.setAdapter(tsadapter);
                }
                else
                {
                    DistrictSpinner.setSelection(0);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}