package com.example.ch.model;


import com.google.gson.annotations.SerializedName;

public class Challenge {

    @SerializedName("id")
    private String id;

    @SerializedName("full_description")
    private String full_description;

    @SerializedName("short_description")
    private String short_description;

    public Challenge(String id, String full_descrition, String short_description) {
        this.id = id;
        this.full_description = full_descrition;
        this.short_description = short_description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFull_description() {
        return full_description;
    }

    public void setFull_description(String full_description) {
        this.full_description = full_description;
    }

    public String getShort_description() {
        return short_description;
    }

    public void setShort_description(String short_description) {
        this.short_description = short_description;
    }
}
