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
}
