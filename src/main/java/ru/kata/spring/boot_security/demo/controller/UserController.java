package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserServiceUmpl;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserServiceUmpl userServiceUmpl;

    @Autowired
    public UserController(UserServiceUmpl userServiceUmpl, UserServiceUmpl userService) {
        this.userServiceUmpl = userServiceUmpl;
    }

    @GetMapping()
    public String showUserByName(Model model, Principal principal) {
        UserDetails userDetails = (UserDetails) ((Authentication) principal).getPrincipal();
        Optional<User> user = userServiceUmpl.findByUsername(userDetails.getUsername());
        model.addAttribute("user", user.orElse(null));
        return "user/user";
    }
}
