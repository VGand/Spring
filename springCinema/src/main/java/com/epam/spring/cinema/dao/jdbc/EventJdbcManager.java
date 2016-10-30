package com.epam.spring.cinema.dao.jdbc;

import com.epam.spring.cinema.dao.AuditoriumManager;
import com.epam.spring.cinema.dao.EventManager;
import com.epam.spring.cinema.dao.TicketManager;
import com.epam.spring.cinema.domain.Auditorium;
import com.epam.spring.cinema.domain.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Andrey_Vaganov on 5/24/2016.
 */
@Resource
public class EventJdbcManager implements EventManager {

    private final String INSERT_QUERY = "INSERT INTO CINEMA_EVENT (ID, NAME, BASE_PRICE, RATIN_SYS_NAME) VALUES (?, ?, ?, ?)";
    private final String GET_MAX_ID_QUERY = "SELECT MAX(ID) FROM CINEMA_EVENT";
    private final String INSERT_AIR_DATES_QUERY = "INSERT INTO CINEMA_AIR_DATES (EVENT_ID, DATE) VALUES (?, ?)";
    private final String INSERT_AUDITORIUM_LINK_QUERY = "INSERT INTO CINEMA_EVENT_AUDITORIUM_LINK (EVENT_ID, DATE, AUDITORIUM_ID) VALUES (?, ?, ?)";
    private final String REMOVE_QUERY = "DELETE FROM CINEMA_EVENT WHERE ID = ?";
    private final String GET_BY_NAME_QUERY = "SELECT * FROM CINEMA_EVENT WHERE NAME = ?";
    private final String GET_BY_ID_QUERY = "SELECT * FROM CINEMA_EVENT WHERE NAME = ?";
    private final String GET_ALL_QUERY = "SELECT * FROM CINEMA_EVENT";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private AuditoriumManager auditoriumManager;

    @Autowired
    private TicketManager ticketManager;

    @Override
    public void save(Event event) {
        Long id = jdbcTemplate.queryForObject(GET_MAX_ID_QUERY, new Object[]{}, Long.class);
        if (id == null ) {
            id = 0L;
        } else {
            id = id + 1;
        }

        jdbcTemplate.update(INSERT_QUERY, id, event.getName(), event.getBasePrice(), event.getRating().getSysName());

        for(LocalDateTime localDateTime :event.getAirDates()) {
            Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
            jdbcTemplate.update(INSERT_AIR_DATES_QUERY, id, new java.sql.Date(date.getTime()));
        }

        for(Map.Entry<LocalDateTime, Auditorium> entry: event.getAuditoriums().entrySet()) {
            Date date = Date.from(entry.getKey().atZone(ZoneId.systemDefault()).toInstant());
            jdbcTemplate.update(INSERT_AUDITORIUM_LINK_QUERY, id, new java.sql.Date(date.getTime()), entry.getValue().getId());
        }
    }

    @Override
    public void remove(Long id) {
        jdbcTemplate.update(REMOVE_QUERY, id);
    }

    @Override
    public Event getById(Long id) {
        return jdbcTemplate.queryForObject(GET_BY_ID_QUERY, new Object[]{id}, new EventRowMapper(auditoriumManager, ticketManager));
    }

    @Override
    public Event getByName(String name) {
        return jdbcTemplate.queryForObject(GET_BY_NAME_QUERY, new Object[]{name}, new EventRowMapper(auditoriumManager, ticketManager));
    }

    @Override
    public List<Event> getAll() {
        return jdbcTemplate.query(GET_ALL_QUERY, new EventRowMapper(auditoriumManager, ticketManager));
    }
}
