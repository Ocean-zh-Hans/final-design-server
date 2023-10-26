package com.example.client.service.impl;

import com.example.client.common.ResponseCode;
import com.example.dao.UserAccountMapper;
import com.example.dao.UserInfoMapper;
import com.example.pojo.UserAccount;
import com.example.client.service.IPersonalInfoService;
import com.example.utils.DateUtil;
import com.example.utils.MD5Util;
import com.example.client.common.ClientResponse;
import com.example.client.vo.LoginResponseData;
import com.example.client.vo.UserRegisterVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PersonalInfoService implements IPersonalInfoService {

    @Autowired
    UserAccountMapper accountMapper;
    @Autowired
    UserInfoMapper infoMapper;

    @Override
    public ClientResponse loginLogic(String username, String password) {

        // step1: 用户名和密码的非空判断
        if (username.isBlank()) {
            return ClientResponse.createServerResponseByFail(
                    ResponseCode.USERNAME_IS_EMPTY.getCode(),
                    ResponseCode.USERNAME_IS_EMPTY.getMsg());
        }
        if (password.isBlank()) {
            return ClientResponse.createServerResponseByFail(
                    ResponseCode.PASSWORD_IS_EMPTY.getCode(),
                    ResponseCode.PASSWORD_IS_EMPTY.getMsg());
        }

        // step2: 查看用户名是否存在
        Integer count = accountMapper.findCountByUsername(username);
        if (count == null || count == 0) {
            // 用户名不存在
            return ClientResponse.createServerResponseByFail(
                    ResponseCode.USERNAME_NOT_EXITS.getCode(),
                    ResponseCode.USERNAME_NOT_EXITS.getMsg());
        }

        String pwdMD5 = MD5Util.encrypt(password);

        // step3: 根据用户名和密码查询
        UserAccount account = accountMapper.findRowByUsernameAndPassword(username, pwdMD5);
        // step4: 返回结果
        if (account == null) {
            return ClientResponse.createServerResponseByFail(
                    ResponseCode.PASSWORD_ERROR.getCode(),
                    ResponseCode.PASSWORD_ERROR.getMsg());
        }

        return ClientResponse.createServerResponseBySuccess(convert(account));
    }

    private LoginResponseData convert(UserAccount account) {
        LoginResponseData accountVO = new LoginResponseData();
        accountVO.setUserId(account.getUserId());
        accountVO.setUsername(account.getUsername());
        accountVO.setCreatedAt(DateUtil.dateToStr(account.getCreatedAt()));
        accountVO.setUpdatedAt(DateUtil.dateToStr(account.getUpdatedAt()));
        return accountVO;
    }

    @Override
    public ClientResponse registerLogic(UserRegisterVO registerVO) {

        String username = registerVO.getUsername();
        String password = registerVO.getPassword();
        String confirm = registerVO.getConfirm();
        String question = registerVO.getQuestion();
        String answer = registerVO.getAnswer();

        // step1: 用户名非空判断
        if (username.isBlank()) {
            return ClientResponse.createServerResponseByFail(
                    ResponseCode.USERNAME_IS_EMPTY.getCode(),
                    ResponseCode.USERNAME_IS_EMPTY.getMsg());
        }

        // step1: 查看用户名是否注册
        Integer count = accountMapper.findCountByUsername(username);
        if (count != null && count != 0) {
            // 用户已注册
            return ClientResponse.createServerResponseByFail(
                    ResponseCode.USER_ALREADY_EXIT.getCode(),
                    ResponseCode.USER_ALREADY_EXIT.getMsg());
        }

        // step2: 密码、确认密码的非空判断和问题答案的统一性
        if (password.isBlank()) {
            return ClientResponse.createServerResponseByFail(
                    ResponseCode.PASSWORD_IS_EMPTY.getCode(),
                    ResponseCode.PASSWORD_IS_EMPTY.getMsg());
        }
        if (!confirm.equals(password)) {
            return ClientResponse.createServerResponseByFail(
                    ResponseCode.PASSWORD_INCONSISTENT.getCode(),
                    ResponseCode.PASSWORD_INCONSISTENT.getMsg());
        }
        if (question.isBlank()) {
            return ClientResponse.createServerResponseByFail(
                    ResponseCode.QUESTION_IS_EMPTY.getCode(),
                    ResponseCode.QUESTION_IS_EMPTY.getMsg());
        }

        if (answer.isBlank()) {
            return ClientResponse.createServerResponseByFail(
                    ResponseCode.ANSWER_IS_EMPTY.getCode(),
                    ResponseCode.ANSWER_IS_EMPTY.getMsg());
        }

        String pwdMD5 = MD5Util.encrypt(password);

        UserAccount account = new UserAccount();

        account.setUsername(username);
        account.setPassword(pwdMD5);
        account.setQuestion(question);
        account.setAnswer(answer);
        account.setCreatedAt(new Date());
        account.setUpdatedAt(new Date());

        // step3: 数据库插入语句
        int result = accountMapper.insert(account);

        // step4: 返回结果
        if (result == 0) {
            return ClientResponse.createServerResponseByFail(
                    ResponseCode.ELSE_ERROR.getCode(),
                    ResponseCode.ELSE_ERROR.getMsg());
        }

        return ClientResponse.createServerResponseBySuccess(0, "校验成功");
    }

    @Override
    public ClientResponse splashLogic(int userId, String updateAt) {
        Date updateTime = accountMapper.findUpdateAtByUserId(userId);
        if (DateUtil.dateToStr(updateTime).equals(updateAt)) {
            return ClientResponse.createServerResponseBySuccess(0, "校验成功");
        } else {
            return ClientResponse.createServerResponseByFail(
                    ResponseCode.LOGIN_INVALID.getCode(),
                    ResponseCode.LOGIN_INVALID.getMsg()
            );
        }
    }

    @Override
    public ClientResponse resetPerpLogic(String username) {
        // step1: 用户名非空判断
        if (username.isBlank()) {
            return ClientResponse.createServerResponseByFail(
                    ResponseCode.USERNAME_IS_EMPTY.getCode(),
                    ResponseCode.USERNAME_IS_EMPTY.getMsg());
        }
        // step1: 查看用户名是否注册
        Integer count = accountMapper.findCountByUsername(username);
        if (count == null || count == 0) {
            // 用户名不存在
            return ClientResponse.createServerResponseByFail(
                    ResponseCode.USERNAME_NOT_EXITS.getCode(),
                    ResponseCode.USERNAME_NOT_EXITS.getMsg());
        }
        String answer = accountMapper.findQuestionByUsername(username);
        return ClientResponse.createServerResponseByFail(0, answer);
    }

    @Override
    public ClientResponse resetLogic(UserRegisterVO registerVO) {
        String username = registerVO.getUsername();
        String password = registerVO.getPassword();
        String confirm = registerVO.getConfirm();
        String question = registerVO.getQuestion();
        String answer = registerVO.getAnswer();

        // step1: 用户名非空判断
        if (username.isBlank()) {
            return ClientResponse.createServerResponseByFail(
                    ResponseCode.USERNAME_IS_EMPTY.getCode(),
                    ResponseCode.USERNAME_IS_EMPTY.getMsg());
        }

        // step1: 查看用户名是否注册
        Integer count = accountMapper.findCountByUsername(username);
        if (count == null || count == 0) {
            // 用户名不存在
            return ClientResponse.createServerResponseByFail(
                    ResponseCode.USERNAME_NOT_EXITS.getCode(),
                    ResponseCode.USERNAME_NOT_EXITS.getMsg());
        }

        // step2: 密码、确认密码的非空判断和问题答案的统一性
        if (password.isBlank()) {
            return ClientResponse.createServerResponseByFail(
                    ResponseCode.PASSWORD_IS_EMPTY.getCode(),
                    ResponseCode.PASSWORD_IS_EMPTY.getMsg());
        }
        if (!confirm.equals(password)) {
            return ClientResponse.createServerResponseByFail(
                    ResponseCode.PASSWORD_INCONSISTENT.getCode(),
                    ResponseCode.PASSWORD_INCONSISTENT.getMsg());
        }
        if (answer.isBlank()) {
            return ClientResponse.createServerResponseByFail(
                    ResponseCode.ANSWER_IS_EMPTY.getCode(),
                    ResponseCode.ANSWER_IS_EMPTY.getMsg());
        }

        UserAccount account = new UserAccount();
        account.setUsername(username);
        account.setQuestion(question);

        // step1: 查看用户名是否注册
        String answer1 = accountMapper.findAnswerByOthers(account);

        if (!answer1.equals(answer)) {
            return ClientResponse.createServerResponseByFail(
                    ResponseCode.ANSWER_ERROR.getCode(),
                    ResponseCode.ANSWER_ERROR.getMsg());
        }

        String pwdMD5 = MD5Util.encrypt(password);

        account.setAnswer(answer);
        account.setUpdatedAt(new Date());
        account.setPassword(pwdMD5);

        // step3: 数据库插入语句
        int result = accountMapper.updatePasswordByOthers(account);

        // step4: 返回结果
        if (result == 0) {
            return ClientResponse.createServerResponseByFail(
                    ResponseCode.ELSE_ERROR.getCode(),
                    ResponseCode.ELSE_ERROR.getMsg());
        }

        return ClientResponse.createServerResponseBySuccess(0, "校验成功");
    }
}
