package com.epam.spring.cinema.console.impl;

import com.epam.spring.cinema.CinemaConstants;
import com.epam.spring.cinema.console.ConsoleMenu;
import com.epam.spring.cinema.service.LoginService;
import com.epam.spring.cinema.service.UserService;
import com.epam.spring.cinema.session.Role;
import com.epam.spring.cinema.session.Session;
import com.epam.spring.cinema.util.CinemaDateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * Created by Andrey_Vaganov on 5/10/2016.
 */
@Component
public class MainConsoleMenuImpl implements ConsoleMenu {

    @Autowired
    private UserService userService;

    @Autowired
    private LoginService loginService;

    @Autowired
    private ConsoleMenu userConsoleMenu;

    @Autowired
    private ConsoleMenu adminConsoleMenu;

    @Override
    public void start(Scanner scanner) {
        Integer command = -1;
        while (command != 0) {
            System.out.println("1-Регистрация");
            System.out.println("2-Авторизация");
            System.out.println("3-Вход без регистрации");
            System.out.println("0-Выход");
            System.out.print("Выберите действие: ");
            command = scanner.nextInt();
            System.out.println();
            switch (command) {
                case 1: {
                    try {
                        String login;
                        String firstName;
                        String lastName;
                        String email;
                        LocalDate birthday;

                        System.out.print("Введите логин: ");
                        login = scanner.next();
                        System.out.print("Введите имя пользователя: ");
                        firstName = scanner.next();
                        System.out.print("Введите фамилию пользователя: ");
                        lastName = scanner.next();
                        System.out.print("Введите email пользователя: ");
                        email = scanner.next();

                        System.out.print("Введите день рождения пользователя (дд.мм.гггг): ");
                        birthday = CinemaDateUtils.getDateByFormat(scanner.next(), CinemaConstants.DATE_FORMAT);

                        userService.add(firstName, lastName, email, login, birthday);
                        System.out.println("Пользователь был успешно добавлен");
                    } catch (Exception e) {
                        System.out.println("Произошла ошибка при регистрации пользователя " + e.getMessage());
                        scanner.next();
                    }
                    break;
                }
                case 2: {
                    try {
                        String login;
                        System.out.print("Введите логин: ");
                        login = scanner.next();
                        boolean result = loginService.login(login);
                        if (result) {
                            System.out.println("Добро пожаловать: " + Session.currentSession().getUserLogin());
                            if (Session.currentSession().getRole() == Role.ADMIN) {
                                adminConsoleMenu.start(scanner);
                            } else {
                                userConsoleMenu.start(scanner);
                            }
                        } else {
                            System.out.println("Пользователь c таким логином не найден");
                        }
                    } catch (Exception e) {
                        System.out.println("Произошла ошибка при входе пользователя " + e.getMessage());
                        scanner.next();
                    }
                    break;
                }
                case 3: {
                    try {
                        loginService.login(null);
                        userConsoleMenu.start(scanner);
                    } catch (Exception e) {
                        System.out.println("Произошла ошибка при входе пользователя без регистрации" + e.getMessage());
                        scanner.next();
                    }
                    break;
                }
            }
        }
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

    public void setUserConsoleMenu(ConsoleMenu userConsoleMenu) {
        this.userConsoleMenu = userConsoleMenu;
    }

    public void setAdminConsoleMenu(ConsoleMenu adminConsoleMenu) {
        this.adminConsoleMenu = adminConsoleMenu;
    }
}
