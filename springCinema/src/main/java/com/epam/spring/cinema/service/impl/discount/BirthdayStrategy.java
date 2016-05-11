package com.epam.spring.cinema.service.impl.discount;

import com.epam.spring.cinema.domain.Event;
import com.epam.spring.cinema.domain.User;
import com.epam.spring.cinema.service.DiscountService;

import java.time.LocalDateTime;

/**
 * Created by Andrey_Vaganov on 5/11/2016.
 */
public class BirthdayStrategy implements DiscountService {

    private Double baseDiscount;

    @Override
    public Double getDiscount(Event event, User user, LocalDateTime dateTime, Integer numberOfTickets) {
        LocalDateTime birthdayDateFrom = user.getBirthday().minusDays(5);
        LocalDateTime birthdayDateTo = user.getBirthday().plusDays(5);
        if (dateTime.isAfter(birthdayDateFrom) && dateTime.isBefore(birthdayDateTo)) {
            return baseDiscount;
        }
        return new Double(0);
    }

    public void setBaseDiscount(Double baseDiscount) {
        this.baseDiscount = baseDiscount;
    }
}
