package com.epam.spring.cinema.dao.jdbc;

import com.epam.spring.cinema.dao.AuditoriumManager;
import com.epam.spring.cinema.dao.TicketManager;
import com.epam.spring.cinema.domain.Auditorium;
import com.epam.spring.cinema.domain.Event;
import com.epam.spring.cinema.domain.EventRating;
import com.epam.spring.cinema.domain.Ticket;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
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
        event.setDescription(resultSet.getString("DESCRIPTION") != null ? resultSet.getString("DESCRIPTION") : "");

        if (resultSet.getDate("EVENT_DATE") != null) {
            Date date = resultSet.getDate("EVENT_DATE");
            Instant instant = Instant.ofEpochMilli(date.getTime());
            event.setDate(LocalDateTime.ofInstant(instant, ZoneId.systemDefault()));
        }

        Auditorium auditorium = auditoriumManager.getAuditoriumByEventId(event.getId());
        event.setAuditorium(auditorium);

        List<Ticket> ticketList = ticketManager.getTicketByEventId(event.getId());
        List<Long> ticketIdList = new ArrayList<>();
        for(Ticket ticket: ticketList) {
            ticketIdList.add(ticket.getId());
        }
        event.setTicketIdList(ticketIdList);

        return event;
    }
}
