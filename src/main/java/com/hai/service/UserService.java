package com.hai.service;

import com.hai.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by chenz at 22:22 on 2021/2/26
 */
@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    @Transactional
    public void balanceOut(int out,int id){
        userMapper.balanceOut(out,id);
    }

    @Transactional
    public void balanceIn(int in,int id){
        userMapper.balanceIn(in,id);
    }
}
