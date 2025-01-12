package com.example.agendaunsada2.Interface;

import com.example.agendaunsada2.Model.LoginResponse;
import com.example.agendaunsada2.Model.RegisterResponse;
import com.example.agendaunsada2.Model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserApi {
    // Endpoint para registrar un usuario
    @POST("register")
    Call<RegisterResponse> registerUser(@Body User user);

    // Endpoint para iniciar sesión
    @POST("login")
    Call<LoginResponse> loginUser(@Body User user);
}
