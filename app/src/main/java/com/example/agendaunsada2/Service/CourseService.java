package com.example.agendaunsada2.Service;


import com.example.agendaunsada2.Interface.CourseApi;
import com.example.agendaunsada2.Model.Course;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CourseService {

    private static final String BASE_URL = "https://5446-179-42-181-209.ngrok-free.app/api/"; // Cambia según la URL actual de tu backend.
    private final CourseApi courseApi;

    public CourseService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        courseApi = retrofit.create(CourseApi.class);
    }

    // Obtener todos los cursos
    public Call<List<Course>> getAllCourses() {
        return courseApi.getAllCourses();
    }

    // Obtener un curso por su ID
    public Call<Course> getCourseById(int courseId) {
        return courseApi.getCourseById(courseId);
    }

    // Crear un nuevo curso
    public Call<Course> createCourse(Course course) {
        return courseApi.createCourse(course);
    }

    // Actualizar un curso por su ID
    public Call<Course> updateCourse(int courseId, Course course) {
        return courseApi.updateCourse(courseId, course);
    }

    // Actualización parcial de un curso
    public Call<Course> updateCoursePartial(int courseId, Course course) {
        return courseApi.updateCoursePartial(courseId, course);
    }

    // Eliminar un curso por su ID
    public Call<Void> deleteCourse(int courseId) {
        return courseApi.deleteCourse(courseId);
    }

}

