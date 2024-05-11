package com.example.dietapplication;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("userId")
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
