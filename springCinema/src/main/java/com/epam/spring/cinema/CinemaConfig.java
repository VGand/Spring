package com.epam.spring.cinema;

import com.epam.spring.cinema.dao.AuditoriumManager;
import com.epam.spring.cinema.dao.map.AuditoriumMapManager;
import com.epam.spring.cinema.domain.Auditorium;
import com.epam.spring.cinema.domain.Event;
import com.epam.spring.cinema.domain.Ticket;
import com.epam.spring.cinema.domain.User;
import com.epam.spring.cinema.session.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Andrey_Vaganov on 15.05.2016.
 */
@Configuration
@PropertySource("classpath:cinema.properties")
@ComponentScan("com.epam.spring.cinema")
public class CinemaConfig {

    @Autowired
    private Environment env;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
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
        List<Auditorium> list = new ArrayList<>();
        list.add(firstAuditorium());
        list.add(secondAuditorium());
        AuditoriumManager auditoriumManager = new AuditoriumMapManager(list);

        return auditoriumManager;
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
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    public Event event(){
        return new Event();
    }

    @Bean
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    public Ticket ticket(){
        return new Ticket();
    }
}
