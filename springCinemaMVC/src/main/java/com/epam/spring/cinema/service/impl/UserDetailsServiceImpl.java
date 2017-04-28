package com.epam.spring.cinema.service.impl;

import com.epam.spring.cinema.dao.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrey_Vaganov on 4/18/2017.
 */

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserManager userManager;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        com.epam.spring.cinema.domain.User cinemaUser = userManager.getByLogin(s);

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        if (cinemaUser.getRole() != null) {
            for (String role : cinemaUser.getRole().split(",")) {
                grantedAuthorities.add(new SimpleGrantedAuthority(role));
            }
        }
        UserDetails user = new User(s, cinemaUser.getPassword(), grantedAuthorities);

        return user;
    }
}
