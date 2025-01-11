package com.example.agendaunsada2.Interface;


import com.example.agendaunsada2.Model.CarrerPlan;
import com.example.agendaunsada2.Model.CarrerPlanWithCourses;
import com.example.agendaunsada2.Model.Student;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CarrerPlanApi {

    // Obtener todos los planes de carreras
    @GET("carrerPlans")
    Call<List<CarrerPlan>> getAllCarrerPlans();

    //Obtener un plan de carrera por id
    @GET("carrerPlan/{id}")
    Call<CarrerPlan> getCarrerPlansById(@Path("id") int id);

    // Obtener un plan de carrera y sus cursos por ID
    @GET("carrerPlans/{id}/courses")
    Call<CarrerPlanWithCourses> getCarrerPlanWithCourses(@Path("id") int id);

    // Crear un nuevo Plan de carrera
    @POST("carrerPlans")
    Call<CarrerPlan> createCarrerPlan(@Body CarrerPlan carrerPlan);

    // Actualizar un Plan de Carrera por ID (PUT)
    @PUT("carrerPlans/{id}")
    Call<CarrerPlan> updateCarrerPlan(@Path("id") int id, @Body CarrerPlan carrerPlan);

    // Actualizar parcialmente un estudiante por ID (PATCH)
    @PATCH("carrerPlans/{id}")
    Call<CarrerPlan> updatePartialCarrerPlan(@Path("id") int id, @Body CarrerPlan carrerPlan);

    // Eliminar plan de carrera por ID
    @DELETE("carrerPlans/{id}")
    Call<Void> deleteCarrerPlan(@Path("id") int id);

    @GET("carrer_plans/names")
    Call<List<String>> getAllCarrerPlanNames();

    @PATCH("students")
    Call<Void> assignCarrerPlanToStudent(@Path("studentId") int studentId, @Query("planId") int planId);

}
