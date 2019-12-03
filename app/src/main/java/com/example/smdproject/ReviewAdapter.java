package com.example.smdproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewHolder> {
    private List<Review> reviews=new ArrayList<>();

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    @NonNull
    @Override
    public ReviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_item,parent,false);
        return new ReviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewHolder holder, int position) {
        Review review = reviews.get(position);
        holder.name.setText(review.getUser());
        holder.rating.setRating(Float.parseFloat(review.getRating()));
        holder.desc.setText(review.getReview());
        holder.date.setText(review.getDate());
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
        notifyDataSetChanged();
    }

    class ReviewHolder extends RecyclerView.ViewHolder{
        TextView name;
        RatingBar rating;
        TextView desc;
        TextView date;
        ReviewHolder(View itemview){
            super(itemview);
            name = itemview.findViewById(R.id.review_name);
            rating = itemview.findViewById(R.id.simpleRatingBar);
            desc = itemview.findViewById(R.id.review_desc);
            date = itemview.findViewById(R.id.review_date);
        }
    }
}
