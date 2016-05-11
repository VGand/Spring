package com.epam.spring.cinema.console.impl;

import com.epam.spring.cinema.CinemaConstants;
import com.epam.spring.cinema.console.ConsoleMenu;
import com.epam.spring.cinema.service.LoginService;
import com.epam.spring.cinema.service.UserService;
import com.epam.spring.cinema.session.Role;
import com.epam.spring.cinema.session.Session;
import com.epam.spring.cinema.util.CinemaDateUtils;

import java.time.LocalDateTime;
import java.util.Scanner;

/**
 * Created by Andrey_Vaganov on 5/10/2016.
 */
public class MainConsoleMenuImpl implements ConsoleMenu {

    private UserService userService;

    private LoginService loginService;

    private ConsoleMenu userConsoleMenu;

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
                    String login;
                    String firstName;
                    String lastName;
                    String email;
                    LocalDateTime birthday;

                    System.out.print("Введите логин: ");
                    login = scanner.next();
                    System.out.print("Введите имя пользователя: ");
                    firstName = scanner.next();
                    System.out.print("Введите фамилию пользователя: ");
                    lastName = scanner.next();
                    System.out.print("Введите email пользователя: ");

                    System.out.print("Введите день рождения пользователя (дд.мм.гггг-чч:мм): ");
                    birthday = CinemaDateUtils.getDateTimeByFormat(scanner.next(), CinemaConstants.DATE_FORMAT);

                    email = scanner.next();
                    userService.add(firstName, lastName, email, login, birthday);
                    System.out.println("Пользователь был успешно добавлен");
                    break;
                }
                case 2: {
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
                    break;
                }
                case 3: {
                    loginService.login(null);
                    userConsoleMenu.start(scanner);
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
