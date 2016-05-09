package com.epam.spring.cinema.service.impl;

import com.epam.spring.cinema.domain.Event;
import com.epam.spring.cinema.domain.Ticket;
import com.epam.spring.cinema.domain.User;
import com.epam.spring.cinema.service.BookingService;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Andrey Vaganov on 09.05.2016.
 */
@Component
public class BookingServiceImpl implements BookingService {
    public Double getTicketsPrice(Event event, LocalDateTime from, User user, List<Long> seats) {
        return null;
    }

    public Boolean bookTicket(List<Ticket> tickets) {
        return null;
    }

    public List<Ticket> getPurchasedTicketsForEvent(Event event, LocalDateTime from) {
        return null;
    }
}
