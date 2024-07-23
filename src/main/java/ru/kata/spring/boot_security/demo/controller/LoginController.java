package ru.kata.spring.boot_security.demo.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.model.User;

@Controller
public class LoginController {

    @GetMapping("/")
    public String loginFromStartPage(){
        return "login";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
