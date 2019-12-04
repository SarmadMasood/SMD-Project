package com.example.smdproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Flights extends AppCompatActivity {

    EditText depDate;
    EditText retDate;
    EditText originPlace;
    EditText destPlace;
    Button search;

    String dep,ret,origin,dest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flights);
        depDate = findViewById(R.id.departEditText);
        retDate = findViewById(R.id.returnEditText);
        originPlace = findViewById(R.id.originEditText);
        destPlace = findViewById(R.id.DestEditText);
        search = findViewById(R.id.searchFlights);

        depDate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                dep = depDate.getText().toString();
            }
        });

        retDate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                ret = retDate.getText().toString();
            }
        });

        originPlace.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                origin = originPlace.getText().toString();
                if(origin!=null){
                    if(origin.equalsIgnoreCase("Paris")){
                        origin="PARI-sky";
                    }
                    if(origin.equalsIgnoreCase("London")){
                        origin="LOND-sky";
                    }
                    if(origin.equalsIgnoreCase("New York")){
                        origin="NYCA-sky";
                    }
                    if(origin.equalsIgnoreCase("Singapore")){
                        origin="SINS-sky";
                    }
                    if(origin.equalsIgnoreCase("Macau")){
                        origin="MFM-sky";
                    }

                    if(origin.equalsIgnoreCase("Dubai")){
                        origin="DXBA-sky";
                    }
                    if(origin.equalsIgnoreCase("Kuala Lumpur")){
                        origin="KULM-sky";
                    }
                    if(origin.equalsIgnoreCase("Istanbul")){
                        origin="ISTA-sky";
                    }
                    if(origin.equalsIgnoreCase("Tokyo")){
                        origin="TYOA-sky";
                    }
                    if(origin.equalsIgnoreCase("Prague")){
                        origin="PRG-sky";
                    }

                    if(origin.equalsIgnoreCase("Miami")){
                        origin="MIA-sky";
                    }
                    if(origin.equalsIgnoreCase("Amsterdam")){
                        origin="AMS-sky";
                    }
                    if(origin.equalsIgnoreCase("Shanghai")){
                        origin="PVG-sky";
                    }
                    if(origin.equalsIgnoreCase("Los Angeles")){
                        origin="LAX-sky";
                    }
                    if(origin.equalsIgnoreCase("Las Vegas")){
                        origin="LASA-sky";
                    }
                }else{
                    origin = "SFO-sky";
                }
            }
        });

        destPlace.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                dest = destPlace.getText().toString();
                if(dest!=null){
                    if(dest.equalsIgnoreCase("Paris")){
                        dest="PARI-sky";
                    }
                    if(dest.equalsIgnoreCase("London")){
                        dest="LOND-sky";
                    }
                    if(dest.equalsIgnoreCase("New York")){
                        dest="NYCA-sky";
                    }
                    if(dest.equalsIgnoreCase("Singapore")){
                        dest="SINS-sky";
                    }
                    if(dest.equalsIgnoreCase("Macau")){
                        dest="MFM-sky";
                    }

                    if(dest.equalsIgnoreCase("Dubai")){
                        dest="DXBA-sky";
                    }
                    if(dest.equalsIgnoreCase("Kuala Lumpur")){
                        dest="KULM-sky";
                    }
                    if(dest.equalsIgnoreCase("Istanbul")){
                        dest="ISTA-sky";
                    }
                    if(dest.equalsIgnoreCase("Tokyo")){
                        dest="TYOA-sky";
                    }
                    if(dest.equalsIgnoreCase("Prague")){
                        dest="PRG-sky";
                    }

                    if(dest.equalsIgnoreCase("Miami")){
                        dest="MIA-sky";
                    }
                    if(dest.equalsIgnoreCase("Amsterdam")){
                        dest="AMS-sky";
                    }
                    if(dest.equalsIgnoreCase("Shanghai")){
                        dest="PVG-sky";
                    }
                    if(dest.equalsIgnoreCase("Los Angeles")){
                        dest="LAX-sky";
                    }
                    if(dest.equalsIgnoreCase("Las Vegas")){
                        dest="LASA-sky";
                    }
                }
                else{
                    dest = "ORD-sky";
                }
            }
        });


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Flights.this,searchedFlights.class);
                intent.putExtra("dep",dep);
                intent.putExtra("ret",ret);
                intent.putExtra("origin",origin);
                intent.putExtra("dest",dest);
                startActivity(intent);
            }
        });

    }
}
