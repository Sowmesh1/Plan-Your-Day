package com.example.planyourday.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "plans")
public class Plans {
    @PrimaryKey(autoGenerate = true)
    public int id;
     public String date;
     public String time;
     public String description;
     public boolean completed;

     public String getTime(){
         return time;
     }

    public String getDescription(){
        return description;
    }

    public boolean isCompleted() {
        return completed;
    }
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

}
