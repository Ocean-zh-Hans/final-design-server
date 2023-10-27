package com.example.client.service.impl;

import com.example.client.common.ResponseCode;
import com.example.dao.UserAccountMapper;
import com.example.dao.UserInfoMapper;
import com.example.pojo.UserAccount;
import com.example.client.service.IPersonalInfoService;
import com.example.utils.DateUtil;
import com.example.utils.MD5Util;
import com.example.client.common.ClientResponse;
import com.example.client.vo.UserLoginResponse;
import com.example.client.vo.UpdateUserAccountRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PersonalInfoService implements IPersonalInfoService {

    @Autowired
    UserAccountMapper accountMapper;
    @Autowired
    UserInfoMapper infoMapper;

    /**
     * 数据一致性判断实现方法
     *
     * @param userId: 用户ID
     * @param updateAt: 数据更新时间
     * @return 判断结果
     */
    @Override
    public ClientResponse splashLogic(int userId, String updateAt) {
        // step1: 解析更新时间
        Date updateTime = accountMapper.findUpdateAtByUserId(userId);
        // step2: 判断与数据库记录时间是否一致
        if (!DateUtil.dateToStr(updateTime).equals(updateAt)) {
            return ClientResponse.createServerResponseByFail(
                    ResponseCode.LOGIN_INVALID.getCode(),
                    ResponseCode.LOGIN_INVALID.getMsg());
        }
        // step3: 返回结果
        return ClientResponse.createServerResponseBySuccess(0, "校验成功");

    }

    /**
     * 用户登录实现方法
     *
     * @param username: 用户名
     * @param password: 密码
     * @return 登录结果
     */
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
            return ClientResponse.createServerResponseByFail(
                    ResponseCode.USERNAME_NOT_EXITS.getCode(),
                    ResponseCode.USERNAME_NOT_EXITS.getMsg());
        }

        String pwdMD5 = MD5Util.encrypt(password);

        // step3: 判断用户密码是否正确
        UserAccount account = accountMapper.findRowByUsernameAndPassword(username, pwdMD5);
        if (account == null) {
            return ClientResponse.createServerResponseByFail(
                    ResponseCode.PASSWORD_ERROR.getCode(),
                    ResponseCode.PASSWORD_ERROR.getMsg());
        }
        // step4: 返回结果
        return ClientResponse.createServerResponseBySuccess(convert(account));
    }

    /**
     * 提取发送给前端的用户信息
     *
     * @param account: 用户信息实体类
     * @return 用户数据
     */
    private UserLoginResponse convert(UserAccount account) {
        UserLoginResponse userData = new UserLoginResponse();
        userData.setUserId(account.getUserId());
        userData.setUsername(account.getUsername());
        userData.setCreatedAt(DateUtil.dateToStr(account.getCreatedAt()));
        userData.setUpdatedAt(DateUtil.dateToStr(account.getUpdatedAt()));
        return userData;
    }

    /**
     * 用户注册实现方法
     *
     * @param registerData: 用户发来的注册信息
     * @return 注册结果
     */
    @Override
    public ClientResponse registerLogic(UpdateUserAccountRequest registerData) {
        String username = registerData.getUsername();
        String password = registerData.getPassword();
        String confirm = registerData.getConfirm();
        String question = registerData.getQuestion();
        String answer = registerData.getAnswer();

        // step1: 用户名非空判断
        if (username.isBlank()) {
            return ClientResponse.createServerResponseByFail(
                    ResponseCode.USERNAME_IS_EMPTY.getCode(),
                    ResponseCode.USERNAME_IS_EMPTY.getMsg());
        }

        // step2: 查看用户名是否注册
        Integer count = accountMapper.findCountByUsername(username);
        if (count != null && count != 0) {
            // 用户已注册
            return ClientResponse.createServerResponseByFail(
                    ResponseCode.USER_ALREADY_EXIT.getCode(),
                    ResponseCode.USER_ALREADY_EXIT.getMsg());
        }

        //step3: 密码、密保问题、密保答案非空判断，两次输入密码一致性判断
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

        // step4: 数据库插入语句
        int result = accountMapper.insert(account);

        // step5: 返回结果
        if (result == 0) {
            return ClientResponse.createServerResponseByFail(
                    ResponseCode.ELSE_ERROR.getCode(),
                    ResponseCode.ELSE_ERROR.getMsg());
        }

        return ClientResponse.createServerResponseBySuccess(0, "校验成功");
    }

    /**
     * 获取密保问题请求实现方法
     *
     * @param username: 用户名
     * @return 密保问题
     */
    @Override
    public ClientResponse resetPerpLogic(String username) {
        // step1: 用户名非空判断
        if (username.isBlank()) {
            return ClientResponse.createServerResponseByFail(
                    ResponseCode.USERNAME_IS_EMPTY.getCode(),
                    ResponseCode.USERNAME_IS_EMPTY.getMsg());
        }
        // step2: 查看用户名是否注册
        Integer count = accountMapper.findCountByUsername(username);
        if (count == null || count == 0) {
            return ClientResponse.createServerResponseByFail(
                    ResponseCode.USERNAME_NOT_EXITS.getCode(),
                    ResponseCode.USERNAME_NOT_EXITS.getMsg());
        }
        // step3: 获取密保问题
        String answer = accountMapper.findQuestionByUsername(username);
        // step4: 返回密保问题
        return ClientResponse.createServerResponseByFail(0, answer);
    }

    /**
     * 重置密码请求实现方法
     *
     * @param resetData: 重置密码所需要的数据
     * @return 重置结果
     */
    @Override
    public ClientResponse resetLogic(UpdateUserAccountRequest resetData) {
        String username = resetData.getUsername();
        String password = resetData.getPassword();
        String confirm = resetData.getConfirm();
        String question = resetData.getQuestion();
        String answer = resetData.getAnswer();

        // step1: 用户名非空判断
        if (username.isBlank()) {
            return ClientResponse.createServerResponseByFail(
                    ResponseCode.USERNAME_IS_EMPTY.getCode(),
                    ResponseCode.USERNAME_IS_EMPTY.getMsg());
        }

        // step2: 查看用户名是否注册
        Integer count = accountMapper.findCountByUsername(username);
        if (count == null || count == 0) {
            // 用户名不存在
            return ClientResponse.createServerResponseByFail(
                    ResponseCode.USERNAME_NOT_EXITS.getCode(),
                    ResponseCode.USERNAME_NOT_EXITS.getMsg());
        }

        // step3: 密码、答案的非空判断和两次输入的密码一致性判断
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

        // step4: 验证密保答案是否正确
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

        // step5: 数据库插入语句
        int result = accountMapper.updatePasswordByOthers(account);
        if (result == 0) {
            return ClientResponse.createServerResponseByFail(
                    ResponseCode.ELSE_ERROR.getCode(),
                    ResponseCode.ELSE_ERROR.getMsg());
        }

        // step6: 返回结果
        return ClientResponse.createServerResponseBySuccess(0, "校验成功");
    }
}
