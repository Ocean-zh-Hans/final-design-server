package com.example.client.vo;

import lombok.Getter;

@Getter
public class UserRegisterVO {
    private Integer userId;
    private String username;
    private String password;
    private String confirm;
    private String question;
    private String answer;

    public UserRegisterVO() {
    }

    public UserRegisterVO(Integer userId, String username, String password, String confirm, String question, String answer) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.confirm = confirm;
        this.question = question;
        this.answer = answer;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "UserRegisterVO{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", confirm='" + confirm + '\'' +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }
}
