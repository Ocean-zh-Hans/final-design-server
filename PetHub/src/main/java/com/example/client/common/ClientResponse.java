package com.example.client.common;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;

/**
 * 封装前端返回的统一实体类
 * @param <T>
 */
@Getter
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ClientResponse<T> {
    private int status;
    private T data;
    private String msg;

    private ClientResponse() {
    }

    private ClientResponse(int status) {
        this.status = status;
    }



    private ClientResponse(int status, T data) {
        this.status = status;
        this.data = data;
    }

    private ClientResponse(int status, T data, String msg) {
        this.status = status;
        this.data = data;
        this.msg = msg;
    }

    @JsonIgnore
    public boolean isSuccess() {
        return this.status == 0;
    }

    public static ClientResponse createServerResponseBySuccess() {
        return new ClientResponse(0);
    }

    public static <T> ClientResponse createServerResponseBySuccess(T data) {
        return new ClientResponse(0, data);
    }

    public static <T> ClientResponse createServerResponseBySuccess(T data, String msg) {
        return new ClientResponse(0, data, msg);
    }

    public static ClientResponse createServerResponseByFail(int status) {
        return new ClientResponse(status);
    }

    public static ClientResponse createServerResponseByFail(int status, String msg) {
        return new ClientResponse(status, null, msg);
    }

    private void setStatus(int status) {
        this.status = status;
    }

    private void setData(T data) {
        this.data = data;
    }

    private void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "ServerResponse{" +
                "status=" + status +
                ", data=" + data +
                ", msg='" + msg + '\'' +
                '}';
    }
}