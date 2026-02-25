package com.example.planyourday;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.planyourday.db.AppDatabase;
import com.example.planyourday.db.Plans;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.PlanHolder> {

    public interface OnDeleteClickListener{
        void onDeleteClick(Plans plan);
    }
    private OnDeleteClickListener listener;
    List<Plans> plansList;

    public void setData(List<Plans> newPlans){
        this.plansList=newPlans;
        notifyDataSetChanged();
    }

    public RecyclerAdapter(List<Plans> plansList,OnDeleteClickListener listener) {
        this.plansList = plansList;
        this.listener=listener;
    }

    int count=0;
    @NonNull
    @Override
    public PlanHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =LayoutInflater.from(parent.getContext());
        View view =layoutInflater.inflate(R.layout.row_plans,parent,false);
        PlanHolder planHolder = new PlanHolder(view);
        return planHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PlanHolder holder, int position) {
       Plans currentPlan=plansList.get(position);
       holder.timestamp.setText(currentPlan.getTime());
       holder.description.setText(currentPlan.getDescription());
        holder.checkbox.setOnCheckedChangeListener(null);
        holder.checkbox.setChecked(currentPlan.isCompleted());

        holder.checkbox.setOnCheckedChangeListener((buttonView, isChecked) -> {

            currentPlan.setCompleted(isChecked);

            AppDatabase db = AppDatabase.getDbInstance(buttonView.getContext());
            db.plansDao().updatePlan(currentPlan);
        });
        holder.deleteButton.setOnClickListener(v -> {
            listener.onDeleteClick(plansList.get(position));
        });

    }

    @Override
    public int getItemCount() {
        return plansList.size();
    }

    public static class PlanHolder extends RecyclerView.ViewHolder{
        TextView timestamp,description;
        CheckBox checkbox;
        ImageButton deleteButton;
        public PlanHolder(@NonNull View itemView) {

            super(itemView);
             timestamp =itemView.findViewById(R.id.timestamp);
             description=itemView.findViewById(R.id.description);
             checkbox =itemView.findViewById(R.id.checkbox);
             deleteButton =itemView.findViewById(R.id.deleteButton);
        }
    }
}
