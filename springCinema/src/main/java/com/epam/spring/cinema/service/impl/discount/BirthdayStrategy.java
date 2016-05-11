package com.epam.spring.cinema.service.impl.discount;

import com.epam.spring.cinema.domain.Event;
import com.epam.spring.cinema.domain.User;
import com.epam.spring.cinema.service.DiscountService;

import java.time.LocalDateTime;

/**
 * Created by Andrey_Vaganov on 5/11/2016.
 */
public class BirthdayStrategy implements DiscountService {
    @Override
    public Double getDiscount(Event event, User user, LocalDateTime from, Integer numberOfTickets) {
        return null;
    }
}
