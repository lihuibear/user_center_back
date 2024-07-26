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
    public void testAddUser() {
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

    @Test
    void UserService() {
        //空密码
        String userAccount = "lihui1";
        String userPassword = "";
        String checkPassword = "12345678";
        long result = userService.userRegister(userAccount, userPassword, checkPassword);

        Assertions.assertEquals(-1, result);//断言

//        Assertions.assertTrue(result>0);//断言
        //用户小于4位
        userAccount = "li";
        userPassword = "";
        checkPassword = "12345678";
        result = userService.userRegister(userAccount, userPassword, checkPassword);

        Assertions.assertEquals(-1, result);//断言
        //密码小于8位
        userAccount = "lihui11";
        userPassword = "123456";
        checkPassword = "123456";
        result = userService.userRegister(userAccount, userPassword, checkPassword);

        Assertions.assertEquals(-1, result);//断言
        //密码校验密码不同
        userPassword = "12345678";
        checkPassword = "123456789";
        result = userService.userRegister(userAccount, userPassword, checkPassword);
        //用户名相同
        userAccount = "lihui11";
        userPassword = "123456789";
        result = userService.userRegister(userAccount, userPassword, checkPassword);

        Assertions.assertEquals(-1, result);//断言
        //正常注册
        userAccount = "yupi";
        userPassword = "12345678";
        checkPassword = "12345678";
        result = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertTrue(result > 0);//断言
    }

}