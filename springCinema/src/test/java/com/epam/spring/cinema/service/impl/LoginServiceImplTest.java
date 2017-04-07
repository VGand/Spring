package com.epam.spring.cinema.service.impl;

import com.epam.spring.cinema.config.CinemaConfigTest;
import com.epam.spring.cinema.domain.User;
import com.epam.spring.cinema.service.LoginService;
import com.epam.spring.cinema.service.UserService;
import com.epam.spring.cinema.session.Role;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Полина on 30.10.2016.
 */
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = CinemaConfigTest.class)
public class LoginServiceImplTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private LoginService loginService = new LoginServiceImpl();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testLogin() throws Exception {
        String login = "login";
        User user = new User(login, Role.USER);

        when(userService.getBuLogin(any(String.class))).thenReturn(user);

        assertTrue(loginService.login(login));
    }

    @Test
    public void testLogin_notLogin() throws Exception {
        String login = "login";
        when(userService.getBuLogin(any(String.class))).thenReturn(null);

        assertFalse(loginService.login(login));
    }
}