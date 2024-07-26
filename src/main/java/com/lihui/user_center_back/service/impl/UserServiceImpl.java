package com.lihui.user_center_back.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lihui.user_center_back.model.domain.User;
import com.lihui.user_center_back.service.UserService;

import com.lihui.user_center_back.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
 * @author lihui
 * @description 针对表【user(用户)】的数据库操作Service实现
 * @createDate 2024-07-26 19:06:08
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

}




