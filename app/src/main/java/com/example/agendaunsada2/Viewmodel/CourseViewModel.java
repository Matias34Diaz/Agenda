package com.example.agendaunsada2.Viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.agendaunsada2.Model.Course;
import com.example.agendaunsada2.Service.CourseService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class CourseViewModel extends ViewModel {
    private final CourseService courseService;
    private final MutableLiveData<List<Course>> courses;
    private final MutableLiveData<String> errorMessage;
    private final MutableLiveData<Boolean> isLoading;

    public CourseViewModel() {
        courseService = new CourseService();
        courses = new MutableLiveData<>();
        errorMessage = new MutableLiveData<>();
        isLoading = new MutableLiveData<>(false);
    }

    // Exponer los datos al observador
    public LiveData<List<Course>> getCourses() {
        return courses;
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    // Método para cargar todos los cursos
    public void fetchCourses() {
        isLoading.setValue(true); // Inicia el indicador de carga

        courseService.getAllCourses().enqueue(new Callback<List<Course>>() {
            @Override
            public void onResponse(Call<List<Course>> call, Response<List<Course>> response) {
                isLoading.setValue(false); // Finaliza el indicador de carga
                if (response.isSuccessful() && response.body() != null) {
                    courses.setValue(response.body()); // Actualiza los datos
                } else {
                    errorMessage.setValue("Error al obtener los cursos: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Course>> call, Throwable t) {
                isLoading.setValue(false); // Finaliza el indicador de carga
                errorMessage.setValue("Error de conexión: " + t.getMessage());
            }
        });
    }

    // Método para manejar errores
    public void clearErrorMessage() {
        errorMessage.setValue(null);
    }

}

