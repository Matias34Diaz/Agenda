package com.example.agendaunsada2.Model;

public class Student {
    private int id;
    private Integer id_user; // Integer para permitir valores null
    private Integer id_plan_carrera; //Integer para permitir valores null
    private String name;
    private String surname;
    private String dni;
    private String created_at;
    private String updated_at;

    public Student() {
    }

    public Student(int id, Integer id_user, Integer id_plan_carrera, String name, String surname, String dni, String created_at, String updated_at) {
        this.id = id;
        this.id_user = id_user;
        this.id_plan_carrera = id_plan_carrera;
        this.name = name;
        this.surname = surname;
        this.dni = dni;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getId_user() {
        return id_user;
    }

    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }

    public Integer getId_plan_carrera() {
        return id_plan_carrera;
    }

    public void setId_plan_carrera(Integer id_plan_carrera) {
        this.id_plan_carrera = id_plan_carrera;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
