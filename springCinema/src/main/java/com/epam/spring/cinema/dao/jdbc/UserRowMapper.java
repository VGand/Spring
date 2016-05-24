package com.epam.spring.cinema.dao.jdbc;

import com.epam.spring.cinema.domain.User;
import com.epam.spring.cinema.session.Role;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * Created by Andrey_Vaganov on 5/24/2016.
 */
public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        String login = resultSet.getString("login");
        String roleSysName = resultSet.getString("roleSysName");

        User user = new User(login, Role.getRoleBySysName(roleSysName));
        user.setFirstName(resultSet.getString("firstName"));
        user.setLastName(resultSet.getString("lastName"));
        user.setEmail(resultSet.getString("email"));

        if (resultSet.getDate("birthday") != null) {
            Date birthday = resultSet.getDate("birthday");
            Instant instant = Instant.ofEpochMilli(birthday.getTime());
            user.setBirthday(LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate());
        }

        return user;
    }
}
