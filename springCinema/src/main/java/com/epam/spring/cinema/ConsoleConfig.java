package com.epam.spring.cinema;

import com.epam.spring.cinema.console.ConsoleMenu;
import com.epam.spring.cinema.console.impl.AdminConsoleMenu;
import com.epam.spring.cinema.console.impl.MainConsoleMenuImpl;
import com.epam.spring.cinema.console.impl.UserConsoleMenu;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Andrey_Vaganov on 15.05.2016.
 */
@Configuration
@ComponentScan("com.epam.spring.cinema")
public class ConsoleConfig {

    @Bean
    public ConsoleMenu mainConsoleMenu() {
        return new MainConsoleMenuImpl();
    }

    @Bean
    public ConsoleMenu userConsoleMenu() {
        return new UserConsoleMenu();
    }

    @Bean
    public ConsoleMenu adminConsoleMenu() {
        return new AdminConsoleMenu();
    }
}
