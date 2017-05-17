package com.example.ch.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ListChallenges {

    @SerializedName("status")
    private String status;

    @SerializedName("data")
    private String data;

    @SerializedName("challenges")
    private List<Challenge> challenges;

    @SerializedName("errors")
    private ArrayList<String> errors;

    public ListChallenges(String status, String data, List<Challenge> challenges, ArrayList<String> errors) {
        this.status = status;
        this.data = data;
        this.challenges = challenges;
        this.errors = errors;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Challenge> getChallenges() {
        return challenges;
    }

    public void setChallenges(List<Challenge> challenges) {
        this.challenges = challenges;
    }

    public ArrayList<String> getErrors() {
        return errors;
    }

    public void setErrors(ArrayList<String> errors) {
        this.errors = errors;
    }
}
