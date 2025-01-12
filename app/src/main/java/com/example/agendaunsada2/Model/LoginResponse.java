package com.example.agendaunsada2.Model;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("status") // Indica el estado de la operación ("success")
    private String status;

    @SerializedName("user") // Información del usuario autenticado
    private User user;

    // Getters y setters
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

