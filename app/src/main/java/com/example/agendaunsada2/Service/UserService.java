package com.example.agendaunsada2.Service;

import com.example.agendaunsada2.Interface.UserApi;
import com.example.agendaunsada2.Model.LoginResponse;
import com.example.agendaunsada2.Model.User;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserService {

    private static final String BASE_URL = "https://flamingo-crack-implicitly.ngrok-free.app/api/";
    private final UserApi userApi;

    public UserService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        userApi = retrofit.create(UserApi.class);
    }

    // Login de usuario con Google
    public Call<LoginResponse> loginUser(String googleId) {
        User user = new User(googleId); // Crear objeto User con googleId
        return userApi.loginUser(user);
    }
}
