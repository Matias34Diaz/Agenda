package com.example.agendaunsada2.View;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.agendaunsada2.R;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        // Mostrar mensaje en logs para confirmar carga de la actividad
        System.out.println("MainMenuActivity: Hola Mundo");
    }
}

