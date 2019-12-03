package com.example.smdproject;

import android.app.Activity;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities ={Place.class} ,version = 1)
public abstract class PlaceDatabase extends RoomDatabase {
    private static PlaceDatabase instance;
    public static  PlaceDatabase getInstance(Context context){
        if(instance==null){
            instance = Room.databaseBuilder(context,PlaceDatabase.class,"placedb").allowMainThreadQueries().build();
        }
        return instance;
    }
    public abstract PlaceDao placeDao();
}
