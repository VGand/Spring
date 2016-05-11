package com.epam.spring.cinema.service;

import com.epam.spring.cinema.domain.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Andrey Vaganov on 09.05.2016.
 */
public interface UserService {

    void add(String firstName, String lastName, String email, String login, LocalDate birthday);

    void save(User user);

    void remove(String login);

    User getBuLogin(String Login);

    User getUserByEmail(String email);

    List<User> getAll();
}
