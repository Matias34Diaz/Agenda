package com.example.agendaunsada2.Interface;

import com.example.agendaunsada2.Model.Course;
import com.example.agendaunsada2.Model.Student;
import com.example.agendaunsada2.Model.StatusUpdateRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface StudentApi {
    @GET("students")
    Call<List<Student>> getAllStudents();

    @PUT("students/{id}")
    Call<Student> updateStudent(@Path("id") int id, @Body Student student);

    @PUT("students/{id}/partial")
    Call<Student> updatePartialStudent(@Path("id") int id, @Body Student student);

    @DELETE("students/{id}")
    Call<Void> deleteStudent(@Path("id") int id);

    @POST("students")
    Call<Student> createStudent(@Body Student student);

    @POST("students/with-plan")
    Call<Void> createStudentWithPlan(@Body Student student);

    @POST("students/execute-query")
    Call<Void> executeQuery();

    @PUT("students/{id}/courses/status")
    Call<Void> updateCourseStatus(@Path("id") int studentId, @Body StatusUpdateRequest request);

    @GET("students/{id}/courses/status")
    Call<List<Course>> getCourseStatus(@Path("id") int studentId);
    @GET("students/{id}/courses")
    Call<List<Course>> getStudentCourses(@Path("id") int studentId);

    @GET("students/{id}/courses/status")
    Call<List<Course>> getCourses(@Path("id") int studentId);

}

