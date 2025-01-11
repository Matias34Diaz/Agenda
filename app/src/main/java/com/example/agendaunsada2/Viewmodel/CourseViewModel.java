package com.example.agendaunsada2.Viewmodel;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.agendaunsada2.Model.CarrerPlanWithCourses;
import com.example.agendaunsada2.Model.Course;
import com.example.agendaunsada2.Service.CarrerPlanService;
import com.example.agendaunsada2.Service.CourseService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CourseViewModel extends ViewModel {

    private final CourseService courseService;

    private final CarrerPlanService carrerPlanService;
    private final MutableLiveData<List<Course>> courses;
    private final MutableLiveData<Course> course;
    private final MutableLiveData<String> errorMessage;

    private int currentCareerPlanId; // Almacena el ID del plan de carrera actual
    public CourseViewModel() {
        courseService = new CourseService();
        carrerPlanService = new CarrerPlanService();
        courses = new MutableLiveData<>();
        course = new MutableLiveData<>();
        errorMessage = new MutableLiveData<>();
    }

    public LiveData<List<Course>> getCourses() {
        return courses;
    }

    public LiveData<Course> getCourse() {
        return course;
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }



    // Obtener cursos por plan de carrera ID
    public void fetchCoursesByCarrerPlanId(int careerPlanId) {
        Call<CarrerPlanWithCourses> call = carrerPlanService.getCarrerPlanWithCourses(careerPlanId);
        call.enqueue(new Callback<CarrerPlanWithCourses>() {
            @Override
            public void onResponse(Call<CarrerPlanWithCourses> call, Response<CarrerPlanWithCourses> response) {
                if (response.isSuccessful()) {
                    courses.setValue(response.body().getCourses());
                } else {
                    errorMessage.setValue("Error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<CarrerPlanWithCourses> call, Throwable t) {
                errorMessage.setValue(t.getMessage());
            }
        });
    }

    // Actualizar el estado de un curso

    // Actualizar el estado de un curso
    public void updateCourseStatus(int courseId, String status) {
        Call<Course> call = courseService.updateCourseStatus(courseId, status);
        call.enqueue(new Callback<Course>() {
            @Override
            public void onResponse(Call<Course> call, Response<Course> response) {
                if (response.isSuccessful()) {
                    fetchCoursesByCarrerPlanId(currentCareerPlanId); // Refresca la lista de cursos
                } else {
                    errorMessage.setValue("Error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Course> call, Throwable t) {
                errorMessage.setValue(t.getMessage());
            }
        });
    }






    // Obtener todos los cursos
    public void fetchAllCourses() {
        Call<List<Course>> call = courseService.getAllCourses();
        call.enqueue(new Callback<List<Course>>() {
            @Override
            public void onResponse(Call<List<Course>> call, Response<List<Course>> response) {
                if (response.isSuccessful()) {
                    courses.setValue(response.body());
                } else {
                    errorMessage.setValue("Error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Course>> call, Throwable t) {
                errorMessage.setValue(t.getMessage());
            }
        });
    }

    // Obtener un curso por ID
    public void fetchCourseById(int id) {
        Call<Course> call = courseService.getCourseById(id);
        call.enqueue(new Callback<Course>() {
            @Override
            public void onResponse(Call<Course> call, Response<Course> response) {
                if (response.isSuccessful()) {
                    course.setValue(response.body());
                } else {
                    errorMessage.setValue("Error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Course> call, Throwable t) {
                errorMessage.setValue(t.getMessage());
            }
        });
    }

    // Crear un nuevo curso
    public void createCourse(Course newCourse) {
        Call<Course> call = courseService.createCourse(newCourse);
        call.enqueue(new Callback<Course>() {
            @Override
            public void onResponse(Call<Course> call, Response<Course> response) {
                if (response.isSuccessful()) {
                    fetchAllCourses(); // Refresca la lista de cursos
                } else {
                    errorMessage.setValue("Error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Course> call, Throwable t) {
                errorMessage.setValue(t.getMessage());
            }
        });
    }

    // Actualizar un curso por ID
    public void updateCourse(int id, Course updatedCourse) {
        Call<Course> call = courseService.updateCourse(id, updatedCourse);
        call.enqueue(new Callback<Course>() {
            @Override
            public void onResponse(Call<Course> call, Response<Course> response) {
                if (response.isSuccessful()) {
                    fetchAllCourses(); // Refresca la lista de cursos
                } else {
                    errorMessage.setValue("Error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Course> call, Throwable t) {
                errorMessage.setValue(t.getMessage());
            }
        });
    }

    // Actualizar parcialmente un curso por ID
    public void updatePartialCourse(int id, Course updatedCourse) {
        Call<Course> call = courseService.updatePartialCourse(id, updatedCourse);
        call.enqueue(new Callback<Course>() {
            @Override
            public void onResponse(Call<Course> call, Response<Course> response) {
                if (response.isSuccessful()) {
                    fetchAllCourses(); // Refresca la lista de cursos
                } else {
                    errorMessage.setValue("Error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Course> call, Throwable t) {
                errorMessage.setValue(t.getMessage());
            }
        });
    }

    // Eliminar un curso por ID
    public void deleteCourse(int id) {
        Call<Void> call = courseService.deleteCourse(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    fetchAllCourses(); // Refresca la lista de cursos
                } else {
                    errorMessage.setValue("Error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                errorMessage.setValue(t.getMessage());
            }
        });
    }



}

