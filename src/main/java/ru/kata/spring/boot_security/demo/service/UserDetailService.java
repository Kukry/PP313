package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.repository.UserFindBy;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.Optional;

@Service
public class UserDetailService implements UserDetailsService {
    private final UserFindBy userFindBy;

    @Autowired
    public UserDetailService(UserFindBy userFindBy) {
        this.userFindBy = userFindBy;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("email = " + email);
        Optional<User> user = userFindBy.findByEmail(email);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("Пользователь не найден");
        }

        return user.get();
    }
}
