package com.example.planyourday;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        if(savedInstanceState==null){
            DailyPlannerFragment dailyPlannerFragment = new DailyPlannerFragment();

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,dailyPlannerFragment).commit();
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



    }
    public void TodosClick(View view){
          TodosFragment todosFragment =new TodosFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,todosFragment).addToBackStack(null).commit();
    }

    public void NotesClick(View view){
        NotesFragment notesFragment =new NotesFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,notesFragment).addToBackStack(null).commit();
    }
    public void PlansClick(View view){
        DailyPlannerFragment dailyPlannerFragment = new DailyPlannerFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,dailyPlannerFragment).commit();
    }
}