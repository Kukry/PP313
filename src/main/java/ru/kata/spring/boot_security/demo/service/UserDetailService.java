package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.repository.UserFindByName;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.security.UserDetail;

import java.util.Optional;

@Service
public class UserDetailService implements UserDetailsService {
    private final UserFindByName userFindByName;

    @Autowired
    public UserDetailService(UserFindByName userFindByName) {
        this.userFindByName = userFindByName;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userFindByName.findByName(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("Пользователь не найден");
        }
        return new UserDetail(user.get());
    }

//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        Optional<User> user = userFindByName.findByName(email);
//        if (user.isEmpty()) {
//            throw new UsernameNotFoundException("Пользователь не найден");
//        }
//        return new UserDetail(user.get());
//    }
}
