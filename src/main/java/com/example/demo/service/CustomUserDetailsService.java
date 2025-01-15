package com.example.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Пример статического пользователя (замените на логику работы с базой данных):
        if ("admin".equals(username)) {
            return User.withUsername("admin")
                       .password("{noop}password") // {noop} отключает шифрование для примера
                       .roles("ADMIN")
                       .build();
        }
        throw new UsernameNotFoundException("User not found");
    }
}
