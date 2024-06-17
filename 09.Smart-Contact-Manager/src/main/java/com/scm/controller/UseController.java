package com.scm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UseController {

    @GetMapping("home")
    public String home(){
        return "home";
    }

    @GetMapping("about")
    public String about(){
        return "about";
    }

    @GetMapping("service")
    public String service(){
        return "service";
    }

    @GetMapping("contact")
    public String contact(){
        return "contact";
    }
}
