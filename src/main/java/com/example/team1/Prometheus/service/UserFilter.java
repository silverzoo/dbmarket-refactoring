package com.example.team1.Prometheus.service;

import com.example.team1.Prometheus.entity.User;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;


import java.io.IOException;

@RequiredArgsConstructor
@Component
public class UserFilter implements Filter{

    User user;
    // 서블릿 컨테이너가 생성될 때 호출
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    // 요청이 올 때 마다 해당 메서드가 호출
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        user = (User) ((HttpServletRequest) request).getSession().getAttribute("user");
        filterChain.doFilter(request, response);

    }

    public void findUserByFilter(Model model){
        model.addAttribute("myusername", user.getUserName());
        model.addAttribute("myuserid", user.getUserId());
        user = null;
    }


    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    public String getHeader(HttpServletRequest request) {
        return request.getHeader("referer");
    }
}