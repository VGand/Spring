package com.epam.spring.cinema.service;

import com.epam.spring.cinema.domain.Event;
import com.epam.spring.cinema.domain.Ticket;
import com.epam.spring.cinema.domain.User;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Andrey Vaganov on 09.05.2016.
 */
public interface BookingService {

    Double getTicketsPrice(Event event, LocalDateTime from, User user, List<Long> seats);

    Boolean bookTicket(List<Ticket> tickets);

    List<Ticket> getPurchasedTicketsForEvent(Event event, LocalDateTime from);
}
