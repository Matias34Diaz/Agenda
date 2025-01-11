package com.example.agendaunsada2.Viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.agendaunsada2.Model.CarrerPlan;
import com.example.agendaunsada2.Model.CarrerPlanWithCourses;
import com.example.agendaunsada2.Model.Course;
import com.example.agendaunsada2.Model.StatusUpdateRequest;
import com.example.agendaunsada2.Model.Student;
import com.example.agendaunsada2.Service.CarrerPlanService;
import com.example.agendaunsada2.Service.StudentService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CarrerPlanViewModel extends ViewModel {

    // Servicios para manejar las operaciones de planes de carrera y estudiantes
    private final CarrerPlanService carrerPlanService;
    private final StudentService studentService;

    // LiveData que mantiene el estado y los datos necesarios para la UI
    private final MutableLiveData<List<CarrerPlan>> carrerPlans = new MutableLiveData<>();
    private final MutableLiveData<CarrerPlanWithCourses> carrerPlanWithCourses = new MutableLiveData<>();
    private final MutableLiveData<List<Course>> courses = new MutableLiveData<>();
    private final MutableLiveData<Boolean> updateCourseStatusResult = new MutableLiveData<>();
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();

    public CarrerPlanViewModel() {
        carrerPlanService = new CarrerPlanService();
        studentService = new StudentService();
    }

    // Devuelve los planes de carrera como LiveData para observar cambios
    public LiveData<List<CarrerPlan>> getCarrerPlans() {
        return carrerPlans;
    }

    // Devuelve un plan de carrera con sus cursos como LiveData
    public LiveData<CarrerPlanWithCourses> getCarrerPlanWithCourses() {
        return carrerPlanWithCourses;
    }

    // Devuelve los cursos de un estudiante como LiveData
    public LiveData<List<Course>> getCourses() {
        return courses;
    }

    public LiveData<Boolean> getUpdateCourseStatusResult() {
        return updateCourseStatusResult;
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    // Método para obtener todos los planes de carrera desde el servicio
    public void fetchAllCarrerPlans() {
        carrerPlanService.getAllCarrerPlans().enqueue(new Callback<List<CarrerPlan>>() {
            @Override
            public void onResponse(Call<List<CarrerPlan>> call, Response<List<CarrerPlan>> response) {
                if (response.isSuccessful()) {
                    // Actualiza LiveData con los planes de carrera recibidos
                    carrerPlans.setValue(response.body());
                } else {
                    // Mensaje de error en caso de una respuesta fallida
                    errorMessage.setValue("Error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<CarrerPlan>> call, Throwable t) {
                // Mensaje de error en caso de fallo en la llamada
                errorMessage.setValue("Error: " + t.getMessage());
            }
        });
    }

    // Método para obtener un plan de carrera específico con cursos por su ID
    public void fetchCarrerPlanWithCourses(int id) {
        carrerPlanService.getCarrerPlanWithCourses(id).enqueue(new Callback<CarrerPlanWithCourses>() {
            @Override
            public void onResponse(Call<CarrerPlanWithCourses> call, Response<CarrerPlanWithCourses> response) {
                if (response.isSuccessful()) {
                    // Actualiza LiveData con el plan de carrera con cursos recibido
                    carrerPlanWithCourses.setValue(response.body());
                } else {
                    errorMessage.setValue("Error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<CarrerPlanWithCourses> call, Throwable t) {
                errorMessage.setValue("Error: " + t.getMessage());
            }
        });
    }

    // Método para obtener los cursos con su estado para un estudiante específico
    public void fetchCourseStatus(int studentId) {
        studentService.getCourseStatus(studentId, new Callback<List<Course>>() {
            @Override
            public void onResponse(Call<List<Course>> call, Response<List<Course>> response) {
                if (response.isSuccessful()) {
                    // Actualiza LiveData con los cursos y sus estados recibidos
                    courses.setValue(response.body());
                } else {
                    // Mensaje de error en caso de una respuesta fallida
                    errorMessage.setValue("Error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Course>> call, Throwable t) {
                // Mensaje de error en caso de fallo en la llamada
                errorMessage.setValue("Error: " + t.getMessage());
            }
        });
    }


    // Método para crear un nuevo estudiante junto con su plan de carrera y devolver el estado de éxito
    public LiveData<Boolean> createStudentWithPlan(Student student) {
        MutableLiveData<Boolean> result = new MutableLiveData<>();
        studentService.createStudentWithPlan(student).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    result.setValue(true);
                } else {
                    result.setValue(false);
                    errorMessage.setValue("Error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                result.setValue(false);
                errorMessage.setValue("Error: " + t.getMessage());
            }
        });
        return result;
    }

    // Método para crear un nuevo estudiante
    public LiveData<Boolean> createStudent(Student student) {
        MutableLiveData<Boolean> result = new MutableLiveData<>();
        studentService.createStudent(student).enqueue(new Callback<Student>() {
            @Override
            public void onResponse(Call<Student> call, Response<Student> response) {
                if (response.isSuccessful()) {
                    result.setValue(true);
                } else {
                    result.setValue(false);
                    errorMessage.setValue("Error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Student> call, Throwable t) {
                result.setValue(false);
                errorMessage.setValue("Error: " + t.getMessage());
            }
        });
        return result;
    }

    // Método para ejecutar la query que asigna materias
    public void executeQuery() {
        studentService.executeQuery(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()) {
                    errorMessage.setValue("Error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                errorMessage.setValue("Error: " + t.getMessage());
            }
        });
    }

    // Método para actualizar el estado de los cursos de un estudiante
    public void updateCourseStatus(int studentId, StatusUpdateRequest request) {
        studentService.updateCourseStatus(studentId, request, new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    updateCourseStatusResult.setValue(true);
                } else {
                    updateCourseStatusResult.setValue(false);
                    errorMessage.setValue("Error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                updateCourseStatusResult.setValue(false);
                errorMessage.setValue("Error: " + t.getMessage());
            }
        });
    }
}
