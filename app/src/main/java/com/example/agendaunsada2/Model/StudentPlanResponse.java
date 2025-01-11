package com.example.agendaunsada2.Model;

public class StudentPlanResponse {

        private int id;
        private String name;
        private String surname;
        private String dni;
        private String careerPlan;

        // Constructor
        public StudentPlanResponse(int id, String name, String surname, String dni, String careerPlan, int status) {
            this.id = id;
            this.name = name;
            this.surname = surname;
            this.dni = dni;
            this.careerPlan = careerPlan;

        }

        // Getters y Setters (puedes generarlos automáticamente en tu IDE)
    }


