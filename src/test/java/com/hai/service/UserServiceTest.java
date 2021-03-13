package com.hai.service;

import com.hai.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by chenz at 22:24 on 2021/2/26
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    public void balanceOut() {
        userService.balanceOut(100,1);
    }

    @Test
    public void testUser(){
        for (int i = 0; i < 100; i++) {
            User user = new User();
            user.setPassword("123456");
            user.setName("test"+i);
            user.setBalance(100);
            userService.loginOrRegister(user);
        }
    }
}