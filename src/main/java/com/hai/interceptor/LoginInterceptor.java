package com.hai.interceptor;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by chenz at 13:51 on 2021/3/13
 */
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info(request.getRequestURI());
        HttpSession session = request.getSession();
        Object username = session.getAttribute("username");
        if (username!=null){
            return true;
        }
        response.sendRedirect("/");
        return false;
    }
}
