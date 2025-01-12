package com.example.agendaunsada2.Service;

import com.example.agendaunsada2.Interface.UserApi;
import com.example.agendaunsada2.Model.LoginResponse;
import com.example.agendaunsada2.Model.RegisterResponse;
import com.example.agendaunsada2.Model.User;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserService {

    private static final String BASE_URL = "https://5446-179-42-181-209.ngrok-free.app/api/"; // Cambia esta URL si tu backend está en producción
    private final UserApi userApi;

    public UserService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        userApi = retrofit.create(UserApi.class);
    }

    // Método para registrar un usuario
    public Call<RegisterResponse> registerUser(User user) {
        return userApi.registerUser(user);
    }

    // Método para iniciar sesión
    public Call<LoginResponse> loginUser(User user) {
        return userApi.loginUser(user);
    }
}

