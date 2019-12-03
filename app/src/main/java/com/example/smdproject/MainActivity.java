package com.example.smdproject;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    Button skip;
    DatabaseReference databaseReference;
    RelativeLayout fbLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databaseReference= FirebaseDatabase.getInstance().getReference();
        setContentView(R.layout.activity_main);
        skip = findViewById(R.id.skip);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Home.class);
                startActivity(intent);
            }
        });
        fbLogin = findViewById(R.id.login_button);
        fbLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

      //  databaseReference = FirebaseDatabase.getInstance().getReference().child("Places");
     //   Review reviews[] = {new Review("Robert", "Beatiful place", "Jan/11/2013"), new Review("John","Must visit once","Dec/01/2015")};
       // Place place = new Place(1,"Paris","https://images.pexels.com/photos/699466/pexels-photo-699466.jpeg?cs=srgb&dl=low-angle-photo-of-eiffel-tower-699466.jpg&fm=jpg",reviews);
     //   databaseReference.child("hgg").setValue(new Review("Robert", "Beatiful place", "Jan/11/2013"));
//        Intent intent = new Intent(this, Map.class);
//        startActivity(intent);

    }
}
