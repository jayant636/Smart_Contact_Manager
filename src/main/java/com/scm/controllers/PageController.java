package com.scm.controllers;

import com.scm.entity.UserEntity;
import com.scm.forms.UserForm;
import com.scm.helper.Message;
import com.scm.helper.MessageType;
import com.scm.services.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class PageController {
    private final UserService userService;

    @RequestMapping(path = "/home")
    public String name(Model model){
        model.addAttribute("name","Substring TXhnologies");
        model.addAttribute("title","No 1 Coder");

        return "Home";
    }

    @RequestMapping(path = "/about")
    public String aboutRoute(Model model){
        model.addAttribute("name","Substring TXhnologies");
        model.addAttribute("title","No 1 Coder");

        return "About";
    }

    @RequestMapping(path = "/service")
    public String serviceRoute(Model model){
        model.addAttribute("name","Substring TXhnologies");
        model.addAttribute("title","No 1 Coder");

        return "Services";
    }

    @GetMapping(path = "/contact")
    public String contact(){
        return "Contact";
    }

    @GetMapping(path = "/login")
    public String login(){
        return "Login";
    }

    @GetMapping(path = "/register")
    public String signup(Model model){
        UserForm userForm = new UserForm();
        userForm.setName("Jayant");
        model.addAttribute("userForm",userForm);
        return "Register";
    }

    @RequestMapping(value = "/do-register",method = RequestMethod.POST)
    public String processRegister(@Valid @ModelAttribute UserForm userForm, BindingResult bindingResult , HttpSession session){
        System.out.println("Do Register");
        System.out.println(userForm);

        if(bindingResult.hasErrors()){
            return "register";
        }

//        UserEntity userEntity = UserEntity.builder()
//                .name(userForm.getName())
//                .email(userForm.getEmail())
//                .password(userForm.getPassword())
//                .about(userForm.getAbout())
//                .phoneNumber(userForm.getPhoneNumber())
//                .profilePic("https://shorturl.at/bBJ70")
//                .build();
        UserEntity userEntity = new UserEntity();
        userEntity.setName(userForm.getName());
        userEntity.setEmail(userForm.getEmail());
        userEntity.setPassword(userForm.getPassword());
        userEntity.setAbout(userForm.getAbout());
        userEntity.setPhoneNumber(userForm.getPhoneNumber());
        userEntity.setProfilePic("https://shorturl.at/bBJ70");
        UserEntity savedUser =  userService.saveUser(userEntity);
        Message message =  Message.builder().content("Registration Successful").type(MessageType.green).build();
        session.setAttribute("message",message);
        //todo validate the form
        return "redirect:/register";
    }

}
