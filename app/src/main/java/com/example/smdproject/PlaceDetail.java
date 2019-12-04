package com.example.smdproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class PlaceDetail extends AppCompatActivity {

    DatabaseReference db;
    CoordinatorLayout bg;
    BottomSheetBehavior bottomSheetBehavior;
    ImageView up;
    RecyclerView recyclerView;
    TextView emergency;
   public static ReviewAdapter adapter;
    List<Review> reviews;
    List<Contact> contacts;
    int id;
    List<Place> places;
    PlaceDatabase localDb;
    TextView name;
    Button toFlights;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_detail);
        final Bundle extras = getIntent().getExtras();
        toFlights = findViewById(R.id.toFlights);
        toFlights.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlaceDetail.this,Flights.class);
                startActivity(intent);
            }
        });
        id = Integer.parseInt(extras.getString("id"));

        name = findViewById(R.id.placeName);
        name.setText(extras.getString("name"));

        contacts = new ArrayList<>();
        db = FirebaseDatabase.getInstance().getReference().child("EmergencyContacts");
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot data:dataSnapshot.getChildren()){
                    contacts.add(new Contact(data.getValue(Contact.class).getId(),data.getValue(Contact.class).getName(),data.getValue(Contact.class).getNumber()));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        bg = findViewById(R.id.bg);
        up = findViewById(R.id.up);
        emergency = findViewById(R.id.emergency);
        emergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(PlaceDetail.this);
                dialog.setContentView(R.layout.dialogbox);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.setTitle("Emergency Numbers");

                for(int i=0;i<contacts.size();i++){
                    if(contacts.get(i).getId().equalsIgnoreCase(extras.getString("id")) && contacts.get(i).getName().equalsIgnoreCase("Fire")){
                        TextView firetxt = (TextView) dialog.findViewById(R.id.fire);
                        firetxt.setText(contacts.get(i).getNumber());
                    }
                }

                TextView policetxt = dialog.findViewById(R.id.police);
                for(int i=0;i<contacts.size();i++){
                    if(contacts.get(i).getId().equalsIgnoreCase(extras.getString("id")) && contacts.get(i).getName().equalsIgnoreCase("Police")){
                        policetxt.setText(contacts.get(i).getNumber());
                    }
                }

                TextView medicaltxt = dialog.findViewById(R.id.ambulance);
                for(int i=0;i<contacts.size();i++){
                    if(contacts.get(i).getId().equalsIgnoreCase(extras.getString("id")) && contacts.get(i).getName().equalsIgnoreCase("Ambulance")){
                            medicaltxt.setText(contacts.get(i).getNumber());
                    }
                }


                TextView dialogButton = dialog.findViewById(R.id.dialogButtonOk);
                // if button is clicked, close the custom dialog
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });
       recyclerView = findViewById(R.id.review_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(PlaceDetail.this));
        recyclerView.setHasFixedSize(true);

        localDb = PlaceDatabase.getInstance(this);
        places = localDb.placeDao().getAllPlaces();
       Picasso.get().load(places.get(id-1).getImageUrl()).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                bg.setBackground(new BitmapDrawable(getApplicationContext().getResources(), bitmap));
            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        });
        reviews = new ArrayList<>();
        db = FirebaseDatabase.getInstance().getReference().child("Reviews");
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               for(DataSnapshot data:dataSnapshot.getChildren()){
                   Review r = new Review(data.getValue(Review.class).getPlaceId(),data.getValue(Review.class).getRating(),data.getValue(Review.class).getUser(),data.getValue(Review.class).getReview(),data.getValue(Review.class).getDate());
                   reviews.add(r);
               }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        final View bottomSheet = findViewById(R.id.bottom_sheet);
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View view, int i) {
                if (i==BottomSheetBehavior.STATE_EXPANDED){
                    bottomSheet.setAlpha(1);

                }
                if (i==BottomSheetBehavior.STATE_COLLAPSED){
                    bottomSheet.setAlpha((float) 0.5);
                    up.setImageResource(R.drawable.arrowup);
                }
            }

            @Override
            public void onSlide(@NonNull View view, float v) {
                adapter = new ReviewAdapter();
                adapter.setReviews(reviews);
                recyclerView.setAdapter(adapter);
                up.setImageResource(R.drawable.minus);
            }
        });
    }
}
