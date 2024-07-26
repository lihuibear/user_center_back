package com.lihui.user_center_back.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lihui.user_center_back.model.domain.User;

/**
* @author lihui
* @description 针对表【user(用户)】的数据库操作Service
* @createDate 2024-07-26 19:06:08
 *
 * 用户服务
 * @author lihui
*/

public interface UserService extends IService<User> {

    /**
     * 用户注册
     * @param userAccount 用户账户
     * @param userPassword 用户密码
     * @param checkPassword 校验密码
     * @return 新用户id
     */
    long userRegister(String userAccount, String userPassword, String checkPassword);

}
