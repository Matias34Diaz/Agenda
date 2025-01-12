package com.example.agendaunsada2.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.agendaunsada2.Model.CarrerPlan;
import com.example.agendaunsada2.R;
import com.example.agendaunsada2.Viewmodel.CarrerPlanViewModel;

import java.util.ArrayList;
import java.util.List;

public class CarrerPlanActivity extends AppCompatActivity {

    private Spinner carrerPlanSpinner;
    private Button confirmButton;
    private ProgressBar progressBar;
    private CarrerPlanViewModel carrerPlanViewModel;
    private ArrayAdapter<String> adapter;
    private List<String> carrerPlanList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrer_plan);

        carrerPlanSpinner = findViewById(R.id.carrerPlanSpinner);
        confirmButton = findViewById(R.id.confirmButton);
        progressBar = findViewById(R.id.progressBar);

        // Inicializar ViewModel
        carrerPlanViewModel = new ViewModelProvider(this).get(CarrerPlanViewModel.class);

        // Configurar Spinner con datos iniciales
        carrerPlanList.add("Seleccionar");
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, carrerPlanList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        carrerPlanSpinner.setAdapter(adapter);

        // Observar cambios en los datos del ViewModel
        observeViewModel();

        // Cargar planes de carrera desde la base de datos
        carrerPlanViewModel.fetchCarrerPlans();

        // Manejar selección del Spinner
        carrerPlanSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) { // Ignorar la opción "Seleccionar"
                    Toast.makeText(CarrerPlanActivity.this, "Plan seleccionado: " + parent.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // No hacer nada si no se selecciona nada
            }
        });

        // Configurar botón Confirmar
        confirmButton.setOnClickListener(v -> {
            String selectedPlan = carrerPlanSpinner.getSelectedItem().toString();
            if (!selectedPlan.equals("Seleccionar")) {
                // Redirigir a la actividad de cursos
                Intent intent = new Intent(CarrerPlanActivity.this, MainMenuActivity.class);
                intent.putExtra("selectedPlan", selectedPlan); // Pasar el plan seleccionado
                startActivity(intent);
            } else {
                Toast.makeText(CarrerPlanActivity.this, "Por favor, seleccione un plan", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void observeViewModel() {
        // Observar el estado de carga
        carrerPlanViewModel.getIsLoading().observe(this, isLoading -> {
            if (isLoading != null) {
                progressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
            }
        });

        // Observar la lista de planes de carrera
        carrerPlanViewModel.getCarrerPlans().observe(this, plans -> {
            if (plans != null) {
                carrerPlanList.clear();
                carrerPlanList.add("Seleccionar"); // Opción predeterminada

                // Extraer los nombres de los planes y agregarlos a la lista
                for (CarrerPlan plan : plans) {
                    carrerPlanList.add(plan.getName()); // Asegúrate de tener un método `getName()` en CarrerPlan
                }

                adapter.notifyDataSetChanged();
            }
        });

        // Observar errores
        carrerPlanViewModel.getErrorMessage().observe(this, errorMessage -> {
            if (errorMessage != null) {
                Toast.makeText(this, "Error: " + errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }
}

