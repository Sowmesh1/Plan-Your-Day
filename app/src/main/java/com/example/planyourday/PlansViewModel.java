package com.example.planyourday;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.planyourday.db.Plans;

import java.util.List;

public class PlansViewModel extends AndroidViewModel {

    public PlansRepository repository;
    public LiveData<List<Plans>> allPlans;
    public PlansViewModel(@NonNull Application application) {
        super(application);
        repository= new PlansRepository(application);
        allPlans=repository.getAllPlans();
    }

    public LiveData<List<Plans>> getAllPlans(){
        return allPlans;
    }
    public void insert(Plans plan){
        repository.insertPlan(plan);
    }

    public void deletePlan(Plans plan){
        repository.deletePlan(plan);
    }
}
