package com.example.ch.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Status {

    @SerializedName("status")
    private String status;

    @SerializedName("errors")
    private ArrayList<String> errors;

    public Status(String status, ArrayList<String> errors) {
        this.status = status;
        this.errors = errors;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<String> getErrors() {
        return errors;
    }

    public void setErrors(ArrayList<String> errors) {
        this.errors = errors;
    }
}
