package com.lihui.user_center_back.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lihui.user_center_back.model.domain.User;
import com.lihui.user_center_back.model.domain.request.UserLoginRequest;
import com.lihui.user_center_back.model.domain.request.UserRegistRequest;
import com.lihui.user_center_back.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.lihui.user_center_back.contant.UserContant.ADMIN_ROLE;
import static com.lihui.user_center_back.contant.UserContant.USER_LOGIN_STATE;

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
        return userService.userRegister(userAccount, userPassword, checkPassword);

    }

    @PostMapping("/login")
    public User userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
        if (userLoginRequest == null) {
            return null;
        }
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            return null;
        }
        return userService.userLogin(userAccount, userPassword, request);

    }

    @GetMapping("current")
    public User getLoginUser(HttpServletRequest request) {
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User currentUser = (User) userObj;
        if (currentUser == null) {
            return null;
        }
        long userId = currentUser.getId();
        User user = userService.getById(userId);
        //todo 校验用户是否合法

        return userService.getSafetyUser(user);

    }

    /**
     * POST http://localhost:7070/user/login
     * Content-Type: application/json
     * <p>
     * {
     * "userAccount": "lihui11",
     * "userPassword": "12345678"
     * }
     **/
    @GetMapping("/search")

    public List<User> searchUsers(String username, HttpServletRequest request) {
//        //鉴权 仅管理员可见
//        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
//        User user = (User) userObj;
//        if (user == null || user.getRole() != ADMIN_ROLE) { //用常量替换
//            return new ArrayList<>();
//        }
        if (!isAdmin(request)) {
            return new ArrayList<>();
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNoneBlank(username)) {
            queryWrapper.like("username", username);
        }
        List<User> userlist = userService.list(queryWrapper);
        return userlist.stream().map(user -> userService.getSafetyUser(user)).collect(Collectors.toList());
    }

    @PostMapping("/delete")
    public boolean deleteUsers(@RequestBody long id, HttpServletRequest request) {
//        //鉴权 仅管理员可见
//        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
//        User user = (User) userObj;
//        if (user == null || user.getRole() != ADMIN_ROLE) { //用常量替换
//            return false;
//        }
        if (!isAdmin(request)) {
            return false;
        }
        if (id <= 0) {
            return false;
        }
        return userService.removeById(id);
    }

    /**
     * * 鉴权 仅管理员可见
     *
     * @param request
     * @return
     */
    private boolean isAdmin(HttpServletRequest request) {
        //鉴权 仅管理员可见
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User user = (User) userObj;
        if (user == null || user.getUserRole() != ADMIN_ROLE) { //用常量替换
            return false;
        }
        return true;
    }


}
