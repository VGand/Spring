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
    UserService userService;

    public void login(String login) {
        if (login != null) {
            User user = userService.getBuLogin(login);
            new Session(user.getRole(), user.getLogin());
        } else {
            new Session(Role.USER, null);
        }
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
