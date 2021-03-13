package com.hai.service;

import com.hai.mapper.UserMapper;
import com.hai.pojo.PageHelper;
import com.hai.pojo.User;
import com.hai.util.MD5Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    public boolean loginOrRegister(User user){
        //user.setPassword(MD5Tool.getMD5(user.getPassword()));
        List<User> userByUsername = userMapper.findUserByUsername(user.getName());
        if(userByUsername.size()==0){
            userMapper.addUser(user);
        }else {
            return userByUsername.get(0).getPassword().equals(user.getPassword());
        }
        return true;
    }

    public PageHelper<User> getPageHelper(String name,String pageNum){
        PageHelper<User> pageHelper = new PageHelper<>();
        int current = 1;
        try {
            current=Integer.parseInt(pageNum);
        }catch (Exception e){
            e.printStackTrace();
        }
        if (name==null){
            name="";
        }
        pageHelper.setItemCount(userMapper.findUserNum(name));
        pageHelper.setPageCount((pageHelper.getItemCount()+ pageHelper.getItemsPerPage()-1)/ pageHelper.getItemsPerPage());
        pageHelper.setCurrentPage(current);
        pageHelper.setItems(userMapper.findPageUsers(name,(current-1)*pageHelper.getItemsPerPage(),pageHelper.getItemsPerPage()));
        return  pageHelper;
    }

    public PageHelper<User> getPageHelperByName(String name,String pageNum){
        PageHelper<User> pageHelper = new PageHelper<>();
        int current = 1;
        try {
            current=Integer.parseInt(pageNum);
        }catch (Exception e){
            e.printStackTrace();
        }
        if (name==null){
            name="";
        }
        pageHelper.setItemCount(userMapper.findUserNumByName(name));
        pageHelper.setPageCount((pageHelper.getItemCount()+ pageHelper.getItemsPerPage()-1)/ pageHelper.getItemsPerPage());
        pageHelper.setCurrentPage(current);
        pageHelper.setItems(userMapper.findPageUsersByName(name,(current-1)*pageHelper.getItemsPerPage(),pageHelper.getItemsPerPage()));
        return  pageHelper;
    }

    public int queryUserRole(String name){
        return userMapper.queryUserRole(name);
    }
}
