package com.scm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class PageController {

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
    public String signup(){
        return "Register";
    }

}
