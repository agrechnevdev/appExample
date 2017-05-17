package com.example.ch.model;

import com.google.gson.annotations.SerializedName;

public class ResponseChallenge {

    @SerializedName("challenge")
    private Challenge challenge;

    @SerializedName("status")
    private String status;

    public ResponseChallenge(Challenge challenge, String status) {
        this.challenge = challenge;
        this.status = status;
    }

    public Challenge getChallenge() {
        return challenge;
    }

    public void setChallenge(Challenge challenge) {
        this.challenge = challenge;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
