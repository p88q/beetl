package com.god.beetl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HelloController {

    @RequestMapping("/")
    public String hello(HttpServletRequest request) {
        request.setAttribute("title", "beetl");
        return "index.html";
    }
}
