package com.epam.spring.cinema.dao;

import com.epam.spring.cinema.domain.Event;
import com.epam.spring.cinema.domain.Ticket;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Andrey Vaganov on 09.05.2016.
 */
public interface TicketManager {

    void save(Ticket ticket);

    void remove(Long id);

    Ticket getTicketById(Long id);

    List<Ticket> getAll();

    List<Ticket> getTicketByEventId(Long eventId);

    List<Ticket> getTicketByUserLogin(String login);
}
