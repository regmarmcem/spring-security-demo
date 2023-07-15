package com.regmarmcem.springsecuritydemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class UserController {

    @GetMapping("/user")
    public String userInfo() {
        return "user";
    }

    @GetMapping("/sys_admin")
    public String sysAdminInfo() {
        return "sys_admin";
    }

    @GetMapping("/user_admin")
    public String userAdminInfo() {
        return "user_admin";
    }

}
