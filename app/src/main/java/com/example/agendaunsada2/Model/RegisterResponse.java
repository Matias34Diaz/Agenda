package com.example.agendaunsada2.Model;

import com.google.gson.annotations.SerializedName;

public class RegisterResponse {

    @SerializedName("id") // Identificador del usuario registrado
    private int id;

    @SerializedName("message") // Mensaje de éxito
    private String message;

    @SerializedName("status") // Código de estado
    private int status;

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}

