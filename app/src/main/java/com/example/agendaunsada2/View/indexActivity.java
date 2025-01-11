package com.example.agendaunsada2.View;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agendaunsada2.Adapter.CourseAdapter;
import com.example.agendaunsada2.Model.Course;
import com.example.agendaunsada2.Model.CarrerPlanWithCourses;
import com.example.agendaunsada2.R;
import com.example.agendaunsada2.Viewmodel.CarrerPlanViewModel;

import java.util.List;

public class indexActivity extends AppCompatActivity {
    private CourseAdapter courseAdapter;
    private CarrerPlanViewModel carrerPlanViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewCareerPlans);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        courseAdapter = new CourseAdapter();
        recyclerView.setAdapter(courseAdapter);

        carrerPlanViewModel = new ViewModelProvider(this).get(CarrerPlanViewModel.class);
        observeViewModel();
        fetchCourses(); // Llama al método para obtener los cursos con estados
    }

    private void observeViewModel() {
        carrerPlanViewModel.getCourses().observe(this, courses -> {
            if (courses != null) {
                courseAdapter.setCourses(courses);
            }
        });

        carrerPlanViewModel.getErrorMessage().observe(this, errorMessage -> {
            if (errorMessage != null) {
                Toast.makeText(indexActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fetchCourses() {
        int studentId = 1; // Id del estudiante
        carrerPlanViewModel.fetchCourseStatus(studentId);
    }
}
