package com.example.agendaunsada2.Model;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("token") // Coincide con el campo "token" en la respuesta JSON
    private String token;

    @SerializedName("user") // Coincide con el campo "user" en la respuesta JSON
    private User user;

    // Constructor vacío
    public LoginResponse() {
    }

    // Getters y setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
