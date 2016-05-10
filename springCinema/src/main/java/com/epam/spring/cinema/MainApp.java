package com.epam.spring.cinema;

import com.epam.spring.cinema.console.ConsoleMenu;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Created by Andrey Vaganov on 09.05.2016.
 */
public class MainApp {

    public static void main(String[] args) throws IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        ConsoleMenu consoleMenu = context.getBean("mainConsoleMenu", ConsoleMenu.class);
        consoleMenu.start();
        //Bootstrap.main(args);
    }
}
