package com.hai.controller;

import com.hai.msg.MyMessage;
import com.hai.pojo.Item;
import com.hai.service.ItemService;
import com.hai.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by chenz at 20:05 on 2021/3/6
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserService userService;

    @Autowired
    ItemService itemService;

    @GetMapping("/getUsers")
    public MyMessage getUsers(@RequestParam(required = false) String name, @RequestParam String pageNum, HttpServletRequest request){
        MyMessage myMessage = new MyMessage();
        String username = (String) request.getSession().getAttribute("username");
        if (username!=null){
            int i = userService.queryUserRole(username);
            if (i==2){
                myMessage.setData(userService.getPageHelperByName(username,pageNum));
                return myMessage;
            }
        }
        myMessage.setData(userService.getPageHelper(name,pageNum));
        return myMessage;
    }

    @PostMapping("/order")
    public MyMessage addOrder(Item item,HttpServletRequest request){
        MyMessage myMessage = new MyMessage();
        String username = (String) request.getSession().getAttribute("username");
        if (username!=null){
            int i = userService.queryUserRole(username);
            if (i==2){
                myMessage.setCode(1);
                myMessage.setMsg("对不起，你没有管理员权限");
                return myMessage;
            }
        }
        try {
            itemService.addItem(item);
            myMessage.setMsg("成功");
        }catch (Exception exception){
            myMessage.setCode(1);
            myMessage.setMsg("失败");
            exception.printStackTrace();
        }finally {
            return  myMessage;
        }
    }

    @GetMapping("/order")
    //type 0-消费 1-充值 2-all
    public MyMessage getOrder(@RequestParam(required = false) String name, @RequestParam int type, @RequestParam String pageNum,HttpServletRequest request){
        MyMessage myMessage = new MyMessage();
        String username = (String) request.getSession().getAttribute("username");
        if (username!=null){
            int i = userService.queryUserRole(username);
            if (i==2){
                myMessage.setData(itemService.getPageHelperByName(username,type,pageNum));
                return myMessage;
            }
        }
        myMessage.setData(itemService.getPageHelper(name, type,pageNum));
        return myMessage;
    }


}
