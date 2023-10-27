package com.example.client.controller;

import com.example.common.Const;
import com.example.client.service.IPersonalInfoService;
import com.example.client.common.ClientResponse;
import com.example.client.vo.UpdateUserAccountRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    /**
     * 数据一致性判断
     * Request{method=GET, url=http://192.168.1.199:8080/portal/user/splash.do?updateAt=2023-10-26+23%3A17%3A22&userId=3}
     * @param userId: 用户ID
     * @param updateAt: 客户端用户信息更新时间
     * @return 响应体
     */
    @GetMapping(value = "user/splash.do")
    public ClientResponse splash(String userId, String updateAt) {
        String updateTime = URLDecoder.decode(updateAt, StandardCharsets.UTF_8);
        int id = Integer.parseInt(userId);
        return service.splashLogic(id, updateTime);
    }


    /**
     * 注册请求
     * Request{method=POST, url=http://192.168.1.199:8080/portal/user/register.do}
     * @param registerData: 注册信息
     * @return 响应体
     */
    @PostMapping(value = "user/register.do")
    public ClientResponse register(UpdateUserAccountRequest registerData) {
        ClientResponse response = service.registerLogic(registerData);
        System.out.println(response.toString());
        return response;
    }

    /**
     * 登录请求
     * Request{method=POST, url=http://192.168.1.199:8080/portal/user/login.do}
     * @param username: 用户名
     * @param password: 密码
     * @param session: 会话，用于跟踪用户状态
     * @return 响应体
     */
    @PostMapping(value = "user/login.do")
    public ClientResponse login(String username, String password, HttpSession session) {
        ClientResponse response = service.loginLogic(username, password);
        if (response.isSuccess()){
            session.setAttribute(Const.CURRENT_USER, response.getData());
        }
        return response;
    }

    /**
     * 获取密保问题请求
     * Request{method=GET, url=http://192.168.1.199:8080/portal/user/reset.prep?username=1234}
     * @param username: 用户名
     * @return 响应体
     */
    @GetMapping(value = "user/reset.prep")
    public ClientResponse resetPerp(String username) {
        return service.resetPerpLogic(username);
    }

    /**
     * 重置密码请求
     * Request{method=POST, url=http://192.168.1.199:8080/portal/user/reset.do}
     * @param resetData: 密码重置信息
     * @return 响应体
     */
    @PostMapping(value = "user/reset.do")
    public ClientResponse reset(UpdateUserAccountRequest resetData) {
        return service.resetLogic(resetData);
    }

}
