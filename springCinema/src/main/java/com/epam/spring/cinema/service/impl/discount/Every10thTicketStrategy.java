package com.epam.spring.cinema.service.impl.discount;

import com.epam.spring.cinema.domain.Event;
import com.epam.spring.cinema.domain.User;
import com.epam.spring.cinema.service.DiscountService;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Created by Andrey_Vaganov on 5/11/2016.
 */
@Component
public class Every10thTicketStrategy implements DiscountService {

    private Double baseDiscount;

    @Override
    public Double getDiscount(User user, Event event, LocalDateTime from, Integer numberOfTickets) {
        if (user != null) {
            Integer purchasedCount = user.getPurchasedTickets().size();
            //кол-во купленных билотов пользователя после последней скидки
            Integer countOfAlreadyPurchasedTickets = purchasedCount % 10;
            return (countOfAlreadyPurchasedTickets + numberOfTickets) / 10 * baseDiscount / numberOfTickets;
        } else {
            return numberOfTickets / 10 * baseDiscount / numberOfTickets;
        }
    }

    public void setBaseDiscount(Double baseDiscount) {
        this.baseDiscount = baseDiscount;
    }
}
