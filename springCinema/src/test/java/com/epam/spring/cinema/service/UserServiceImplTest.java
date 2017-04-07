package com.epam.spring.cinema.service;


import com.epam.spring.cinema.config.CinemaConfigTest;
import com.epam.spring.cinema.domain.User;
import com.epam.spring.cinema.service.UserService;
import com.epam.spring.cinema.session.Role;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

/**
 * Created by Полина on 29.10.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CinemaConfigTest.class)
public class UserServiceImplTest {

    @Autowired
    UserService userService;

    @Mock
    User savedUser;

    @Test
    public void testAdd_equals() throws Exception {
        String firstName = "firstName";
        String lastName = "lastName";
        String email = "email";
        String login = "login";
        LocalDate birthday = LocalDate.now();

        User user = userService.add(firstName, lastName, email, login, birthday);

        assertEquals(firstName, user.getFirstName());
        assertEquals(lastName, user.getLastName());
        assertEquals(email, user.getEmail());
        assertEquals(login, user.getLogin());
        assertEquals(birthday, user.getBirthday());
    }

    @Test
    public void testAdd_userRole() {
        String firstName = "firstName";
        String lastName = "lastName";
        String email = "email";
        String login = "login";
        LocalDate birthday = LocalDate.now();

        User user = userService.add(firstName, lastName, email, login, birthday);

        assertEquals(Role.USER, user.getRole());
    }
}