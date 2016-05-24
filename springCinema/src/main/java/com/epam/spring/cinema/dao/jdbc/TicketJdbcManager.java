package com.epam.spring.cinema.dao.jdbc;

import com.epam.spring.cinema.dao.TicketManager;
import com.epam.spring.cinema.domain.Event;
import com.epam.spring.cinema.domain.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Andrey_Vaganov on 5/24/2016.
 */
@Resource
public class TicketJdbcManager implements TicketManager {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void save(Ticket ticket) {

    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public Ticket getTicketById(Long id) {
        return null;
    }

    @Override
    public List<Ticket> getAll() {
        return null;
    }

    @Override
    public List<Ticket> getByEventAndDateTime(Event event, LocalDateTime dateTime) {
        return null;
    }

    @Override
    public List<Ticket> getTicketByUserLogin(String login) {
        return null;
    }
}
