package com.example.smdproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class HotelsAdapter extends RecyclerView.Adapter<HotelsAdapter.HotelHolder> {
    List<Hotel> hotels = new ArrayList<>();

    @NonNull
    @Override
    public HotelHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hotel_item,parent,false);
        return new HotelsAdapter.HotelHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HotelHolder holder, int position) {
        Hotel hotel = hotels.get(position);
        holder.name.setText(hotel.getName());
        holder.rating.setRating(Float.parseFloat(hotel.getRating()));
        Picasso.get().load(hotel.getImgUrl()).fit().centerCrop().into(holder.img);
    }

    @Override
    public int getItemCount() {
        return hotels.size();
    }

    public void setHotels(List<Hotel> hotels) {
        this.hotels = hotels;
    }

    class HotelHolder extends RecyclerView.ViewHolder{
        TextView name;
        RatingBar rating;
        ImageView img;
        HotelHolder(View itemview){
            super(itemview);
            name = itemview.findViewById(R.id.hotelName);
            rating = itemview.findViewById(R.id.hotelRatingBar);
            img = itemview.findViewById(R.id.hotelImage);
        }
    }
}
