package com.epam.spring.cinema.service.impl;

import com.epam.spring.cinema.config.app.ApplicationContextProvider;
import com.epam.spring.cinema.dao.UserManager;
import com.epam.spring.cinema.domain.User;
import com.epam.spring.cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Andrey Vaganov on 09.05.2016.
 */
@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserManager userManager;

    @Override
    public User add(String firstName, String lastName, String email, String login, LocalDate birthday) {
        User user = ApplicationContextProvider.getApplicationContext().getBean("user", User.class);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setLogin(login);
        user.setBirthday(birthday);
        save(user);

        return user;
    }

    public void save(User user) {
        userManager.save(user);
    }

    public void remove(String id) {
        userManager.remove(id);
    }

    public User getBuLogin(String login) {
        return userManager.getByLogin(login);
    }

    public User getUserByEmail(String email) {
        return userManager.getByEmail(email);
    }

    public List<User> getAll() {
        return userManager.getAll();
    }

    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }
}
