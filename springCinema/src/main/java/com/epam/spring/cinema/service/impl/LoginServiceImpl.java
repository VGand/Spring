package com.epam.spring.cinema.service.impl;

import com.epam.spring.cinema.domain.User;
import com.epam.spring.cinema.service.LoginService;
import com.epam.spring.cinema.service.UserService;
import com.epam.spring.cinema.session.Role;
import com.epam.spring.cinema.session.Session;
import org.springframework.stereotype.Component;

/**
 * Created by Andrey Vaganov on 09.05.2016.
 */
@Component
public class LoginServiceImpl implements LoginService {
    private UserService userService;

    public boolean login(String login) {
        boolean result = false;
        if (login != null) {
            User user = userService.getBuLogin(login);
            if (user != null) {
                new Session(user.getRole(), user.getLogin());
                result = true;
            }
        } else {
            new Session(Role.USER, null);
        }
        return result;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
