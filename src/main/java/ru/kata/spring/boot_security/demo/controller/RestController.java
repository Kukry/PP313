package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<User> showAllUsers() {
        List<User> allUsers = userService.allUsers();
        return allUsers;
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable int id){
        User user = userService.showUser(id);
        return user;
    }

    @PostMapping("/users")
    public User addNewUser(@RequestBody User user) {
        userService.saveUser(user);
        return user;
    }

    @PutMapping("/users")
    public User updateUser(@RequestBody User user) {
        userService.updateUser(user);
        return user;
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return "Пользователь с id=" + id + " успешно удалён";
    }

    @GetMapping("/auth")
    public User getAuthUser(@AuthenticationPrincipal User user) {
        return user;
    }
}
