package com.example.agendaunsada2.Service;

import com.example.agendaunsada2.Interface.CourseApi;
import com.example.agendaunsada2.Model.Course;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CourseService {

    private static final String BASE_URL = "https://flamingo-crack-implicitly.ngrok-free.app/api/";

    private final CourseApi courseApi;

    public CourseService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        courseApi = retrofit.create(CourseApi.class);
    }

    public Call<List<Course>> getAllCourses() {
        return courseApi.getAllCourses();
    }

    public Call<Course> getCourseById(int id) {
        return courseApi.getCourseById(id);
    }

    public Call<Course> createCourse(Course course) {
        return courseApi.createCourse(course);
    }

    public Call<Course> updateCourse(int id, Course course) {
        return courseApi.updateCourse(id, course);
    }

    public Call<Course> updatePartialCourse(int id, Course course) {
        return courseApi.updatePartialCourse(id, course);
    }

    public Call<Course> updateCourseStatus(int id, String status) {
        return courseApi.updateCourseStatus(id, status);
    }

    public Call<Void> deleteCourse(int id) {
        return courseApi.deleteCourse(id);
    }


}

