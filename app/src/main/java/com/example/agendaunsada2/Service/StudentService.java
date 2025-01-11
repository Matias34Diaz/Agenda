package com.example.agendaunsada2.Service;


import com.example.agendaunsada2.Interface.StudentApi;
import com.example.agendaunsada2.Model.Course;
import com.example.agendaunsada2.Model.StatusUpdateRequest;
import com.example.agendaunsada2.Model.Student;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StudentService {
    private static final String BASE_URL = "https://flamingo-crack-implicitly.ngrok-free.app/api/";
    private StudentApi studentApi;
    private Retrofit retrofit;

    public StudentService() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        studentApi = retrofit.create(StudentApi.class);
    }

    // Método para obtener los cursos de un estudiante específico
    public Call<List<Course>> getCourses(int studentId) {
        return studentApi.getCourses(studentId);
    }

    public void getAllStudents(Callback<List<Student>> callback) {
        Call<List<Student>> call = studentApi.getAllStudents();
        call.enqueue(callback);
    }

    public void updateStudent(int id, Student updatedStudent, Callback<Student> callback) {
        Call<Student> call = studentApi.updateStudent(id, updatedStudent);
        call.enqueue(callback);
    }

    public void updatePartialStudent(int id, Student updatedStudent, Callback<Student> callback) {
        Call<Student> call = studentApi.updatePartialStudent(id, updatedStudent);
        call.enqueue(callback);
    }

    public void deleteStudent(int id, Callback<Void> callback) {
        Call<Void> call = studentApi.deleteStudent(id);
        call.enqueue(callback);
    }

    public Call<Student> createStudent(Student student) {
        return studentApi.createStudent(student);
    }

    public Call<Void> createStudentWithPlan(Student student) {
        return studentApi.createStudentWithPlan(student);
    }

    public void executeQuery(Callback<Void> callback) {
        Call<Void> call = studentApi.executeQuery();
        call.enqueue(callback);
    }

    public void updateCourseStatus(int studentId, StatusUpdateRequest request, Callback<Void> callback) {
        Call<Void> call = studentApi.updateCourseStatus(studentId, request);
        call.enqueue(callback);
    }

    public void getCourseStatus(int studentId, Callback<List<Course>> callback) {
        Call<List<Course>> call = studentApi.getCourseStatus(studentId);
        call.enqueue(callback);
    }

    public Call<List<Course>> getStudentCourses(int studentId) {
        return studentApi.getStudentCourses(studentId);
    }
}

