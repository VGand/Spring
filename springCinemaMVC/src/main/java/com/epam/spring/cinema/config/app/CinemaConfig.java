package com.epam.spring.cinema.config.app;

import com.epam.spring.cinema.dao.*;
import com.epam.spring.cinema.dao.jdbc.*;
import com.epam.spring.cinema.dao.map.MapDB;
import com.epam.spring.cinema.domain.Auditorium;
import com.epam.spring.cinema.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Andrey_Vaganov on 15.05.2016.
 */
@Configuration
@EnableAspectJAutoProxy
@EnableTransactionManagement
public class CinemaConfig {

    @Autowired
    private Environment env;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        propertySourcesPlaceholderConfigurer.setLocations(new Resource[] {
                new ClassPathResource("WEB-INF\\classes\\cinema.properties")
        });
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
        driverManagerDataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
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

    @Bean(name = "jdbcTransactionManager")
    public PlatformTransactionManager jdbcTransactionManager() {
        PlatformTransactionManager jdbcTransactionManager = new DataSourceTransactionManager(driverManagerDataSource());
        return jdbcTransactionManager;
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
        List<Auditorium> auditoriums = new ArrayList<>();
        auditoriums.add(firstAuditorium());
        auditoriums.add(secondAuditorium());
        return new AuditoriumJdbcManager(auditoriums, jdbcTemplate());
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
    AspectCounterManager aspectCounterManager() {
        return new AspectCounterJdbcManager();
    }

    @Bean
    public UserAccountManager getUserAccountManager() {
        return new UserAccountJdbcManager();
    }

    @Bean
    public User administrator() {
        String login = env.getProperty("adminLogin");
        String role = env.getProperty("adminRole");
        String password = env.getProperty("admPassword");
        User user = new User(login, password, role);
        return user;
    }

    @Bean
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    public User user() {
        String role = env.getProperty("userRole");
        User user = new User(role);
        return user;
    }

    @Bean
    public Jaxb2Marshaller jaxb2Marshaller() {
        Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
        jaxb2Marshaller.setPackagesToScan("com.epam.spring.cinema.domain.jaxb");
        return jaxb2Marshaller;
    }
}
