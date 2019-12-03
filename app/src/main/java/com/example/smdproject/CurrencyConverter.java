package com.example.smdproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.BreakIterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class CurrencyConverter extends AppCompatActivity {
    public static BreakIterator data;
    List<String> keysList;
    Spinner toCurrency;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_converter);
        toCurrency = (Spinner) findViewById(R.id.planets_spinner);
        final EditText edtEuroValue = (EditText) findViewById(R.id.editText4);
        final Button btnConvert = (Button) findViewById(R.id.button);
        textView = (TextView) findViewById(R.id.textView7);
        try {
            loadConvTypes();
        } catch (IOException e) {
            e.printStackTrace();
        }

        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!edtEuroValue.getText().toString().isEmpty()) {
                    String toCurr = toCurrency.getSelectedItem().toString();
                    double euroVlaue = Double.valueOf(edtEuroValue.getText().toString());

                    Toast.makeText(CurrencyConverter.this, "Please Wait..", Toast.LENGTH_SHORT).show();
                    try {
                        convertCurrency(toCurr, euroVlaue);
                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(CurrencyConverter.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(CurrencyConverter.this, "Please Enter a Value to Convert..", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    public void loadConvTypes() throws IOException {

        String url = "https://api.exchangeratesapi.io/latest";

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .header("Content-Type", "application/json")
                .build();



        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                String mMessage = e.getMessage().toString();
                Log.w("failure Response", mMessage);
                Toast.makeText(CurrencyConverter.this, mMessage, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String mMessage = response.body().string();


                CurrencyConverter.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //Toast.makeText(MainActivity.this, mMessage, Toast.LENGTH_SHORT).show();
                        try {
                            JSONObject obj = new JSONObject(mMessage);
                            JSONObject  b = obj.getJSONObject("rates");

                            Iterator keysToCopyIterator = b.keys();
                            keysList = new ArrayList<String>();

                            while(keysToCopyIterator.hasNext()) {

                                String key = (String) keysToCopyIterator.next();

                                keysList.add(key);

                            }
                            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, keysList );
                            toCurrency.setAdapter(spinnerArrayAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
            }
        });
    }

    public void convertCurrency(final String toCurr, final double euroVlaue) throws IOException {

        String url = "https://api.exchangeratesapi.io/latest";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .header("Content-Type", "application/json")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                String mMessage = e.getMessage().toString();
                Log.w("failure Response", mMessage);
                Toast.makeText(CurrencyConverter.this, mMessage, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String mMessage = response.body().string();
                CurrencyConverter.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //Toast.makeText(MainActivity.this, mMessage, Toast.LENGTH_SHORT).show();
                        try {
                            JSONObject obj = new JSONObject(mMessage);
                            JSONObject  b = obj.getJSONObject("rates");

                            String val = b.getString(toCurr);

                            double output = euroVlaue*Double.valueOf(val);


                            textView.setText(String.valueOf(output));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
            }
        });
    }
}

