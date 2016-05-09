package com.epam.spring.cinema.service;

import com.epam.spring.cinema.domain.Event;
import com.epam.spring.cinema.domain.User;

import java.time.LocalDateTime;

/**
 * Created by Andrey Vaganov on 09.05.2016.
 */
public interface DiscountService {

    Double getDiscount(Event event, User user, LocalDateTime from, Integer numberOfTickets);
}
