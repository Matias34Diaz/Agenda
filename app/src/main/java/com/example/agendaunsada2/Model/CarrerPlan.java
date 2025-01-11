package com.example.agendaunsada2.Model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class CarrerPlan implements Serializable {

    private int id;
    private String name;

    @SerializedName("proposal_code")
    private String proposalCode;

    // Constructor
    public CarrerPlan() {
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProposalCode() {
        return proposalCode;
    }

    public void setProposalCode(String proposalCode) {
        this.proposalCode = proposalCode;
    }
}
