package com.rts.services.controller;


import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.rts.services.dao.UserDAO;
import com.rts.services.model.User;
import com.rts.services.security.UserPrincipal;

@Component
public class UserAuthDetailsService implements UserDetailsService {

    private final UserDAO userRepository;

    public UserAuthDetailsService(UserDAO userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserPrincipal loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository
                .findByEmailid(s)
                .orElseThrow(() -> new UsernameNotFoundException("User name " + s + "Not Found in DB"));
        return UserPrincipal.create(user);

    }
}
