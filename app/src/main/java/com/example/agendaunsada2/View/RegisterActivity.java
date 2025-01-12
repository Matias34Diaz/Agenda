package com.example.agendaunsada2.View;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.agendaunsada2.Model.User;
import com.example.agendaunsada2.R;
import com.example.agendaunsada2.Viewmodel.UserViewModel;

public class RegisterActivity extends AppCompatActivity {

    private UserViewModel userViewModel;
    private EditText nameEditText, emailEditText, passwordEditText;
    private Button registerButton;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Inicializar ViewModel
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        // Inicializar vistas
        nameEditText = findViewById(R.id.nameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        registerButton = findViewById(R.id.registerButton);
        progressBar = findViewById(R.id.progressBar);

        // Configurar observadores
        observeViewModel();

        // Configurar botón de registro
        registerButton.setOnClickListener(v -> {
            String name = nameEditText.getText().toString().trim();
            String email = emailEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();

            if (validateInputs(name, email, password)) {
                User user = new User(name, email, password); // Ajustar constructor según modelo
                userViewModel.registerUser(user);
            }
        });
    }

    private void observeViewModel() {
        // Observa el estado de carga
        userViewModel.getIsLoading().observe(this, isLoading -> {
            if (isLoading != null) {
                progressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
            }
        });

        // Observa la respuesta de registro
        userViewModel.getRegisterResponse().observe(this, registerResponse -> {
            if (registerResponse != null && registerResponse.getStatus() == 201) {
                Log.d("Register", "Registro exitoso: " + registerResponse.getMessage());
                Toast.makeText(this, registerResponse.getMessage(), Toast.LENGTH_SHORT).show();
                navigateToHome(); // Finaliza esta actividad y regresa al login
            } else {
                Log.e("Register", "Registro fallido: " + (registerResponse != null ? registerResponse.getMessage() : "Sin mensaje"));
                Toast.makeText(this, "Registro fallido. Intenta de nuevo.", Toast.LENGTH_SHORT).show();
            }
        });

        // Observa los errores
        userViewModel.getErrorMessage().observe(this, errorMessage -> {
            if (errorMessage != null) {
                Log.e("RegisterError", errorMessage);
                Toast.makeText(this, "Error: " + errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void navigateToHome() {
        Intent intent = new Intent(RegisterActivity.this, CarrerPlanActivity.class);
        startActivity(intent);
        finish(); // Finaliza la actividad actual
    }



    private boolean validateInputs(String name, String email, String password) {
        if (name.isEmpty()) {
            Toast.makeText(this, "El nombre no puede estar vacío.", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (email.isEmpty()) {
            Toast.makeText(this, "El email no puede estar vacío.", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (password.isEmpty()) {
            Toast.makeText(this, "La contraseña no puede estar vacía.", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}


