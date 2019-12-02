package com.example.smdproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.mancj.slideup.SlideUp;

public class PlaceDetail extends AppCompatActivity {

    ConstraintLayout layout;
    BottomSheetBehavior bottomSheetBehavior;
    ImageView up;
    TextView review;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_detail);
        up = findViewById(R.id.up);
        review = findViewById(R.id.review);
       // layout = findViewById(R.id.bg);

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
                bottomSheet.setAlpha((float) (0.5-v/100));
                up.setImageResource(R.drawable.minus);
            }
        });
    }
}
