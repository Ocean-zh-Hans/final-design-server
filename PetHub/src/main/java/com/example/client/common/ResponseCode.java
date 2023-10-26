package com.example.client.common;

import lombok.Getter;

import javax.management.loading.MLetContent;

@Getter
public enum ResponseCode {
    USERNAME_IS_EMPTY(1, "用户名不能为空"),
    PASSWORD_IS_EMPTY(2, "密码不能为空"),
    USERNAME_NOT_EXITS(3, "用户名不存在"),
    PASSWORD_ERROR(4, "密码错误"),
    PASSWORD_INCONSISTENT(5, "两次输入的密码不一致"),
    QUESTION_IS_EMPTY(6, "密保问题不能为空"),
    ANSWER_IS_EMPTY(7, "密保答案不能为空"),
    USER_ALREADY_EXIT(8, "用户名已被注册"),
    LOGIN_INVALID(9, "登录已失效"),
    ANSWER_ERROR(10, "密保答案错误"),

    ELSE_ERROR(99, "未知错误")
    ;


    private int code;
    private String msg;

    ResponseCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
