package com.example.planyourday.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PlansDao {
     @Query("SELECT * FROM plans")
    List<Plans> getAllPlans();

     @Insert
    void insertPlan(Plans plan);
}
