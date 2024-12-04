package com.scm.controllers;

import com.scm.helper.Helper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping(path = "/user")
public class UserController {

    @GetMapping(path = "/dashboard")
    public String userDashboard(){
        return "user/dashboard";
    }

    @GetMapping(path = "/profile")
    public String userProfile(Authentication authentication){
        String username =  Helper.getEmailOfLoggedInUser( authentication);
        return "user/profile";
    }
}
