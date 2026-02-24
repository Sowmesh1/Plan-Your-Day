package com.example.planyourday.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PlansDao {
     @Query("SELECT * FROM plans ORDER BY id DESC ")
     LiveData<List<Plans>> getAllPlans();

     @Insert
    void insertPlan(Plans plan);

     @Update
    void updatePlan(Plans plan);
}
