package com.example.planyourday;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;


public class DailyPlannerFragment extends Fragment {

    public DailyPlannerFragment() {
        // Required empty public constructor
    }


    private String showTodayDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd , yyyy", Locale.getDefault());
        String formattedDate = sdf.format(new Date());
                return formattedDate;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_daily_planner, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        TextView title_day = view.findViewById(R.id.title_day);
        title_day.setText(showTodayDate());
    }

}