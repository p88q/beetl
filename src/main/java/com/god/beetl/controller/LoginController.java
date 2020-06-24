package com.god.beetl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @GetMapping("/")
    public ModelAndView loginPage(@RequestParam(value = "error", required = false) String error,
                                  @RequestParam(value = "logout", required = false) String logout) {
        ModelAndView mav = new ModelAndView();
        if (error != null) {
            mav.addObject("error", "用户名或密码不正确");
        }
        if (logout != null) {
            mav.addObject("msg", "退出成功!");
        }
        mav.setViewName("login");
        return mav;
    }

    @GetMapping("/login")
    public ModelAndView login(@RequestParam(value = "error", required = false) String error,
                              @RequestParam(value = "logout", required = false) String logout) {
        ModelAndView mav = new ModelAndView();
        if (error != null) {
            mav.addObject("error", "用户名或密码不正确");
        }
        if (logout != null) {
            mav.addObject("msg", "退出成功!");
        }
        mav.setViewName("login");
        return mav;
    }
}
