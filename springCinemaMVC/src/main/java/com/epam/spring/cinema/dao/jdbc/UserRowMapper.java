package com.epam.spring.cinema.dao.jdbc;

import com.epam.spring.cinema.dao.TicketManager;
import com.epam.spring.cinema.domain.Ticket;
import com.epam.spring.cinema.domain.User;
import com.epam.spring.cinema.session.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

/**
 * Created by Andrey_Vaganov on 5/24/2016.
 */
public class UserRowMapper implements RowMapper<User> {

    private TicketManager ticketManager;

    public UserRowMapper(TicketManager ticketManager) {
        this.ticketManager = ticketManager;
    }

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

        List<Ticket> ticketList = ticketManager.getTicketByUserLogin(login);
        for(Ticket ticket: ticketList) {
            ticket.setUser(user);
        }
        user.setPurchasedTickets(ticketList);

        return user;
    }
}
