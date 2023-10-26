package com.example.client.service;

import com.example.client.common.ClientResponse;
import com.example.client.vo.LoginResponseData;
import com.example.client.vo.UserRegisterVO;

public interface IPersonalInfoService {

    public ClientResponse loginLogic(String username, String password);

    public ClientResponse registerLogic(UserRegisterVO registerVO);

    ClientResponse splashLogic(int userId, String updateAt);

    ClientResponse resetPerpLogic(String username);

    ClientResponse resetLogic(UserRegisterVO registerVO);
}