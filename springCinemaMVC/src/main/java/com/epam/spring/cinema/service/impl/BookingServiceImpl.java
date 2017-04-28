package com.epam.spring.cinema.service.impl;

import com.epam.spring.cinema.dao.TicketManager;
import com.epam.spring.cinema.dao.UserAccountManager;
import com.epam.spring.cinema.domain.*;
import com.epam.spring.cinema.service.BookingService;
import com.epam.spring.cinema.service.DiscountService;
import com.epam.spring.cinema.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Andrey Vaganov on 09.05.2016.
 */
@Component
public class BookingServiceImpl implements BookingService {

    @Autowired
    private EventService eventService;

    @Autowired
    private TicketManager ticketManager;

    @Autowired
    private UserAccountManager userAccountManager;

    @Autowired
    private DiscountService discountService;

    @Value("${coefficientForHighRating}")
    private Double coefficientForHighRating;

    @Value("${coefficientForVipSits}")
    private Double coefficientForVipSits;

    public Double getTicketsPrice(Event event, User user, Set<Long> seats) {
        Auditorium auditorium = event.getAuditorium();
        Double totalCost = new Double(0);
        for(Long set : seats) {
            if (auditorium.getVipSeats().contains(set)) {
                totalCost += event.getBasePrice() * coefficientForVipSits;
            } else {
                totalCost += event.getBasePrice();
            }
        }
        if (event.getRating() == EventRating.HIGH) {
            totalCost = totalCost * coefficientForHighRating;
        }

        Double discount = discountService.getDiscount(user, event, seats.size());
        totalCost = totalCost - discount/100 * totalCost;

        return totalCost;
    }

    @Transactional(value="jdbcTransactionManager", propagation = Propagation.REQUIRED)
    public Boolean bookTicket(List<Ticket> tickets, User user) {

        UserAccount userAccount = userAccountManager.get(user.getLogin());

        for(Ticket ticket : tickets) {
            ticket.setLucky(Boolean.FALSE);
            Set<Long> ticketSet = new HashSet<>();
            ticketSet.add(ticket.getSeat());

            Event event = eventService.getById(ticket.getEventId());
            Double ticketPrice = getTicketsPrice(event, user, ticketSet);

            if (ticketPrice <= userAccount.getAvailableAmount()) {
                ticket.setTicketPrice(ticketPrice);

                ticket.setUserLogin(user.getLogin());

                user.getPurchasedTickets().add(ticket);

                ticketManager.save(ticket);

                userAccount.setAvailableAmount(userAccount.getAvailableAmount() - ticketPrice);
                userAccountManager.save(userAccount);
            }
        }
        return null;
    }

    public List<Ticket> getPurchasedTicketsForEvent(Event event) {
        List<Ticket> resultList = new ArrayList<Ticket>();
        for(Long ticketId : event.getTicketIdList()) {
            Ticket ticket = ticketManager.getTicketById(ticketId);
            if (ticket.getUserLogin() != null) {
                //Если есть логин пользователя, значит билет уже продан
                resultList.add(ticket);
            }
        }
        return resultList;
    }

    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }

    public void setDiscountService(DiscountService discountService) {
        this.discountService = discountService;
    }

    public void setCoefficientForHighRating(Double coefficientForHighRating) {
        this.coefficientForHighRating = coefficientForHighRating;
    }

    public void setCoefficientForVipSits(Double coefficientForVipSits) {
        this.coefficientForVipSits = coefficientForVipSits;
    }
}
