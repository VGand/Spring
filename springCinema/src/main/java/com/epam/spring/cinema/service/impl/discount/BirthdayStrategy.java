package com.epam.spring.cinema.service.impl.discount;

import com.epam.spring.cinema.domain.Event;
import com.epam.spring.cinema.domain.User;
import com.epam.spring.cinema.service.DiscountService;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by Andrey_Vaganov on 5/11/2016.
 */
public class BirthdayStrategy implements DiscountService {

    private Double baseDiscount;

    @Override
    public Double getDiscount(Event event, User user, LocalDateTime dateTime, Integer numberOfTickets) {

        LocalDate birthdayDateFrom = LocalDate.of(LocalDate.now().getYear(), user.getBirthday().getMonth(),
                user.getBirthday().getDayOfMonth()).minusDays(5);

        LocalDate birthdayDateTo = LocalDate.of(LocalDate.now().getYear(), user.getBirthday().getMonth(),
                user.getBirthday().getDayOfMonth()).plusDays(5);

        if (dateTime.toLocalDate().isAfter(birthdayDateFrom) && dateTime.toLocalDate().isBefore(birthdayDateTo)) {
            return baseDiscount;
        }
        return new Double(0);
    }

    public void setBaseDiscount(Double baseDiscount) {
        this.baseDiscount = baseDiscount;
    }
}
