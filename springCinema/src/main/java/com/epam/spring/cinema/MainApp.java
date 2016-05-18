package com.epam.spring.cinema;

import com.epam.spring.cinema.config.CinemaConfig;
import com.epam.spring.cinema.config.DiscountConfig;
import com.epam.spring.cinema.console.ConsoleMenu;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Andrey Vaganov on 09.05.2016.
 */
public class MainApp {

    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                CinemaConfig.class, DiscountConfig.class);
        ConsoleMenu consoleMenu = context.getBean("mainConsoleMenu", ConsoleMenu.class);

        Scanner scanner = new Scanner(System.in);
        consoleMenu.start(scanner);
        scanner.close();
    }
}
