package com.example.client.controller;

import com.example.client.vo.LoginResponseData;
import com.example.common.Const;
import com.example.client.service.IPersonalInfoService;
import com.example.client.common.ClientResponse;
import com.example.client.vo.UserRegisterVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/portal/")
public class UserController {

    @Autowired
    IPersonalInfoService service;

    // 注册 /user/register.do?username=xxx&password=xxx&securityQuestion=xxx&securityAnswer=xxx
    @RequestMapping(value = "user/register.do")
    public ClientResponse register(UserRegisterVO registerVO) {
        ClientResponse response = service.registerLogic(registerVO);
        System.out.println(response.toString());
        return response;
    }

    // 登录 /user/login.do?username=xxx&password=xxx
    @RequestMapping(value = "user/login.do")
    public ClientResponse login(String username, String password, HttpSession session) {
        ClientResponse response = service.loginLogic(username, password);
        if (response.isSuccess()){
            session.setAttribute(Const.CURRENT_USER, response.getData());
        }
        return response;
    }

    @RequestMapping(value = "user/splash.do")
    public ClientResponse splash(String userId, String updateAt) {
        String updateTime = URLDecoder.decode(updateAt, StandardCharsets.UTF_8);
        int id = Integer.parseInt(userId);
        return service.splashLogic(id, updateTime);
    }

    @RequestMapping(value = "user/reset.prep")
    public ClientResponse resetPerp(String username) {
        return service.resetPerpLogic(username);
    }

    @RequestMapping(value = "user/reset.do")
    public ClientResponse reset(UserRegisterVO registerVO) {
        return service.resetLogic(registerVO);
    }

}
