package com.example.agendaunsada2.View;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.agendaunsada2.R;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        Button btnViewCourses = findViewById(R.id.btn_view_courses);
        Button btnViewStudents = findViewById(R.id.btn_view_students);
        Button btnViewProfile = findViewById(R.id.btn_view_profile);


    }
}
