package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.User;
import java.util.List;

public interface UserService {
    List<User> allUsers();
    User showUser(int id);
    void saveUser(User user);
    void updateUser(User editedUser);
    void deleteUser(int id);
}
