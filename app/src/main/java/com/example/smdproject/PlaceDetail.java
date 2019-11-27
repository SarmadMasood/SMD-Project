package com.example.smdproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.mancj.slideup.SlideUp;

public class PlaceDetail extends AppCompatActivity {

    ConstraintLayout layout;
    SlideUp slideUp;
    View reviewSlide;
    TextView slideText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_detail);
        layout = findViewById(R.id.bg);
        reviewSlide = findViewById(R.id.slideView);
        slideUp = new SlideUp(reviewSlide);
        slideUp.hideImmediately();
        slideText = findViewById(R.id.review_tap);
        slideText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slideUp.animateIn();
            }
        });

        slideUp.setSlideListener(new SlideUp.SlideListener() {
            @Override
            public void onSlideDown(float v) {


            }

            @Override
            public void onVisibilityChanged(int i) {

            }
        });
    }
}
