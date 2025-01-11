package com.example.agendaunsada2.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class CarrerPlanWithCourses implements Serializable {
    private int id;

    @SerializedName("plan_name")
    private String planName;

    private List<Course> courses;

    // Constructor
    public CarrerPlanWithCourses() {}

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
