package com.lihui.user_center_back.controller;

import com.lihui.user_center_back.model.domain.User;
import com.lihui.user_center_back.model.domain.request.UserLoginRequest;
import com.lihui.user_center_back.model.domain.request.UserRegistRequest;
import com.lihui.user_center_back.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 用户接口
 *
 * @author lihui
 */

@RestController
@RequestMapping("/user")

public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/register")
    public Long userRegister(@RequestBody UserRegistRequest userRegistRequest) {
        if (userRegistRequest == null) {
            return null;
        }
        String userAccount = userRegistRequest.getUserAccount();
        String userPassword = userRegistRequest.getUserPassword();
        String checkPassword = userRegistRequest.getCheckPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword)) {
            return null;
        }
        return userService.userRegister(userAccount,userPassword,checkPassword);

    }

    @PostMapping("/login")
    public User userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request)  {
        if (userLoginRequest == null) {
            return null;
        }
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            return null;
        }
        return userService.userLogin(userAccount,userPassword,request);

    }
}
