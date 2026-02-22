package com.example.planyourday.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.planyourday.RecyclerAdapter;

@Database(entities = {Plans.class},version = 2)
public abstract class AppDatabase extends RoomDatabase {

    public abstract PlansDao plansDao();

    private static AppDatabase INSTANCE;

    public static AppDatabase getDbInstance(Context context){
        if(INSTANCE==null){
               INSTANCE=Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class,"Plans Table")
                       .fallbackToDestructiveMigration()
                       .allowMainThreadQueries()
                       .build();
        }
        return  INSTANCE;
    }

}
