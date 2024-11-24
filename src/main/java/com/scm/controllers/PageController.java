package com.scm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class PageController {

    @RequestMapping(path = "/home")
    public String name(Model model){
        model.addAttribute("name","Substring TXhnologies");
        model.addAttribute("title","No 1 Coder");

        return "Home";
    }

}
