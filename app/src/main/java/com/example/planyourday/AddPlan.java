package com.example.planyourday;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.textfield.TextInputEditText;

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



        Button savebutton = view.findViewById(R.id.savebutton);
        savebutton.setOnClickListener(v -> {
            String meridian= timeswitch.isChecked()?"PM":"AM";
            String time = timeInput.getText().toString()+meridian;
            String task = taskDescription.getText().toString();
            Log.d("plan",time + " " +task);
        });
    }

    public void savePlan(){

    }
}