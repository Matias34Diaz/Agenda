package com.example.agendaunsada2.Interface;


import com.example.agendaunsada2.Model.CarrerPlan;


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

    // Obtener todos los planes de carrera
    @GET("carrerPlans")
    Call<List<CarrerPlan>> getAllCarrerPlans();

    // Obtener un plan de carrera por ID
    @GET("carrerPlans/{id}")
    Call<CarrerPlan> getCarrerPlanById(@Path("id") int id);

    // Crear un nuevo plan de carrera
    @POST("carrerPlans")
    Call<CarrerPlan> createCarrerPlan(@Body CarrerPlan carrerPlan);

    // Actualizar un plan de carrera por ID (completo)
    @PUT("carrerPlans/{id}")
    Call<CarrerPlan> updateCarrerPlan(@Path("id") int id, @Body CarrerPlan carrerPlan);

    // Actualizar parcialmente un plan de carrera por ID
    @POST("carrerPlans/{id}")
    Call<CarrerPlan> updatePartialCarrerPlan(@Path("id") int id, @Body CarrerPlan carrerPlan);

    // Eliminar un plan de carrera por ID
    @DELETE("carrerPlans/{id}")
    Call<Void> deleteCarrerPlan(@Path("id") int id);
}
