package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.model.User;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;

    @GetMapping
    public String getAdminPage(Model model, Principal principal) {
        model.addAttribute("users", userService.allUsers());
        return "/admin/admin";
    }

    @GetMapping("/new")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "/admin/new";
    }

    @PostMapping()
    public String createUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/admin/new";
        }
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/edit")
    public String editUser(@RequestParam(value = "id", defaultValue = "0") int id, Model model) {
        model.addAttribute("user", userService.showUser(id));
        return "/admin/edit";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/admin/edit";
        }
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam(value = "id", defaultValue = "0") int id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @GetMapping("/userInfo")
    public String showUser(@RequestParam(value = "id", defaultValue = "0") int id, Model model) {
        model.addAttribute("user", userService.showUser(id));
        return "/user/user";
    }
}