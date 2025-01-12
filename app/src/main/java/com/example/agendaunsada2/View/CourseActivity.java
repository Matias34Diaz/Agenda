package com.example.agendaunsada2.View;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agendaunsada2.Adapter.CourseAdapter;
import com.example.agendaunsada2.Model.Course;
import com.example.agendaunsada2.R;
import com.example.agendaunsada2.Viewmodel.CourseViewModel;

import java.util.ArrayList;
import java.util.List;

public class CourseActivity extends AppCompatActivity { // Falta declaración de clase y herencia

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private CourseViewModel courseViewModel;
    private CourseAdapter courseAdapter;
    private List<Course> courseList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        // Inicializar vistas
        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);

        // Configurar RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        courseAdapter = new CourseAdapter(courseList);
        recyclerView.setAdapter(courseAdapter);

        // Inicializar ViewModel
        courseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);

        // Observar datos del ViewModel
        observeViewModel();

        // Cargar los cursos desde el servidor
        courseViewModel.fetchCourses();
    }

    private void observeViewModel() {
        // Observar el estado de carga
        courseViewModel.getIsLoading().observe(this, isLoading -> {
            if (isLoading != null) {
                progressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
            }
        });

        // Observar la lista de cursos
        courseViewModel.getCourses().observe(this, courses -> {
            if (courses != null) {
                courseList.clear();
                courseList.addAll(courses);
                courseAdapter.notifyDataSetChanged();
            }
        });

        // Observar errores
        courseViewModel.getErrorMessage().observe(this, errorMessage -> {
            if (errorMessage != null) {
                Toast.makeText(this, "Error: " + errorMessage, Toast.LENGTH_SHORT).show();
                Log.e("CourseActivity", errorMessage);
            }
        });
    }
}
