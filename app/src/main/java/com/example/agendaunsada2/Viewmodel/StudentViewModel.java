package com.example.agendaunsada2.Viewmodel;
// interactúe con el StudentService y proporcione datos a la interfaz de usuario.

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.agendaunsada2.Model.Student;
import com.example.agendaunsada2.Service.StudentService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentViewModel extends ViewModel{
    // Servicio para manejar las operaciones con los estudiantes
    private StudentService studentService;
    // LiveData para la lista de estudiantes
    private MutableLiveData<List<Student>> students;

    // LiveData para mensajes de error
    private MutableLiveData<String> errorMessage;

    // Constructor
    public StudentViewModel() {
        studentService = new StudentService(); // Inicializa el servicio de estudiantes
        students = new MutableLiveData<>(); // Inicializa LiveData para estudiantes
        errorMessage = new MutableLiveData<>();  // Inicializa LiveData para mensajes de error
        loadAllStudents();  // Carga todos los estudiantes al iniciar
    }
    // Retorna LiveData con la lista de estudiantes
    public LiveData<List<Student>> getStudents() {
        return students;
    }

    // Retorna LiveData con mensajes de error
    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    // Método privado para cargar todos los estudiantes desde el servicio
    private void loadAllStudents() {
        studentService.getAllStudents(new Callback<List<Student>>() {
            @Override
            public void onResponse(Call<List<Student>> call, Response<List<Student>> response) {
                // Si la respuesta es exitosa, actualiza el valor de students
                if (response.isSuccessful()) {
                    students.setValue(response.body());
                } else {
                    errorMessage.setValue("Código: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Student>> call, Throwable t) {
                // Si la llamada falla, establece el mensaje de error
                errorMessage.setValue(t.getMessage());
            }
        });
    }

    // Método para actualizar un estudiante
    public void updateStudent(int id, Student updatedStudent) {
        studentService.updateStudent(id, updatedStudent, new Callback<Student>() {
            @Override
            public void onResponse(Call<Student> call, Response<Student> response) {
                // Si la actualización es exitosa, recarga la lista de estudiantes
                if (response.isSuccessful()) {
                    loadAllStudents();
                } else {
                    errorMessage.setValue("Código: " + response.code() + " Mensaje: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Student> call, Throwable t) {
                errorMessage.setValue(t.getMessage());
            }
        });
    }

    // Método para actualizar parcialmente un estudiante
    public void updatePartialStudent(int id, Student updatedStudent) {
        studentService.updatePartialStudent(id, updatedStudent, new Callback<Student>() {
            @Override
            public void onResponse(Call<Student> call, Response<Student> response) {
                // Si la actualización parcial es exitosa, recarga la lista de estudiantes
                if (response.isSuccessful()) {
                    loadAllStudents();
                } else {
                    errorMessage.setValue("Código: " + response.code() + " Mensaje: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Student> call, Throwable t) {
                errorMessage.setValue(t.getMessage());
            }
        });
    }

    //ELIMINAR ESTUDIANTES
    public void deleteStudent(int id) {
        studentService.deleteStudent(id, new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    loadAllStudents();
                } else {
                    errorMessage.setValue("Código: " + response.code() + " Mensaje: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                errorMessage.setValue(t.getMessage());
            }
        });
    }

}
