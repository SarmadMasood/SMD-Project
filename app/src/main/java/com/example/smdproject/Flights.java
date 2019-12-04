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
