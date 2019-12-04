package com.example.smdproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Hotels extends AppCompatActivity {

    RecyclerView hotelRecycler;
    DatabaseReference db;
    List<Hotel> hotels;
    HotelsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotels);
        hotels = new ArrayList<>();
        hotelRecycler = findViewById(R.id.hotelsRecycler);
        hotelRecycler.setLayoutManager(new LinearLayoutManager(Hotels.this));
        hotelRecycler.setHasFixedSize(true);
        db = FirebaseDatabase.getInstance().getReference().child("Hotels");
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot data:dataSnapshot.getChildren()){
                    System.out.println(data.getValue(Hotel.class).getName());
                    hotels.add(new Hotel(data.getValue(Hotel.class).getName(),data.getValue(Hotel.class).getRating(),data.getValue(Hotel.class).getImgUrl()));
                }
                adapter = new HotelsAdapter();
                adapter.setHotels(hotels);
                hotelRecycler.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
