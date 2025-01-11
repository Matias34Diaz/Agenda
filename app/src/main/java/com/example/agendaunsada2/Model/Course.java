package com.example.agendaunsada2.Model;


import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class Course implements Serializable {

    @SerializedName("course_id")
    private int courseId;

    @SerializedName("status_id")
    private int statusId;

    @SerializedName("course_name")
    private String courseName;

    @SerializedName("status_name")
    private String statusName;

    // Constructor predeterminado sin parámetros
    public Course() {
    }

    // Constructor con parámetros
    public Course(int courseId, int statusId, String courseName, String statusName) {
        this.courseId = courseId;
        this.statusId = statusId;
        this.courseName = courseName;
        this.statusName = statusName;
    }

    // Getters y Setters
    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    @Override
    public String toString() {
        return courseName; // Retorna el nombre del curso como representación de texto
    }
}
