package com.example.team1.Prometheus.service;

import com.example.team1.Prometheus.entity.User;
import groovy.lang.Singleton;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.util.PatternMatchUtils;


import java.io.IOException;

import static org.springframework.util.ObjectUtils.isEmpty;

@RequiredArgsConstructor
@Slf4j
@Component
public class UserFilter implements Filter {

    private final HttpSession httpSession;
    User user;
    static boolean sessionPersistToken = false;
    private static final String[] whiteList = {"/","/home", "/users/join","/users/login"};

    // 서블릿 컨테이너가 생성될 때 호출
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    // 요청이 올 때 마다 해당 메서드가 호출
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();
        HttpServletResponse httpResponse = (HttpServletResponse) response;
       try{
           if(isLoginCheckPath(requestURI)){
               if(httpSession.getAttribute("user") == null){
                   httpResponse.sendRedirect("/home");
                   return;
               }
           }
           filterChain.doFilter(request, response);
       }
       catch (Exception e){throw e;}
    }

    public User findUserByFilter(Model model, HttpServletRequest request) {
        user = (User) request.getSession().getAttribute("user");
            model.addAttribute("myusername", user.getUserName());
            model.addAttribute("myuserid", user.getUserId());
        return user;
    }

    private boolean isLoginCheckPath(String requestURL){
        return !PatternMatchUtils.simpleMatch(whiteList,requestURL);
    }


    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    public String getHeader(HttpServletRequest request) {
        return request.getHeader("referer");
    }
}
