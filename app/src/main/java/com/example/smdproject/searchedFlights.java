package com.example.smdproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class searchedFlights extends AppCompatActivity {
    String dep,ret,origin,dest, url;
    RecyclerView flightsRecycler;
    List<Flight> flights;
    List<Carrier> carriers;
    FlightsAdpter adpter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searched_flights);
        flightsRecycler = findViewById(R.id.flightsRecycler);
        Bundle extras = getIntent().getExtras();
        dep = extras.getString("dep");
        ret = extras.getString("ret");
        origin = extras.getString("origin");
        dest = extras.getString("dest");

        dep = "2019-12-06";
        if(origin==null || origin==""){
            origin = "SFO-sky";
        }

        if(dest==null || dest==""){
            dest = "ORD-sky";
        }

        flights = new ArrayList<>();
        carriers = new ArrayList<>();
        flightsRecycler = findViewById(R.id.flightsRecycler);
        flightsRecycler.setLayoutManager(new LinearLayoutManager(searchedFlights.this));
        flightsRecycler.setHasFixedSize(true);


        url = "https://skyscanner-skyscanner-flight-search-v1.p.rapidapi.com/apiservices/browseroutes/v1.0/US/USD/en-US/"+origin+"/"+dest+"/"+dep;
        System.out.println(url);
        RequestQueue requestQueue = Volley.newRequestQueue(searchedFlights.this);
        JsonObjectRequest objectRequest = new JsonObjectRequest(
                Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try
                {
                    JSONArray quotes = response.getJSONArray("Quotes");
                    for(int i=0;i<quotes.length();i++){
                        JSONObject quote = quotes.getJSONObject(i);
                        JSONObject leg = quote.getJSONObject("OutboundLeg");
                        JSONArray carrierIds = leg.getJSONArray("CarrierIds");
                        String id = carrierIds.getString(0);
                        flights.add(new Flight(id,quote.getString("MinPrice"),leg.getString("DepartureDate")));
                    }
                    JSONArray jcarriers = response.getJSONArray("Carriers");
                    for(int i=0;i<jcarriers.length();i++){
                        JSONObject carrier = jcarriers.getJSONObject(0);
                        carriers.add(new Carrier(carrier.getString("CarrierId"),carrier.getString("Name")));
                    }

                    adpter = new FlightsAdpter();
                    for(int j=0;j<carriers.size();j++){
                    for(int i=0;i<flights.size();i++){

                            if(flights.get(i).getCarrier().equalsIgnoreCase(carriers.get(j).getId())){
                                System.out.println("Name: "+carriers.get(j).getName());
                                flights.get(i).setCarrier(carriers.get(j).getName());

                            }
                        }
                    }
                    adpter.setFlights(flights);
                    flightsRecycler.setAdapter(adpter);
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                error.printStackTrace();
            }
        }
        ){
            @Override
            public java.util.Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("X-RapidAPI-Key", "42e7dc452fmsh579f44044179178p1e69bdjsn2559aaecc04b");

                return params;
            }
        };
        requestQueue.add(objectRequest);

    }
}
