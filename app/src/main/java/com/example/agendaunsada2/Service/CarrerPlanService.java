package com.example.agendaunsada2.Service;

import com.example.agendaunsada2.Interface.CarrerPlanApi;
import com.example.agendaunsada2.Model.CarrerPlan;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CarrerPlanService {

        private static final String BASE_URL = "https://5446-179-42-181-209.ngrok-free.app/api/"; // Cambia esta URL si es necesario
        private final CarrerPlanApi carrerPlanApi;

        public CarrerPlanService() {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            carrerPlanApi = retrofit.create(CarrerPlanApi.class);
        }

        // Obtener todos los planes de carrera
        public Call<List<CarrerPlan>> getAllCarrerPlans() {
            return carrerPlanApi.getAllCarrerPlans();
        }

        // Obtener un plan de carrera por ID
        public Call<CarrerPlan> getCarrerPlanById(int id) {
            return carrerPlanApi.getCarrerPlanById(id);
        }

        // Crear un nuevo plan de carrera
        public Call<CarrerPlan> createCarrerPlan(CarrerPlan carrerPlan) {
            return carrerPlanApi.createCarrerPlan(carrerPlan);
        }

        // Actualizar un plan de carrera por ID (completo)
        public Call<CarrerPlan> updateCarrerPlan(int id, CarrerPlan carrerPlan) {
            return carrerPlanApi.updateCarrerPlan(id, carrerPlan);
        }

        // Actualizar parcialmente un plan de carrera por ID
        public Call<CarrerPlan> updatePartialCarrerPlan(int id, CarrerPlan carrerPlan) {
            return carrerPlanApi.updatePartialCarrerPlan(id, carrerPlan);
        }

        // Eliminar un plan de carrera por ID
        public Call<Void> deleteCarrerPlan(int id) {
            return carrerPlanApi.deleteCarrerPlan(id);
        }
    }

