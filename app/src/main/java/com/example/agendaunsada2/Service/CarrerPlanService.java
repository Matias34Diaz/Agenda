package com.example.agendaunsada2.Service;

import com.example.agendaunsada2.Interface.CarrerPlanApi;
import com.example.agendaunsada2.Model.CarrerPlan;
import com.example.agendaunsada2.Model.CarrerPlanWithCourses;
import com.example.agendaunsada2.Model.Student;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CarrerPlanService {
    private static final String BASE_URL = "https://flamingo-crack-implicitly.ngrok-free.app/api/";

    private final CarrerPlanApi carrerPlanApi;

    public CarrerPlanService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        carrerPlanApi = retrofit.create(CarrerPlanApi.class);
    }

    public Call<List<CarrerPlan>> getAllCarrerPlans() {
        return carrerPlanApi.getAllCarrerPlans();
    }

    public Call<CarrerPlan> getCarrerPlansById(int id) {
        return carrerPlanApi.getCarrerPlansById(id);
    }

    public Call<CarrerPlan> createCarrerPlans(CarrerPlan carrerPlan) {
        return carrerPlanApi.createCarrerPlan(carrerPlan);
    }

    public Call<CarrerPlan> updateCarrerPlan(int id, CarrerPlan carrerPlan){
        return carrerPlanApi.updateCarrerPlan(id, carrerPlan);
    }

    public Call<CarrerPlan> updatePartialCarrerPlan(int id, CarrerPlan carrerPlan){
        return carrerPlanApi.updatePartialCarrerPlan(id, carrerPlan);
    }

    public Call<Void> deleteCarrerPlan(int id){
        return carrerPlanApi.deleteCarrerPlan(id);
    }

    public Call<CarrerPlanWithCourses> getCarrerPlanWithCourses(int id) {
        return carrerPlanApi.getCarrerPlanWithCourses(id);
    }

    public Call<List<String>> getAllCarrerPlanNames() {
        return carrerPlanApi.getAllCarrerPlanNames();
    }

    public Call<Void> assignCarrerPlanToStudent(int studentId, int planId) {
        return carrerPlanApi.assignCarrerPlanToStudent(studentId, planId);
    }



}
