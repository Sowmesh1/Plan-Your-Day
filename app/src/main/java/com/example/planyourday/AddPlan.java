package com.example.planyourday;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.planyourday.db.AppDatabase;
import com.example.planyourday.db.Plans;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddPlan extends DialogFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.add_plan, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        TextInputEditText timeInput =view.findViewById(R.id.timeInput);
        TextInputEditText taskDescription =view.findViewById(R.id.taskDescription);
        SwitchCompat timeswitch = view.findViewById(R.id.timeswitch);

        PlansViewModel viewModel = new ViewModelProvider(requireActivity()).get(PlansViewModel.class);

        Button savebutton = view.findViewById(R.id.savebutton);
        savebutton.setOnClickListener(v -> {
            String meridian= timeswitch.isChecked()?"PM":"AM";
            String time = timeInput.getText().toString()+meridian;
            String description = taskDescription.getText().toString();
            String date = showTodayDate();
            Log.d("plan",date+" "+time + " " +description);
            Plans plan = new Plans();
            plan.date=date;
            plan.time=time;
            plan.description= description;
            viewModel.insert(plan);

            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                dismiss();
            }, 500);

        });
    }
    private String showTodayDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd , yyyy", Locale.getDefault());
        String formattedDate = sdf.format(new Date());
        return formattedDate;
    }
}