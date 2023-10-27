package com.example.client.service;

import com.example.client.common.ClientResponse;
import com.example.client.vo.UpdateUserAccountRequest;

public interface IPersonalInfoService {

    public ClientResponse loginLogic(String username, String password);

    public ClientResponse registerLogic(UpdateUserAccountRequest registerVO);

    ClientResponse splashLogic(int userId, String updateAt);

    ClientResponse resetPerpLogic(String username);

    ClientResponse resetLogic(UpdateUserAccountRequest registerVO);
}