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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ImageView img1, img2, img3, img4, img5, img6, img7,img8, img9, img10, img11, img12, img13, img14,img15, img16, img18, img19, img20, img17, img21,img22;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    PlaceDatabase db;
    List<Place> places;

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

        img8=findViewById(R.id.img8);
        img9=findViewById(R.id.img9);
        img10=findViewById(R.id.img10);
        img11=findViewById(R.id.img11);
        img12=findViewById(R.id.img12);
        img13=findViewById(R.id.img13);
        img14=findViewById(R.id.img14);

        img15=findViewById(R.id.img15);
        img16=findViewById(R.id.img16);
        img17=findViewById(R.id.img17);
        img18=findViewById(R.id.img18);
        img19=findViewById(R.id.img19);
        img20=findViewById(R.id.img20);
        img21=findViewById(R.id.img21);

        db = PlaceDatabase.getInstance(this);
        places = new ArrayList<>();
        places = db.placeDao().getAllPlaces();
        if(places.size()==0){
            Place place = new Place(1,"Paris","https://images.pexels.com/photos/699466/pexels-photo-699466.jpeg?cs=srgb&dl=low-angle-photo-of-eiffel-tower-699466.jpg&fm=jpg");
            Place place2 = new Place(2,"London","https://wallpaperaccess.com/full/732321.jpg");
            places.add(place);
            places.add(place2);
            places.add(new Place(3,"Singapore","https://fsa.zobj.net/crop.php?r=QyXz73Px9D13tzBmsY5BQK_kGxWxtTAj0-wnPqHohe1NmZrpgaqfGaIBiEAP23iHGfVjYTpiU_8JpE7_OxZPLQ0PICIPqQoE2l31hx0ukfriIsskg-pwG6yDJluqrFgS1XEdPjGZ39SEoIt4"));
            places.add(new Place(4,"Macau","https://i.pinimg.com/originals/69/ea/47/69ea472c381a44560f3185c825fbcd3a.jpg"));
            places.add(new Place(5,"Dubai","https://image.winudf.com/v2/image/Y29tLm1vYmlsZWxpdmUuZHViYWloZHdhbGxwYXBlcnNfc2NyZWVuXzBfMTUxNzk5NzkxNF8wNzk/screen-0.jpg?fakeurl=1&type=.jpg"));
            places.add(new Place(6,"New York","http://getwallpapers.com/wallpaper/full/d/3/6/539706.jpg"));
            places.add(new Place(7,"Kuala Lumpur","https://i.pinimg.com/originals/7d/45/05/7d4505e39651f97c652936eb131a102c.jpg"));
            places.add(new Place(8,"Istanbul","https://cdn.hipwallpaper.com/i/13/32/JvKj7C.jpg"));
            places.add(new Place(9,"Tokyo","https://i.pinimg.com/originals/2e/80/4a/2e804af2fe69257bc01a54ba74d88848.jpg"));
            places.add(new Place(10,"Prague","https://mfiles.alphacoders.com/713/713816.jpg"));
            places.add(new Place(11,"Miami","https://i.pinimg.com/originals/d6/0d/94/d60d94f59443581dce75b337c23d30fd.jpg"));
            places.add(new Place(12,"Amsterdam","https://s2.best-wallpaper.net/wallpaper/iphone/1411/Amsterdam-Holland-houses-boat-river-night_iphone_320x480.jpg"));
            places.add(new Place(13,"Shanghai","https://mfiles.alphacoders.com/634/634031.jpg"));
            places.add(new Place(14,"Los Angeles","https://fsa.zobj.net/crop.php?r=TjfBhWOEPDeMY5l7KQr-TMl-zBbKfdH4CSFpn0DLu-xW8cgjC3sgUBF332m1400eWDRLqWKcGXro4KUalDyIg6fvNRUdXbZdo1bGdvtzHpAn8jh8F59Jvju1IJY2tYFmj10oaUyxPrOBqVCaDwHWb6bJ94bOh4_Lq0KRWxUawwlQCbq54RBHAXFL5neYGs8IDcSTGjODANm6oHyn"));
            places.add(new Place(15,"Las Vegas","https://i.pinimg.com/originals/b4/9e/60/b49e606dececa4dd326c5a5dc9bfff80.jpg"));
            places.add(new Place(16,"Agra","https://images.wallpaperscraft.com/image/taj_mahal_agra_india_mausoleum_mosque_10904_800x1200.jpg"));
            places.add(new Place(17,"Barcelona","https://wallpaperbro.com/img/19392.jpg"));
            places.add(new Place(18,"Berlin","https://wallpaperaccess.com/full/755174.jpg"));
            places.add(new Place(19,"Venice","https://r1.ilikewallpaper.net/iphone-4s-wallpapers/download/4710/Venice-iphone-4s-wallpaper-ilikewallpaper_com.jpg"));
            places.add(new Place(20,"Moscow","https://images.unsplash.com/photo-1520106212299-d99c443e4568?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&w=1000&q=80"));
            places.add(new Place(21,"Cairo","https://i.pinimg.com/originals/24/4d/09/244d0991b91d454593c0f56dc43f6d65.jpg"));
            places.add(new Place(22,"Lisbon","https://wallpaperaccess.com/full/1423575.jpg"));

            for(Place p: places){
                db.placeDao().insert(p);
            }
        }

        places = db.placeDao().getAllPlaces();

        setUpImage(img1,1);
        setUpImage(img2,2);
        setUpImage(img3,3);
        setUpImage(img4,4);
        setUpImage(img5,5);
        setUpImage(img6,6);
        setUpImage(img7,7);

        setUpImage(img8,8);
        setUpImage(img9,9);
        setUpImage(img10,10);
        setUpImage(img11,11);
        setUpImage(img12,12);
        setUpImage(img13, 12);
        setUpImage(img14,14);

        setUpImage(img15, 15);
        setUpImage(img16, 16);
        setUpImage(img17, 17);
        setUpImage(img18, 18);
        setUpImage(img19, 19);
        setUpImage(img20,20);
        setUpImage(img21, 21);

        img22 = findViewById(R.id.img22);
        setUpImage(img22, 22);


    }

    private void setUpImage(ImageView image,final int id){
        final Place p = places.get(id-1);
        Picasso.get().load(p.getImageUrl()).fit().centerCrop().into(image);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Home.this, PlaceDetail.class);
                intent.putExtra("id",String.valueOf(id));
                intent.putExtra("name",p.getName());
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
            case R.id.world_clock: {
                Intent intent = new Intent(this, WorldClock.class);
                startActivity(intent);
                break;
            }
            case R.id.menuMap: {
                Intent intent = new Intent(this, Map.class);
                startActivity(intent);
                break;
            }
            case R.id.menuProfile: {
                Intent intent = new Intent(this, Profile.class);
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
