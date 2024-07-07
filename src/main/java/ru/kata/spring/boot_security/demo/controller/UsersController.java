package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.model.User;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/")
public class UsersController {
    @Autowired
    private UserService userService;

    @GetMapping
    public String getStartPage() {
        return "start";
    }
    @GetMapping("/admin")
    public String getAdminPage(Model model, Principal principal) {
        model.addAttribute("users", userService.allUsers());
        return "admin";
    }

    @GetMapping("/user")
    public String showUser(@RequestParam(value = "id", defaultValue = "0") int id, Model model) {
        model.addAttribute("user", userService.showUser(id));
        return "user";
    }

    @GetMapping("/new")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "new";
    }

    @PostMapping()
    public String createUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "new";
        }
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/edit")
    public String editUser(@RequestParam(value = "id", defaultValue = "0") int id, Model model) {
        model.addAttribute("user", userService.showUser(id));
        return "/edit";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit";
        }
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam(value = "id", defaultValue = "0") int id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}