package com.example.client.vo;


import lombok.Getter;

@Getter
public class UserLoginResponse {

    private Integer userId;

    private String username;

    private String createdAt;

    private String updatedAt;

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}