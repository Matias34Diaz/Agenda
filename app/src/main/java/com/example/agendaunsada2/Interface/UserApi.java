package com.example.agendaunsada2.Interface;

import com.example.agendaunsada2.Model.LoginResponse;
import com.example.agendaunsada2.Model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserApi {
    // Login de usuario con Google
    @POST("login/google")
    Call<LoginResponse> loginUser(@Body User user);
}
