package com.example.ch.model;

import com.google.gson.annotations.SerializedName;

public class ResponseData {

    @SerializedName("username_email")
    private String username_email;

    @SerializedName("password")
    private String password;

    @SerializedName("email")
    private String email;

    @SerializedName("first_name")
    private String first_name;

    @SerializedName("last_name")
    private String last_name;

    public String getUsername_email() {
        return username_email;
    }

    public void setUsername_email(String username_email) {
        this.username_email = username_email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        return stringBuilder.toString();
    }
}
