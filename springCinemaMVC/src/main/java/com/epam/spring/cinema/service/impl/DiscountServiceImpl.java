package com.epam.spring.cinema.service.impl;

import com.epam.spring.cinema.domain.Event;
import com.epam.spring.cinema.domain.User;
import com.epam.spring.cinema.service.DiscountService;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Andrey Vaganov on 09.05.2016.
 */
@Component
public class DiscountServiceImpl implements DiscountService {

    private List<DiscountService> discountStrategies;

    public Double getDiscount(User user, Event event, Integer numberOfTickets) {
        Double maxDiscount = new Double(0);
        for(DiscountService strategy : discountStrategies) {
            Double discount = strategy.getDiscount(user, event, numberOfTickets);
            if (discount > maxDiscount) {
                maxDiscount = discount;
            }
        }
        return maxDiscount;
    }

    public void setDiscountStrategies(List<DiscountService> discountStrategies) {
        this.discountStrategies = discountStrategies;
    }
}
