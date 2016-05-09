package com.epam.spring.cinema.service.impl;

import com.epam.spring.cinema.domain.Event;
import com.epam.spring.cinema.domain.User;
import com.epam.spring.cinema.service.DiscountService;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Created by Andrey Vaganov on 09.05.2016.
 */
@Component
public class DiscountServiceImpl implements DiscountService {
    public Double getDiscount(Event event, User user, LocalDateTime from, Integer numberOfTickets) {
        return null;
    }
}
