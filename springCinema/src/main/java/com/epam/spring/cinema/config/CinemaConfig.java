package com.epam.spring.cinema.config;

import com.epam.spring.cinema.console.ConsoleMenu;
import com.epam.spring.cinema.console.impl.AdminConsoleMenu;
import com.epam.spring.cinema.console.impl.MainConsoleMenuImpl;
import com.epam.spring.cinema.console.impl.UserConsoleMenu;
import com.epam.spring.cinema.dao.AuditoriumManager;
import com.epam.spring.cinema.dao.EventManager;
import com.epam.spring.cinema.dao.TicketManager;
import com.epam.spring.cinema.dao.UserManager;
import com.epam.spring.cinema.dao.jdbc.AuditoriumJdbcManager;
import com.epam.spring.cinema.dao.jdbc.EventJdbcManager;
import com.epam.spring.cinema.dao.jdbc.TicketJdbcManager;
import com.epam.spring.cinema.dao.jdbc.UserJdbcManager;
import com.epam.spring.cinema.dao.map.MapDB;
import com.epam.spring.cinema.domain.Auditorium;
import com.epam.spring.cinema.domain.User;
import com.epam.spring.cinema.session.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Andrey_Vaganov on 15.05.2016.
 */
@Configuration
@PropertySource({"classpath:cinema.properties", "classpath:db.properties"})
@ComponentScan("com.epam.spring.cinema")
@EnableAspectJAutoProxy
public class CinemaConfig {

    @Autowired
    private Environment env;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public MapDB mapDB() {
        return new MapDB();
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(driverManagerDataSource());
    }

    @Bean
    public DataSource driverManagerDataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setUrl(env.getProperty("jdbc.url"));
        driverManagerDataSource.setUsername(env.getProperty("jdbc.username"));
        driverManagerDataSource.setPassword(env.getProperty("jdbc.password"));
        return driverManagerDataSource;
    }

    @Bean
    public Auditorium firstAuditorium() {
        Auditorium auditorium = new Auditorium();

        auditorium.setName(env.getProperty("auditoium1.name"));
        auditorium.setNumberOfSeats(Long.parseLong(env.getProperty("auditoium1.numberOfSeats")));

        Set<Long> vipSeats = new HashSet<Long>();
        String vipSeatsStr = env.getProperty("auditoium1.vipSeats");
        if (vipSeatsStr != null) {
            List<Long> longList = Arrays.asList(vipSeatsStr.split(",")).stream().map(Long::parseLong).collect(Collectors.toList());
            vipSeats.addAll(longList);
        }
        auditorium.setVipSeats(vipSeats);
        return auditorium;
    }

    @Bean
    public Auditorium secondAuditorium() {
        Auditorium auditorium = new Auditorium();

        auditorium.setName(env.getProperty("auditoium2.name"));
        auditorium.setNumberOfSeats(Long.parseLong(env.getProperty("auditoium2.numberOfSeats")));

        Set<Long> vipSeats = new HashSet<Long>();
        String vipSeatsStr = env.getProperty("auditoium2.vipSeats");
        if (vipSeatsStr != null) {
            List<Long> longList = Arrays.asList(vipSeatsStr.split(",")).stream().map(Long::parseLong).collect(Collectors.toList());
            vipSeats.addAll(longList);
        }
        auditorium.setVipSeats(vipSeats);
        return auditorium;
    }

    @Bean
    public AuditoriumManager auditoriumManager() {
        return new AuditoriumJdbcManager();
    }

    @Bean
    public EventManager eventManager() {
        return new EventJdbcManager();
    }

    @Bean
    public TicketManager ticketManager() {
        return new TicketJdbcManager();
    }

    @Bean
    public UserManager userManager() {
        return new UserJdbcManager();
    }

    @Bean
    public User administrator() {
        String login = env.getProperty("adminLogin");
        Role role = Role.getRoleBySysName(env.getProperty("adminRole"));
        User user = new User(login, role);
        return user;
    }

    @Bean
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    public User user() {
        Role role = Role.getRoleBySysName(env.getProperty("userRole"));
        User user = new User(role);
        return user;
    }

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
