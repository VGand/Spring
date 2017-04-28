package com.epam.spring.cinema.dao.jdbc;

import com.epam.spring.cinema.dao.TicketManager;
import com.epam.spring.cinema.domain.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Andrey_Vaganov on 5/24/2016.
 */
@Resource
public class TicketJdbcManager implements TicketManager {

    private final String INSERT_TICKET_QUERY = "INSERT INTO CINEMA_TICKET (ID, USER_LOGIN, EVENT_ID, SEAT, PRICE, IS_LUCKY, IS_VIP) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private final String UPDATE_TICKET_QUERY = "UPDATE CINEMA_TICKET SET USER_LOGIN = ?, EVENT_ID = ?, SEAT = ?, PRICE = ?, IS_LUCKY = ?, IS_VIP = ? WHERE ID = ?";
    private final String GET_MAX_ID_QUERY = "SELECT MAX(ID) FROM CINEMA_TICKET";
    private final String REMOVE_TICKET_QUERY = "DELETE FROM CINEMA_TICKET WHERE ID = ?";
    private final String GET_TICKET_BY_ID = "SELECT * FROM CINEMA_TICKET WHERE ID = ?";
    private final String GET_ALL_TICKET_QUERY = "SELECT * FROM CINEMA_TICKET";
    private final String GET_TICKETS_BY_USER_LOGIN = "SELECT * FROM CINEMA_TICKET WHERE USER_LOGIN = ?";
    private final String GET_TICKETS_BY_EVENT_ID = "SELECT * FROM CINEMA_TICKET WHERE EVENT_ID = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void save(Ticket ticket) {

        if (ticket.getId() != null) {
            jdbcTemplate.update(UPDATE_TICKET_QUERY, ticket.getUserLogin(), ticket.getEventId(),
                    ticket.getSeat(), ticket.getTicketPrice(), ticket.getLucky(), ticket.getVip(), ticket.getId());
        } else {
            Long id = jdbcTemplate.queryForObject(GET_MAX_ID_QUERY, new Object[]{}, Long.class);
            if (id == null ) {
                id = 0L;
            } else {
                id = id + 1;
            }
            jdbcTemplate.update(INSERT_TICKET_QUERY, id, ticket.getUserLogin(), ticket.getEventId(),
                    ticket.getSeat(), ticket.getTicketPrice(), ticket.getLucky(), ticket.getVip());
        }
    }

    @Override
    public void remove(Long id) {
        jdbcTemplate.update(REMOVE_TICKET_QUERY, new Object[]{id});
    }

    @Override
    public Ticket getTicketById(Long id) {
        return jdbcTemplate.queryForObject(GET_TICKET_BY_ID, new Object[]{id}, new TicketRowMapper());
    }

    @Override
    public List<Ticket> getAll() {
        return jdbcTemplate.query(GET_ALL_TICKET_QUERY, new TicketRowMapper());
    }

    @Override
    public List<Ticket> getTicketByEventId(Long eventId) {
        return jdbcTemplate.query(GET_TICKETS_BY_EVENT_ID, new Object[]{eventId}, new TicketRowMapper());
    }

    @Override
    public List<Ticket> getTicketByUserLogin(String login) {
        return jdbcTemplate.query(GET_TICKETS_BY_USER_LOGIN, new Object[]{login}, new TicketRowMapper());
    }
}
