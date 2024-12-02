package com.scm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/user")
public class UserController {

    @GetMapping(path = "/dashboard")
    public String userDashboard(){
        return "user/dashboard";
    }

    @GetMapping(path = "/profile")
    public String userProfile(){
        return "user/profile";
    }
}
