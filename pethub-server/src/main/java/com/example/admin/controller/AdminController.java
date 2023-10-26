package com.example.admin.controller;

import com.example.client.common.ClientResponse;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/")
public class AdminController {

    // 登录 /user/login.do?username=xxx&password=xxx
    @CrossOrigin(origins = "http://localhost:7000")
    @PostMapping(value = "/user/login.do")
    public ClientResponse login(Object object) {
        System.out.println(object);
        ClientResponse response = ClientResponse.createServerResponseBySuccess(null, "");
        return response;
    }
}
