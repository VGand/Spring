package com.epam.spring.cinema.dao.map;

import com.epam.spring.cinema.dao.TicketManager;
import com.epam.spring.cinema.domain.Event;
import com.epam.spring.cinema.domain.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Andrey Vaganov on 09.05.2016.
 */
@Component
public class TicketMapManager implements TicketManager {

    @Autowired
    private MapDB mapDB;

    public void save(Ticket ticket) {
        if (ticket != null) {
            if (ticket.getId() == null) {
                Long id = mapDB.getNextTicketId();
                ticket.setId(id);
            }
            mapDB.getTicketMap().put(ticket.getId(), ticket);
        }
    }

    public void remove(Long id) {
        if (id != null) {
            mapDB.getTicketMap().remove(id);
        }
    }

    public Ticket getTicketById(Long id) {
        if (id != null) {
            return mapDB.getTicketMap().get(id);
        }
        return null;
    }

    public List<Ticket> getAll() {
        return new ArrayList<Ticket>(mapDB.getTicketMap().values());
    }

    public List<Ticket> getTicketByEventId(Long eventId) {
        List<Ticket> tickets= new ArrayList<Ticket>();
        if (eventId != null) {
            for (Map.Entry<Long, Ticket> entry : mapDB.getTicketMap().entrySet()) {
                Ticket ticket = entry.getValue();
                if (ticket != null && eventId.equals(ticket.getEvent().getId())) {
                    tickets.add(ticket);
                }
            }
        }
        return tickets;
    }

    @Override
    public List<Ticket> getTicketByUserLogin(String login) {
        return null;
    }
}
