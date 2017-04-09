package com.epam.spring.cinema.service.impl.discount;

import com.epam.spring.cinema.domain.Event;
import com.epam.spring.cinema.domain.User;
import com.epam.spring.cinema.service.DiscountService;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by Andrey_Vaganov on 5/11/2016.
 */
@Component
public class BirthdayStrategy implements DiscountService {

    private Double baseDiscount;

    @Override
    public Double getDiscount(User user, Event event,Integer numberOfTickets) {
        if (user != null && user.getBirthday() != null) {
            LocalDate birthdayDateFrom = LocalDate.of(LocalDate.now().getYear(), user.getBirthday().getMonth(),
                    user.getBirthday().getDayOfMonth()).minusDays(5);

            LocalDate birthdayDateTo = LocalDate.of(LocalDate.now().getYear(), user.getBirthday().getMonth(),
                    user.getBirthday().getDayOfMonth()).plusDays(5);

            if (event.getDate().toLocalDate().isAfter(birthdayDateFrom) && event.getDate().toLocalDate().isBefore(birthdayDateTo)) {
                return baseDiscount;
            }
        }
        return new Double(0);
    }

    public void setBaseDiscount(Double baseDiscount) {
        this.baseDiscount = baseDiscount;
    }
}
