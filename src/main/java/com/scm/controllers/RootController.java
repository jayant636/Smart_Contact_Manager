package com.scm.controllers;

import com.scm.entity.UserEntity;
import com.scm.helper.Helper;
import com.scm.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class RootController {

    @Autowired
    private UserService userService;

    @ModelAttribute
    public void addLoggedInUserInformation(Model model , Authentication authentication){
       if(authentication == null){
           return;
       }

        String username =  Helper.getEmailOfLoggedInUser( authentication);
        UserEntity userEntity = userService.getUserByEmail(username);

        if(userEntity == null){
            model.addAttribute("loggedInUser",null);
        }else{
            model.addAttribute("loggedInUser",userEntity);

        }
    }

}
