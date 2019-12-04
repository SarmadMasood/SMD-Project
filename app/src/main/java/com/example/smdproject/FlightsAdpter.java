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

public class FlightsAdpter extends RecyclerView.Adapter<FlightsAdpter.FlightHolder> {
   List<Flight> flights = new ArrayList<>();

    @NonNull
    @Override
    public FlightHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.flight_item,parent,false);
        return new FlightHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FlightHolder holder, int position) {
        Flight flight = flights.get(position);
        holder.carrier.setText(flight.getCarrier());
        holder.price.setText(flight.getPrice()+" USD");
        holder.date.setText(flight.getDate());
    }

    @Override
    public int getItemCount() {
        return flights.size();
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }

    class FlightHolder extends RecyclerView.ViewHolder{
        TextView carrier;
        TextView price;
        TextView date;
        FlightHolder(View itemview){
            super(itemview);
            carrier = itemview.findViewById(R.id.carrier);
            price = itemview.findViewById(R.id.price);
            date = itemview.findViewById(R.id.date);
        }
    }
}
