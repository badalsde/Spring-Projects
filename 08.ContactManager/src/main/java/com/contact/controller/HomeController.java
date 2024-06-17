package com.contact.controller;

import com.contact.entity.User;
import com.contact.service.UserService;
import com.contact.utility.Message;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
public class HomeController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Home-Contact Manager");
        return "home";
    }

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("title", "About-Contact Manager");
        return "about";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("title", "Login-Contact Manager");
        return "login";
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("title", "Signup-Contact Manager");
        model.addAttribute("user", new User());

        return "signup";
    }

    @PostMapping("/do_register")
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, Model model, @RequestParam(value = "agreement", defaultValue = "false") boolean agreement, HttpSession session) {
        if(result.hasErrors()) {
            model.addAttribute("user", user);
            return "signup";
        }

        if(!agreement) {
            model.addAttribute("user", user);
            session.setAttribute("message", new Message("You must agree to the terms and conditions", "alert-danger"));
            return "signup";
        }

        userService.save(user, model, agreement, session);
        return "signup";
    }

}