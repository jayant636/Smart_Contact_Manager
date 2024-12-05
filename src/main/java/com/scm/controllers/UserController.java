package com.scm.controllers;

import com.scm.entity.UserEntity;
import com.scm.helper.Helper;
import com.scm.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    private UserService userService;



    @GetMapping(path = "/dashboard")
    public String userDashboard(){
        return "user/dashboard";
    }

    @GetMapping(path = "/profile")
    public String userProfile(Model model , Authentication authentication){

        return "user/profile";
    }
}
