package com.example.agendaunsada2.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class User implements Serializable {

    @SerializedName("id")
    private Integer id;

    @SerializedName("name")
    private String name;

    @SerializedName("email")
    private String email;

    @SerializedName("google_id")
    private String googleId;

    @SerializedName("id_role")
    private Integer idRole;

    @SerializedName("email_verified_at")
    private String emailVerifiedAt;

    // Constructor completo
    public User(Integer id, String name, String email, String googleId, Integer idRole, String emailVerifiedAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.googleId = googleId;
        this.idRole = idRole;
        this.emailVerifiedAt = emailVerifiedAt;
    }

    // Constructor para Google Login
    public User(String googleId) {
        this.googleId = googleId;
    }

    // Getters y setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGoogleId() {
        return googleId;
    }

    public void setGoogleId(String googleId) {
        this.googleId = googleId;
    }

    public Integer getIdRole() {
        return idRole;
    }

    public void setIdRole(Integer idRole) {
        this.idRole = idRole;
    }

    public String getEmailVerifiedAt() {
        return emailVerifiedAt;
    }

    public void setEmailVerifiedAt(String emailVerifiedAt) {
        this.emailVerifiedAt = emailVerifiedAt;
    }
}
