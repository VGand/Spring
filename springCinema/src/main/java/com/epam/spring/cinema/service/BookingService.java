package com.epam.spring.cinema.service;

import com.epam.spring.cinema.domain.Event;
import com.epam.spring.cinema.domain.Ticket;
import com.epam.spring.cinema.domain.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 * Created by Andrey Vaganov on 09.05.2016.
 */
public interface BookingService {

    Ticket createTicket(Event event, LocalDateTime from, User user, Long seatNumber);

    Double getTicketsPrice(Event event, LocalDateTime from, User user, Set<Long> seats);

    Boolean bookTicket(List<Ticket> tickets);

    List<Ticket> getPurchasedTicketsForEvent(Event event, LocalDateTime from);
}
