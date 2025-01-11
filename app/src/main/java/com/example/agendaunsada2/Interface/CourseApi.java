package com.example.agendaunsada2.Interface;

import com.example.agendaunsada2.Model.Course;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CourseApi {

    // Obtener todos los cursos
    @GET("courses")
    Call<List<Course>> getAllCourses();

    // Obtener un curso por ID
    @GET("courses/{id}")
    Call<Course> getCourseById(@Path("id") int id);

    // Crear un nuevo Curso
    @POST("courses")
    Call<Course> createCourse(@Body Course course);

    // Actualizar un curso por ID (PUT)
    @PUT("courses/{id}")
    Call<Course> updateCourse(@Path("id") int id, @Body Course course);

    // Actualizar parcialmente un curso por ID (PATCH)
    @PATCH("courses/{id}")
    Call<Course> updatePartialCourse(@Path("id") int id, @Body Course course);

    @PATCH("courses/{id}/status")
    Call<Course> updateCourseStatus(@Path("id") int id, @Body String status);

    // Eliminar un curso por ID
    @DELETE("courses/{id}")
    Call<Void> deleteCourse(@Path("id") int id);
}
