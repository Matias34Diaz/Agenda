package com.example.agendaunsada2.View;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;
import androidx.appcompat.app.AppCompatActivity;

import com.example.agendaunsada2.R;
import com.example.agendaunsada2.Viewmodel.UserViewModel;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;

public class MainActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 100;

    private GoogleSignInClient googleSignInClient;
    private UserViewModel userViewModel;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializa el ViewModel
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        // Configura el ProgressBar
        progressBar = findViewById(R.id.progress_bar);

        // Configura Google Sign-In
        configureGoogleSignIn();

        // Observa los estados del ViewModel
        observeViewModel();

        // Configura el botón de inicio de sesión con Google
        findViewById(R.id.login_button).setOnClickListener(v -> signInWithGoogle());
    }

    private void configureGoogleSignIn() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id)) // Verifica que este ID coincide con google-services.json
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    private void observeViewModel() {
        // Observa el estado de carga
        userViewModel.getIsLoading().observe(this, isLoading -> {
            if (isLoading != null) {
                progressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
            }
        });

        // Observa la respuesta del inicio de sesión
        userViewModel.getLoginResponse().observe(this, loginResponse -> {
            if (loginResponse != null) {
                Log.d("Login", "Inicio de sesión exitoso: " + loginResponse.getToken());
                navigateToCarrerPlanActivity();
            }
        });

        // Observa los errores
        userViewModel.getErrorMessage().observe(this, errorMessage -> {
            if (errorMessage != null) {
                Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void signInWithGoogle() {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            try {
                GoogleSignInAccount account = GoogleSignIn.getSignedInAccountFromIntent(data).getResult(ApiException.class);

                if (account != null) {
                    // Obtén el token de usuario
                    String idToken = account.getIdToken();
                    Log.d("GoogleSignIn", "Token ID: " + idToken);

                    // Envía el token al ViewModel para iniciar sesión
                    userViewModel.loginUser(idToken);
                } else {
                    Log.e("GoogleSignIn", "GoogleSignInAccount es nulo.");
                }

            } catch (ApiException e) {
                Log.e("GoogleSignIn", "Error al iniciar sesión: " + e.getMessage());
                Toast.makeText(this, "Error al iniciar sesión con Google", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void navigateToCarrerPlanActivity() {
        Intent intent = new Intent(MainActivity.this, CarrerPlanActivity.class);
        startActivity(intent);
        finish(); // Finaliza la actividad actual
    }
}
