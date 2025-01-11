package com.example.agendaunsada2.View;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.agendaunsada2.Model.CarrerPlan;
import com.example.agendaunsada2.Model.Student;
import com.example.agendaunsada2.R;
import com.example.agendaunsada2.Viewmodel.CarrerPlanViewModel;

import java.util.ArrayList;
import java.util.List;

public class CarrerPlanActivity extends AppCompatActivity {

    // ViewModel para manejar la lógica de los planes de carrera y estudiante
    private CarrerPlanViewModel carrerPlanViewModel;
    private Spinner spinnerCarrerPlans;
    private Button buttonSaveAndContinue;
    private EditText editTextStudentName;
    private EditText editTextStudentLastName;
    private EditText editTextStudentDNI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrer_plan);

        // // Inicializa el CarrerPlanViewModel
        carrerPlanViewModel = new ViewModelProvider(this).get(CarrerPlanViewModel.class);

        //  Inicializa las vistas del layout
        spinnerCarrerPlans = findViewById(R.id.spinnerCarrerPlans);
        buttonSaveAndContinue = findViewById(R.id.buttonSaveAndContinue);
        editTextStudentName = findViewById(R.id.editTextStudentName);
        editTextStudentLastName = findViewById(R.id.editTextStudentLastName);
        editTextStudentDNI = findViewById(R.id.editTextStudentDNI);
        TextView textViewTitle = findViewById(R.id.textViewCarrerPlanTitle);


        textViewTitle.setText(R.string.choose_career_plan);

        // Observa los cambios en los planes de carrera
        carrerPlanViewModel.getCarrerPlans().observe(this, carrerPlans -> {
            if (carrerPlans != null && !carrerPlans.isEmpty()) {
                // Crea una lista de nombres de planes de carrera
                List<String> carrerPlanNames = new ArrayList<>();
                for (CarrerPlan plan : carrerPlans) {
                    carrerPlanNames.add(plan.getName());
                }
                // Configura el adapter para el spinner
                ArrayAdapter<String> adapter = new ArrayAdapter<>(CarrerPlanActivity.this, android.R.layout.simple_spinner_item, carrerPlanNames);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerCarrerPlans.setAdapter(adapter);

                // Configura el listener para el spinner
                spinnerCarrerPlans.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        textViewTitle.setText(carrerPlanNames.get(position));
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        textViewTitle.setText(R.string.choose_career_plan);
                    }
                });
            } else {
                // Muestra un mensaje si no hay planes de carrera disponibles
                Toast.makeText(CarrerPlanActivity.this, "No career plans available", Toast.LENGTH_SHORT).show();
            }
        });

        // Observa los mensajes de error
        carrerPlanViewModel.getErrorMessage().observe(this, errorMessage -> {
            if (errorMessage != null) {
                Toast.makeText(CarrerPlanActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
            }
        });

        // Obtiene todos los planes de carrera al iniciar la actividad
        carrerPlanViewModel.fetchAllCarrerPlans();

        // Configura el listener para el botón de guardar y continuar
        buttonSaveAndContinue.setOnClickListener(v -> {
            if (validateForm()) {
                int selectedPosition = spinnerCarrerPlans.getSelectedItemPosition();
                if (selectedPosition != AdapterView.INVALID_POSITION) {
                    CarrerPlan selectedPlan = carrerPlanViewModel.getCarrerPlans().getValue().get(selectedPosition);
                    String studentName = editTextStudentName.getText().toString().trim();
                    String studentLastName = editTextStudentLastName.getText().toString().trim();
                    String studentDNI = editTextStudentDNI.getText().toString().trim();
                    Student student = new Student();
                    student.setName(studentName);
                    student.setSurname(studentLastName);
                    student.setDni(studentDNI);
                    student.setId_plan_carrera(selectedPlan.getId());

                    carrerPlanViewModel.createStudentWithPlan(student).observe(this, studentId -> {
                        if (studentId != null) {
                            Toast.makeText(this, "Estudiante creado y plan de carrera asignado con éxito", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(CarrerPlanActivity.this, indexActivity.class);
                            intent.putExtra("carrerPlanId", selectedPlan.getId());
                            intent.putExtra("studentId", studentId);
                            startActivity(intent);
                        } else {
                            Toast.makeText(this, "Error al crear estudiante y asignar plan de carrera", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Toast.makeText(CarrerPlanActivity.this, "Please select a career plan", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean validateForm() {
        boolean isValid = true;

        // Valida que el nombre del estudiante no esté vacío
        String studentName = editTextStudentName.getText().toString().trim();
        if (TextUtils.isEmpty(studentName)) {
            editTextStudentName.setError("Please enter student name");
            isValid = false;
        }

        String studentLastName = editTextStudentLastName.getText().toString().trim();
        if (TextUtils.isEmpty(studentLastName)) {
            editTextStudentLastName.setError("Please enter student last name");
            isValid = false;
        }

        String studentDNI = editTextStudentDNI.getText().toString().trim();
        if (TextUtils.isEmpty(studentDNI)) {
            editTextStudentDNI.setError("Please enter student DNI");
            isValid = false;
        }

        return isValid;
    }
}
