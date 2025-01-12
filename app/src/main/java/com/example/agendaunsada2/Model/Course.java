package com.example.agendaunsada2.Model;


import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class Course implements Serializable {

    @SerializedName("id")
    private Integer id;

    @SerializedName("name")
    private String name;

    @SerializedName("course_code")
    private String courseCode;

    @SerializedName("year")
    private Integer year;

    @SerializedName("semester")
    private Integer semester;

    @SerializedName("id_department")
    private Integer departmentId;

    // Constructor completo
    public Course(Integer id, String name, String courseCode, Integer year, Integer semester, Integer departmentId) {
        this.id = id;
        this.name = name;
        this.courseCode = courseCode;
        this.year = year;
        this.semester = semester;
        this.departmentId = departmentId;
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

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }
}
