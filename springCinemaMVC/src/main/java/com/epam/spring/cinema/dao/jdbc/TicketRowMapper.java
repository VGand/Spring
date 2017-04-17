package com.epam.spring.cinema.dao.jdbc;

import com.epam.spring.cinema.domain.Ticket;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Andrey_Vaganov on 28.05.2016.
 */
public class TicketRowMapper implements RowMapper<Ticket> {
    @Override
    public Ticket mapRow(ResultSet resultSet, int i) throws SQLException {
        Ticket ticket = new Ticket();
        ticket.setId(resultSet.getLong("ID"));
        ticket.setUserLogin(resultSet.getString("USER_LOGIN"));
        ticket.setEventId(resultSet.getLong("EVENT_ID"));
        ticket.setSeat(resultSet.getLong("SEAT"));
        ticket.setTicketPrice(resultSet.getDouble("PRICE"));
        ticket.setLucky(resultSet.getBoolean("IS_LUCKY"));
        ticket.setVip(resultSet.getBoolean("IS_VIP"));

        return ticket;
    }
}
