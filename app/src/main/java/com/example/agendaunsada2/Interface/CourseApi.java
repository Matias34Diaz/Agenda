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

    // Obtener un curso por su ID
    @GET("courses/{id}")
    Call<Course> getCourseById(@Path("id") int courseId);

    // Crear un nuevo curso
    @POST("courses")
    Call<Course> createCourse(@Body Course course);

    // Actualizar un curso por su ID
    @PUT("courses/{id}")
    Call<Course> updateCourse(@Path("id") int courseId, @Body Course course);

    // Actualización parcial de un curso por su ID
    @PATCH("courses/{id}")
    Call<Course> updateCoursePartial(@Path("id") int courseId, @Body Course course);

    // Eliminar un curso por su ID
    @DELETE("courses/{id}")
    Call<Void> deleteCourse(@Path("id") int courseId);

}
