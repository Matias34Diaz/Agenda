package com.example.agendaunsada2.Viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.agendaunsada2.Model.LoginResponse;
import com.example.agendaunsada2.Service.UserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserViewModel extends ViewModel {

    private final UserService userService;
    private final MutableLiveData<LoginResponse> loginResponse;
    private final MutableLiveData<String> errorMessage;
    private final MutableLiveData<Boolean> isLoading;

    public UserViewModel() {
        userService = new UserService();
        loginResponse = new MutableLiveData<>();
        errorMessage = new MutableLiveData<>();
        isLoading = new MutableLiveData<>(false);
    }

    public LiveData<LoginResponse> getLoginResponse() {
        return loginResponse;
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    // Método para realizar el login del usuario con Google
    public void loginUser(String googleId) {
        isLoading.setValue(true); // Indica que la operación está en curso

        userService.loginUser(googleId).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                isLoading.setValue(false); // Finaliza la operación
                if (response.isSuccessful() && response.body() != null) {
                    loginResponse.setValue(response.body());
                } else {
                    errorMessage.setValue("Error en el login: " + (response.message() != null ? response.message() : "Respuesta vacía"));
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                isLoading.setValue(false); // Finaliza la operación
                errorMessage.setValue("Error de conexión: " + t.getMessage());
            }
        });
    }
}

