package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.model.User;

import javax.validation.Valid;

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
    public String getAdminPage(Model model, Authentication auth) {
        User user = (User) auth.getPrincipal();
        model.addAttribute("loginBy", auth.getName());
        model.addAttribute("roles", AuthorityUtils.authorityListToSet(auth.getAuthorities()));
        model.addAttribute("users", userService.allUsers());
        model.addAttribute("user_log", userService.showUser(user.getId()));
        model.addAttribute("newUser", new User());
        model.addAttribute("roleAdmin", Role.ROLE_ADMIN.name());
        model.addAttribute("roleUser", Role.ROLE_USER.name());
        return "admin";
    }

    @GetMapping("/user")
    public String showUser(@RequestParam(value = "id", defaultValue = "0") int id, Model model, Authentication auth) {
        model.addAttribute("loginBy", auth.getName());
        model.addAttribute("roles", AuthorityUtils.authorityListToSet(auth.getAuthorities()));
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
        model.addAttribute("roleAdmin", Role.ROLE_ADMIN.name());
        model.addAttribute("roleUser", Role.ROLE_USER.name());
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