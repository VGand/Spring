package com.epam.spring.cinema.dao.jdbc;

import com.epam.spring.cinema.dao.AuditoriumManager;
import com.epam.spring.cinema.dao.TicketManager;
import com.epam.spring.cinema.domain.Event;
import com.epam.spring.cinema.domain.EventRating;
import com.epam.spring.cinema.domain.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Andrey_Vaganov on 26.05.2016.
 */
public class EventRowMapper implements RowMapper<Event> {

    private AuditoriumManager auditoriumManager;

    private TicketManager ticketManager;

    public EventRowMapper(AuditoriumManager auditoriumManager, TicketManager ticketManager) {
        this.auditoriumManager = auditoriumManager;
        this.ticketManager = ticketManager;
    }

    @Override
    public Event mapRow(ResultSet resultSet, int i) throws SQLException {
        Event event = new Event();
        event.setId(resultSet.getLong("ID"));
        event.setName(resultSet.getString("NAME"));
        event.setBasePrice(resultSet.getDouble("BASE_PRICE"));
        event.setRating(EventRating.getRatingBySysName(resultSet.getString("RATIN_SYS_NAME")));

        event.setAirDates(auditoriumManager.getAirDatesByEventId(event.getId()));
        event.setAuditoriums(auditoriumManager.getAuditoriumMapByEventId(event.getId()));

        List<Ticket> ticketList = ticketManager.getTicketByEventId(event.getId());
        for(Ticket ticket: ticketList) {
            ticket.setEvent(event);
        }
        event.setPurchasedTickets(ticketList);

        return event;
    }
}
