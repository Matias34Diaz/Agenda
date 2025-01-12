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

public class MainActivity extends AppCompatActivity {

    private UserViewModel userViewModel;
    private EditText emailEditText, passwordEditText;
    private Button loginButton, registerButton;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar ViewModel
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        // Inicializar vistas
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        registerButton = findViewById(R.id.registerButton);
        progressBar = findViewById(R.id.progressBar);

        // Configurar observadores
        observeViewModel();

        // Configurar botón de inicio de sesión
        loginButton.setOnClickListener(v -> {
            String email = emailEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();

            if (!email.isEmpty() && !password.isEmpty()) {
                User user = new User(null, null, email, null, null, null);
                user.setPassword(password);
                userViewModel.loginUser(user);
            } else {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
            }
        });

        // Configurar botón de registro
        registerButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
    }

    private void observeViewModel() {
        // Observa el estado de carga
        userViewModel.getIsLoading().observe(this, isLoading -> {
            if (isLoading != null) {
                progressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
            }
        });

        // Observa la respuesta de inicio de sesión
        userViewModel.getLoginResponse().observe(this, loginResponse -> {
            if (loginResponse != null && "success".equals(loginResponse.getStatus())) {
                Log.d("Login", "Inicio de sesión exitoso para el usuario: " + loginResponse.getUser().getName());
                Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
                navigateToHome();
            } else {
                Log.e("Login", "Error: Estado no exitoso");
                Toast.makeText(this, "Error al iniciar sesión. Verifica tus credenciales.", Toast.LENGTH_SHORT).show();
            }
        });

        // Observa errores
        userViewModel.getErrorMessage().observe(this, errorMessage -> {
            if (errorMessage != null) {
                Log.e("LoginError", errorMessage);
                Toast.makeText(this, "Error: " + errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void navigateToHome() {
        Intent intent = new Intent(MainActivity.this, MainMenuActivity.class);
        startActivity(intent);
        finish(); // Finaliza la actividad actual
    }
}
