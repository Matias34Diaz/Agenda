package com.example.agendaunsada2.Viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.agendaunsada2.Model.LoginResponse;
import com.example.agendaunsada2.Model.RegisterResponse;
import com.example.agendaunsada2.Model.User;
import com.example.agendaunsada2.Service.UserService;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserViewModel extends ViewModel {

    private final UserService userService;
    private final MutableLiveData<LoginResponse> loginResponse;
    private final MutableLiveData<RegisterResponse> registerResponse;
    private final MutableLiveData<String> errorMessage;
    private final MutableLiveData<Boolean> isLoading;

    public UserViewModel() {
        userService = new UserService();
        loginResponse = new MutableLiveData<>();
        registerResponse = new MutableLiveData<>();
        errorMessage = new MutableLiveData<>();
        isLoading = new MutableLiveData<>(false);
    }

    public LiveData<LoginResponse> getLoginResponse() {
        return loginResponse;
    }

    public LiveData<RegisterResponse> getRegisterResponse() {
        return registerResponse;
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    // Método para iniciar sesión
    public void loginUser(User user) {
        isLoading.setValue(true); // Indica que la operación está en curso

        userService.loginUser(user).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                isLoading.setValue(false); // Finaliza la operación
                if (response.isSuccessful() && response.body() != null) {
                    if ("success".equals(response.body().getStatus())) {
                        loginResponse.setValue(response.body());
                    } else {
                        errorMessage.setValue("Error en el login: Estado no exitoso.");
                    }
                } else {
                    String errorDetails = parseErrorResponse(response.errorBody());
                    errorMessage.setValue("Error en el login: " + response.message() + " - " + errorDetails);
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                isLoading.setValue(false); // Finaliza la operación
                errorMessage.setValue("Error de conexión: " + t.getMessage());
            }
        });
    }

    // Método para registrar un usuario
    public void registerUser(User user) {
        isLoading.setValue(true); // Indica que la operación está en curso

        userService.registerUser(user).enqueue(new Callback<RegisterResponse>() {

            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                isLoading.setValue(false); // Finaliza la operación
                if (response.isSuccessful() && response.body() != null) {
                    RegisterResponse serverResponse = response.body(); // Renombra esta variable
                    if (serverResponse.getStatus() == 201) { // Verifica el código de estado
                        registerResponse.setValue(serverResponse); // Asigna la respuesta al LiveData
                    } else {
                        errorMessage.setValue("Registro fallido: " + serverResponse.getMessage());
                    }
                } else {
                    // Manejo de error en la respuesta
                    try {
                        String errorDetails = response.errorBody() != null ? response.errorBody().string() : "Sin detalles";
                        errorMessage.setValue("Error en el registro: " + errorDetails);
                    } catch (IOException e) {
                        errorMessage.setValue("Error al procesar el mensaje de error.");
                    }
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                isLoading.setValue(false); // Finaliza la operación
                errorMessage.setValue("Error de conexión: " + t.getMessage());
            }
        });
    }

    // Método para procesar respuestas de error del backend
    private String parseErrorResponse(ResponseBody errorBody) {
        if (errorBody != null) {
            try {
                return errorBody.string();
            } catch (IOException e) {
                return "Error al procesar el mensaje de error.";
            }
        }
        return "Sin detalles.";
    }
}
