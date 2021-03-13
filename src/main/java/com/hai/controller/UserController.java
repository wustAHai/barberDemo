package com.hai.controller;

import com.geetest.sdk.entity.GeetestLibResult;
import com.geetest.sdk.enums.DigestmodEnum;
import com.geetest.sdk.utils.PropertiesUtils;
import com.geetest.sdk.GeetestLib;
import com.hai.msg.MyMessage;
import com.hai.pojo.User;
import com.hai.service.UserService;
import com.hai.util.CheckStrings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenz at 22:43 on 2021/2/26
 */
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/register")
    public String firstRegister(){
        GeetestLib gtLib = new GeetestLib(PropertiesUtils.get("geetest.id"), PropertiesUtils.get("geetest.key"));
        String userId = "test";
        GeetestLibResult result = null;
        result = gtLib.localInit();
        return result.getData();
    }


    @PostMapping("/register")
    public MyMessage secondRegister(HttpServletRequest request){
        GeetestLib gtLib = new GeetestLib(PropertiesUtils.get("geetest.id"), PropertiesUtils.get("geetest.key"));
        String challenge = request.getParameter(GeetestLib.GEETEST_CHALLENGE);
        String validate = request.getParameter(GeetestLib.GEETEST_VALIDATE);
        String seccode = request.getParameter(GeetestLib.GEETEST_SECCODE);
        GeetestLibResult result = null;

        result = gtLib.failValidate(challenge, validate, seccode);
        MyMessage myMessage = new MyMessage();
        // 注意，不要更改返回的结构和值类型
        if (result.getStatus() == 1) {
            //验证码通过，验证本地用户名 密码
            String username = request.getParameter("username");
            String pwd = request.getParameter("pwd");
            HttpSession session = request.getSession();
            if (CheckStrings.isValid(username, pwd)) {
                User user = new User();
                user.setName(username);
                user.setPassword(pwd);
                if (userService.loginOrRegister(user)) {
                    session.setAttribute("username", username);
                    myMessage.setCode(0);
                    myMessage.setMsg("登陆成功");
                    myMessage.setData(username);
                    System.out.println(myMessage);
                } else {
                    myMessage.setCode(1);
                    myMessage.setMsg("账号或密码错误");
                }
            } else {
                myMessage.setCode(2);
                myMessage.setMsg("输入不合法");
            }
        } else {
            myMessage.setCode(3);
            myMessage.setMsg(result.getMsg());
            myMessage.setData(GeetestLib.VERSION);
        }
        return myMessage;
    }

    @GetMapping("/loginCheck")
    public MyMessage loginCheck(HttpServletRequest request){
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        MyMessage myMessage = new MyMessage();
        if (username==null){
            myMessage.setCode(1);
            myMessage.setMsg("用户未登录");
        }else {
            myMessage.setMsg("用户已登录");
            myMessage.setData(username);
        }
        return myMessage;
    }

    @GetMapping("/u/{username}")
    public void userAdmin(@PathVariable("username") String username,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/html/admin.html").forward(request,response);
    }

    @GetMapping("/i/{username}")
    public void itemAdmin(@PathVariable("username") String username,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/html/item.html").forward(request,response);
    }

    @GetMapping("/role")
    public MyMessage getRole(HttpServletRequest request) {
        MyMessage myMessage = new MyMessage();
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        if (username==null){
            myMessage.setCode(1);
        }else {
            int i = userService.queryUserRole(username);
            String[] info = {username,""+i};
            myMessage.setData(info);
        }
        return myMessage;
    }
}
