package com.epam.spring.cinema.dao;

import com.epam.spring.cinema.domain.User;

import java.util.List;

/**
 * Created by Andrey Vaganov on 09.05.2016.
 */
public interface UserManager {

    void save(User user);

    void remove(String login);

    User getByLogin(String login);

    User getByEmail(String email);

    List<User> getAll();
}
