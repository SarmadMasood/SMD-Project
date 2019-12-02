package com.example.smdproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ImageView img1, img2, img3, img4, img5, img6, img7;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        drawerLayout = findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        img1=findViewById(R.id.img1);
        img2=findViewById(R.id.img2);
        img3=findViewById(R.id.img3);
        img4=findViewById(R.id.img4);
        img5=findViewById(R.id.img5);
        img6=findViewById(R.id.img6);
        img7=findViewById(R.id.img7);

        handleImageClick(img1);
        handleImageClick(img2);
        handleImageClick(img3);
        handleImageClick(img4);
        handleImageClick(img5);
        handleImageClick(img6);
        handleImageClick(img7);

    }

    private void handleImageClick(ImageView image){
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Home.this, PlaceDetail.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.translator: {
                Intent intent = new Intent(this,Translator.class);
                startActivity(intent);
                break;
            }
            case R.id.weather: {
                Intent intent = new Intent(this,Weather.class);
                startActivity(intent);
                break;
            }
            case R.id.currency_converter: {
                Intent intent = new Intent(this, CurrencyConverter.class);
                startActivity(intent);
                break;
            }
            case R.id.profile: {
                Intent intent = new Intent(this, Profile.class);
                startActivity(intent);
                break;
            }
            case R.id.world_clock: {
                Intent intent = new Intent(this, WorldClock.class);
                startActivity(intent);
                break;
            }
            case R.id.ride_with_uber: {
                Intent intent = new Intent(this, UberBooking.class);
                startActivity(intent);
                break;
            }
            default: {
                startActivity(new Intent(this, this.getClass()));
                break;
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return toggle.onOptionsItemSelected(item);
    }
}
