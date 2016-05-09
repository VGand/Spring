package com.epam.spring.cinema.dao.map;

import com.epam.spring.cinema.dao.TicketManager;
import com.epam.spring.cinema.domain.Event;
import com.epam.spring.cinema.domain.Ticket;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Andrey Vaganov on 09.05.2016.
 */
public class TicketMapManager implements TicketManager {
    public void save(Ticket ticket) {
        if (ticket != null) {
            if (ticket.getId() == null) {
                Long id = MapDB.getInstance().getNextTicketId();
                ticket.setId(id);
            }
            MapDB.getInstance().getTicketMap().put(ticket.getId(), ticket);
        }
    }

    public void remove(Long id) {
        if (id != null) {
            MapDB.getInstance().getTicketMap().remove(id);
        }
    }

    public Ticket getTicketById(Long id) {
        if (id != null) {
            return MapDB.getInstance().getTicketMap().get(id);
        }
        return null;
    }

    public List<Ticket> getAll() {
        return new ArrayList<Ticket>(MapDB.getInstance().getTicketMap().values());
    }

    public List<Ticket> getByEventAndDateTime(Event event, LocalDateTime dateTime) {
        List<Ticket> tickets= new ArrayList<Ticket>();
        if (event != null && dateTime != null) {
            for (Map.Entry<Long, Ticket> entry : MapDB.getInstance().getTicketMap().entrySet()) {
                Ticket ticket = entry.getValue();
                if (ticket != null && event.equals(ticket.getEvent()) && dateTime.compareTo(ticket.getDateTime()) == 0) {
                    tickets.add(ticket);
                }
            }
        }
        return tickets;
    }
}
