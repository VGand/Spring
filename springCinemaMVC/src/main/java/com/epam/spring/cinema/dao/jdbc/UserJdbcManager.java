package com.epam.spring.cinema.dao.jdbc;

import com.epam.spring.cinema.dao.TicketManager;
import com.epam.spring.cinema.dao.UserManager;
import com.epam.spring.cinema.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

/**
 * Created by Andrey_Vaganov on 5/24/2016.
 */
@Resource
public class UserJdbcManager implements UserManager {

    private final String SAVE_QUERY = "INSERT INTO CINEMA_USER (LOGIN, FIRST_NAME, LAST_NAME, EMAIL, ROLE_SYS_NAME, BIRTHDAY) VALUES (?, ?, ?, ?, ?, ?)";
    private final String REMOVE_QUERY = "DELETE FROM CINEMA_USER WHERE LOGIN = ?";
    private final String SELECT_USER_BY_LOGIN = "SELECT LOGIN as login, FIRST_NAME as firstName, LAST_NAME as lastName, EMAIL as email, ROLE_SYS_NAME as roleSysName, BIRTHDAY as birthday FROM CINEMA_USER WHERE LOGIN = ?";
    private final String SELECT_USER_BY_EMAIL = "SELECT LOGIN as login, FIRST_NAME as firstName, LAST_NAME as lastName, EMAIL as email, ROLE_SYS_NAME as roleSysName, BIRTHDAY as birthday FROM CINEMA_USER WHERE EMAIL = ?";
    private final String SELECT_ALL_USER = "SELECT LOGIN as login, FIRST_NAME as firstName, LAST_NAME as lastName, EMAIL as email, ROLE_SYS_NAME as roleSysName, BIRTHDAY as birthday FROM CINEMA_USER";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    TicketManager ticketManager;

    @Override
    public void save(User user) {
        Date birthday = Date.from(user.getBirthday().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        jdbcTemplate.update(SAVE_QUERY, user.getLogin(), user.getFirstName(), user.getLastName(), user.getEmail(),
                user.getRole().getRoleSysName(), new java.sql.Date(birthday.getTime()));
    }

    @Override
    public void remove(String login) {
        jdbcTemplate.update(REMOVE_QUERY, login);
    }

    @Override
    public User getByLogin(String login) {
        return jdbcTemplate.queryForObject(SELECT_USER_BY_LOGIN, new Object[]{login}, new UserRowMapper(ticketManager));
    }

    @Override
    public User getByEmail(String email) {
        return jdbcTemplate.queryForObject(SELECT_USER_BY_EMAIL, new Object[]{email}, new UserRowMapper(ticketManager));
    }

    @Override
    public List<User> getAll() {
        return jdbcTemplate.query(SELECT_ALL_USER, new UserRowMapper(ticketManager));
    }
}
