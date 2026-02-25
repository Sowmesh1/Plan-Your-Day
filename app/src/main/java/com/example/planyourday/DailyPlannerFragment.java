package com.example.planyourday;

import android.app.TimePickerDialog;
import android.media.Image;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.planyourday.db.AppDatabase;
import com.example.planyourday.db.Plans;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;



public class DailyPlannerFragment extends Fragment {

    List<Plans> plansList;
    RecyclerAdapter recyclerAdapter;

    public DailyPlannerFragment() {
        // Required empty public constructor
    }

    private String showTodayDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd , yyyy", Locale.getDefault());
        String formattedDate = sdf.format(new Date());
                return formattedDate;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        plansList = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_daily_planner, container, false);

    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        TextView title_day = view.findViewById(R.id.title_day);
        title_day.setText(showTodayDate());
        RecyclerView recyclerView=view.findViewById(R.id.plans);
        PlansViewModel viewModel =
                new ViewModelProvider(this).get(PlansViewModel.class);
         recyclerAdapter=new RecyclerAdapter(plansList,plan -> {
             viewModel.deletePlan(plan);
         });
        recyclerView.setAdapter(recyclerAdapter);


        ImageButton calenderButton=view.findViewById(R.id.calender_icon);
        CalendarView cal = view.findViewById(R.id.calendar);
        CardView cal_Card =view.findViewById(R.id.calCard);
        cal_Card.setVisibility(View.GONE);
        cal.setVisibility(View.GONE);
        calenderButton.setOnClickListener(view1 -> {
            if(cal.getVisibility()==View.VISIBLE){
                cal.setVisibility(View.GONE);
                cal_Card.setVisibility(View.GONE);
            }
            else{
                cal.setVisibility(View.VISIBLE);
                cal_Card.setVisibility(View.VISIBLE);
            }
        });

        cal.setOnDateChangeListener((calendarView, year, month, dayOfMonth) -> {
            Calendar selectedCal = Calendar.getInstance();
            selectedCal.set(year,month,dayOfMonth);
            SimpleDateFormat sdf =
                    new SimpleDateFormat("MMMM dd,yyyy", Locale.getDefault());

            String formattedDate = sdf.format(selectedCal.getTime());

            title_day.setText(formattedDate);
        });

        FloatingActionButton addbtn=view.findViewById(R.id.add_button);
        addbtn.setOnClickListener(v -> {
            AddPlan addPlan=new AddPlan();
            addPlan.show(getParentFragmentManager(),"Add Plan dialog");
        });
            viewModel.getAllPlans().observe(getViewLifecycleOwner(),plans -> {
                recyclerAdapter.setData(plans);
            });


    }
}