package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.UserFindByName;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceUmpl implements UserService {
    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;
    private final UserFindByName userFindByName;

    @Autowired
    public UserServiceUmpl(UserDao userDao, PasswordEncoder passwordEncoder, UserFindByName userFindByName) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.userFindByName = userFindByName;
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> allUsers() {
        return userDao.allUsers();
    }

    @Transactional(readOnly = true)
    @Override
    public User showUser(int id) {
        return userDao.showUser(id);
    }

    @Transactional
    @Override
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.saveUser(user);
    }

    @Transactional
    @Override
    public void updateUser(User editedUser) {
        editedUser.setPassword(passwordEncoder.encode(editedUser.getPassword()));
        userDao.updateUser(editedUser);
    }

    @Transactional
    @Override
    public void deleteUser(int id) {
        userDao.deleteUser(id);
    }


    @Transactional
    public Optional<User> findByUsername(String name) {
        Optional<User> user = userFindByName.findByName(name);
        return user;
    }
}
