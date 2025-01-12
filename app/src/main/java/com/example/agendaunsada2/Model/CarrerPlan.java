package com.example.agendaunsada2.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CarrerPlan implements Serializable {

    @SerializedName("id")
    private Integer id;

    @SerializedName("id_carrer_name") // ID del nombre de la carrera
    private Integer idCarrerName;

    @SerializedName("proposal_code") // Código de propuesta
    private String proposalCode;

    @SerializedName("name") // Nombre del plan de carrera
    private String name;

    // Constructor completo
    public CarrerPlan(Integer id, Integer idCarrerName, String proposalCode, String name) {
        this.id = id;
        this.idCarrerName = idCarrerName;
        this.proposalCode = proposalCode;
        this.name = name;
    }

    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdCarrerName() {
        return idCarrerName;
    }

    public void setIdCarrerName(Integer idCarrerName) {
        this.idCarrerName = idCarrerName;
    }

    public String getProposalCode() {
        return proposalCode;
    }

    public void setProposalCode(String proposalCode) {
        this.proposalCode = proposalCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

