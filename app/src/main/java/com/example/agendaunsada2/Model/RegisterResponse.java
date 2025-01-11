package com.example.agendaunsada2.Model;

import com.google.gson.annotations.SerializedName;

public class RegisterResponse {
    @SerializedName("id_user")
    private Integer idUser;
    private String message;
    private int status;

    // Getters and setters
    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
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
