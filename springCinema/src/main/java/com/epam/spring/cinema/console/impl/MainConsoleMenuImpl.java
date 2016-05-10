package com.epam.spring.cinema.console.impl;

import com.epam.spring.cinema.console.ConsoleMenu;
import com.epam.spring.cinema.service.LoginService;
import com.epam.spring.cinema.service.UserService;
import com.epam.spring.cinema.session.Role;
import com.epam.spring.cinema.session.Session;

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
    public void start() {
        Integer command = -1;
        Scanner scanner = new Scanner(System.in);
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

                    System.out.print("Введите логин: ");
                    login = scanner.next();
                    System.out.print("Введите имя пользователя: ");
                    firstName = scanner.next();
                    System.out.print("Введите фамилию пользователя: ");
                    lastName = scanner.next();
                    System.out.print("Введите email пользователя: ");

                    email = scanner.next();
                    userService.add(firstName, lastName, email, login);
                    System.out.println("Пользователь был успешно добавлен");
                    break;
                }
                case 2: {
                    String login;
                    System.out.print("Введите логин: ");
                    login = scanner.next();
                    loginService.login(login);
                    if (Session.currentSession().getRole() == Role.ADMIN) {
                        adminConsoleMenu.start();
                    } else {
                        userConsoleMenu.start();
                    }
                    break;
                }
                case 3: {
                    loginService.login(null);
                    userConsoleMenu.start();
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
