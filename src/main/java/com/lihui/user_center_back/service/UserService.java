package com.lihui.user_center_back.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lihui.user_center_back.model.domain.User;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lihui
 * @author lihui
 * @description 针对表【user(用户)】的数据库操作Service
 * @createDate 2024-07-26 19:06:08
 * <p>
 * 用户服务
 */

public interface UserService extends IService<User> {



    /**
     * 用户注册
     *
     * @param userAccount   用户账户
     * @param userPassword  用户密码
     * @param checkPassword 校验密码
     * @return 新用户id
     */
    long userRegister(String userAccount, String userPassword, String checkPassword);


    /**
     * 用户登录
     *
     * @param userAccount  用户账户
     * @param userPassword 用户密码
     * @return 脱敏后的用户信息
     */

    User userLogin(String userAccount, String userPassword, HttpServletRequest request);

    /**
     * 用户脱敏
     * @param originUser
     * @return
     */
    User getSafetyUser(User originUser);

    /**
     * 用户注销
     * @param request
     */

    int userLogout(HttpServletRequest request);

}
