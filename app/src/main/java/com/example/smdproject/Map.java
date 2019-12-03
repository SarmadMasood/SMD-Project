package com.example.smdproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.uber.sdk.android.rides.RideRequestButton;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Map extends AppCompatActivity implements OnMapReadyCallback{

    private static final int PERMISSIONS_REQUEST_ACCESS_LOCATION = 100;
    private static final float DEFAULT_ZOOM = 15f;
    String permissions[] = {Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION};
    Boolean locationPermissionGranted;
    private GoogleMap map;
    private FusedLocationProviderClient client;

    private RideRequestButton button;
    private EditText searchMap;
    private ImageView gps;
    private BottomSheetBehavior bottomSheetBehavior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        searchMap = findViewById(R.id.search_map);
        gps = findViewById(R.id.gps);

        button = findViewById(R.id.uber);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//
                final String appPackageName = "com.ubercab";

                    Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.ubercab");
                if (isAppInstalled("com.ubercab")) {
                    startActivity(launchIntent);//null pointer check in case package name was not found
                }
                else{
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("market://details?id=" + appPackageName)));
                }
            }
        });

        final View bottomSheet = findViewById(R.id.bottom_sheet_map);
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {

            locationPermissionGranted = true;
            initMap();
        }
        else {
                ActivityCompat.requestPermissions(this,
                        permissions,
                        PERMISSIONS_REQUEST_ACCESS_LOCATION);
            initMap();
        }

        init();

        gps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCurrentLocation();
            }
        });
    }

    private boolean isAppInstalled(String uri) {
        PackageManager pm = getPackageManager();
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
        }

        return false;
    }


    private void init(){
        searchMap.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_SEARCH
                || actionId == EditorInfo.IME_ACTION_DONE
                || event.getAction() == KeyEvent.ACTION_DOWN
                || event.getAction() == KeyEvent.KEYCODE_ENTER){
                    geoLocate();
                }
                return false;
            }
        });
    }

    private void geoLocate() {
        String searchString = searchMap.getText().toString();

        Geocoder geocoder = new Geocoder(Map.this);
        List<Address> list = new ArrayList<>();
        try{
            list = geocoder.getFromLocationName(searchString,1);
        }catch(IOException e){
            Log.e("Map","geoLocate : IOException: " + e.getMessage());
        }

        if(list.size()>0){
            Address address = list.get(0);
            moveCamera(new LatLng(address.getLatitude(),address.getLongitude()), DEFAULT_ZOOM ,address.getAddressLine(0));

        }
    }

    private void getCurrentLocation(){
        client = LocationServices.getFusedLocationProviderClient(this);
        try {
            if(locationPermissionGranted){
                Task location = client.getLastLocation();
                location.addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if(task.isSuccessful()){
                            Location currentLocarion = (Location) task.getResult();
                            moveCamera(new LatLng(currentLocarion.getLatitude(),currentLocarion.getLongitude()), DEFAULT_ZOOM,"My Location");
                        }
                        else{
                            Log.d("Map","Current location is null.");
                        }
                    }
                });
            }
            else {
                Log.d("Map","Location permission is not granted.");
            }
        } catch (SecurityException e){
            Log.e("Map","Security Exception on current location " + e.getMessage());
        }
    }

    private void moveCamera(LatLng location, float zoom, String title){
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(location,zoom));
       if(title!= "My Location"){
           MarkerOptions options = new MarkerOptions()
                   .position(location)
                   .title(title);
           map.addMarker(options);
           map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
               @Override
               public boolean onMarkerClick(Marker marker) {
                   bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                   return false;
               }
           });
       }
       hideSoftKeyboard();
    }

    private void initMap(){
        final SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(Map.this);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        locationPermissionGranted = false;

        switch (requestCode){
            case PERMISSIONS_REQUEST_ACCESS_LOCATION:
                if(grantResults.length>0){
                    for(int i=0;i<grantResults.length;i++){
                        if(grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                            locationPermissionGranted = false;
                            break;
                        }
                    }
                    locationPermissionGranted = true;

                }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        if(locationPermissionGranted){
            getCurrentLocation();
            if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED){
                return;
            }
            map.setMyLocationEnabled(true);
            map.getUiSettings().setZoomControlsEnabled(true);
            map.getUiSettings().setZoomGesturesEnabled(true);
            map.getUiSettings().setMyLocationButtonEnabled(false);
            init();
        }
        else {
            Log.d("Map","Location permission is not granted.");
        }
    }

    private void hideSoftKeyboard(){
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }
}
