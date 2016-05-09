package com.epam.spring.cinema;

import com.epam.spring.cinema.dao.UserManager;
import com.epam.spring.cinema.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.shell.Bootstrap;

import java.io.IOException;

/**
 * Created by Andrey Vaganov on 09.05.2016.
 */
public class MainApp {

    public static void main(String[] args) throws IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        addAdministrator(context);
        Bootstrap.main(args);
    }

    private static void addAdministrator(ApplicationContext context) {
        UserManager userManager = context.getBean("userManager", UserManager.class);
        User administrator = context.getBean("administrator", User.class);
        userManager.save(administrator);
    }
}
