package com.example.planyourday;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.planyourday.db.AppDatabase;
import com.example.planyourday.db.Plans;
import com.example.planyourday.db.PlansDao;

import java.util.List;


public class PlansRepository {
    public PlansDao plansDao;
    public LiveData<List<Plans>> allPlans;
    public PlansRepository(Application application) {
        AppDatabase db = AppDatabase.getDbInstance(application);
        plansDao =db.plansDao();
        allPlans= plansDao.getAllPlans();
    }

    public LiveData<List<Plans>> getAllPlans(){
        return allPlans;
    }

    public  void insertPlan(Plans plan){
        plansDao.insertPlan(plan);
    }

    public void deletePlan(Plans plan){
        plansDao.deletePlan(plan);
    }
}
