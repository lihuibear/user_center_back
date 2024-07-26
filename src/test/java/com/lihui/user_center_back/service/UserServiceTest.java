package com.lihui.user_center_back.service;
import java.util.Date;

import com.lihui.user_center_back.model.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest

class UserServiceTest {
    @Resource

    private UserService userService;
    @Test
    public void testAddUser(){
        User user = new User();

        user.setUsername("lihui11");
        user.setUserAccount("lihui11");
        user.setAvatarUrl("https://blog-1319612571.cos.ap-shanghai.myqcloud.com/public/images/avatar.jpg");
        user.setGender(0);
        user.setUserPassword("12345678");
        user.setPhone("111");
        user.setEmail("222");
        boolean result = userService.save(user);

        System.out.println(user.getId());
        Assertions.assertTrue(result);//断言

    }

}