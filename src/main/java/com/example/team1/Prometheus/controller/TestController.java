package com.example.team1.Prometheus.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class TestController {
    @GetMapping("/")
    public String thymeleafExample(Model model) {
        return "index";
    }
}
