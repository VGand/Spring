package com.epam.spring.cinema.service.impl;

import com.epam.spring.cinema.ApplicationContextProvider;
import com.epam.spring.cinema.domain.*;
import com.epam.spring.cinema.service.BookingService;
import com.epam.spring.cinema.service.DiscountService;
import com.epam.spring.cinema.service.EventService;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Andrey Vaganov on 09.05.2016.
 */
@Component
public class BookingServiceImpl implements BookingService {

    private EventService eventService;

    private DiscountService discountService;

    private Double coefficientForHighRating;

    private Double coefficientForVipSits;

    @Override
    public Ticket createTicket(Event event, LocalDateTime from, User user, Long seatNumber) {
        //Проверяем свободно ли место
        for(Ticket ticket : event.getPurchasedTickets()) {
            if (from.isEqual(ticket.getDateTime()) && seatNumber.equals(ticket.getSeat())) {
                return null;
            }
        }
        Ticket ticket = ApplicationContextProvider.getApplicationContext().getBean("ticket", Ticket.class);
        ticket.setDateTime(from);
        ticket.setEvent(event);
        ticket.setSeat(seatNumber);
        ticket.setUser(user);
        return ticket;
    }

    public Double getTicketsPrice(Event event, LocalDateTime from, User user, Set<Long> seats) {
        Auditorium auditorium = event.getAuditoriums().get(from);
        Double totalCost = 0D;
        for(Long set : seats) {
            Double cost;
            if (auditorium.getVipSeats().contains(set)) {
                cost = event.getBasePrice() * coefficientForVipSits;
            } else {
                cost = event.getBasePrice();
            }
            Double discount = discountService.getDiscount(event, user, from, seats.size());
            cost = cost - discount/100 * cost;

            totalCost += cost;
        }
        if (event.getRating() == EventRating.HIGH) {
            totalCost = totalCost * coefficientForHighRating;
        }

        return totalCost;
    }

    public Boolean bookTicket(List<Ticket> tickets) {
        for(Ticket ticket : tickets) {
            ticket.getEvent().getPurchasedTickets().add(ticket);
            if (ticket.getUser() != null) {
                ticket.getUser().getPurchasedTickets().add(ticket);
            }
        }
        return null;
    }

    public List<Ticket> getPurchasedTicketsForEvent(Event event, LocalDateTime from) {
        List<Ticket> resultList = new ArrayList<Ticket>();
        for(Ticket ticket : event.getPurchasedTickets()) {
            if (ticket.getDateTime().isEqual(from)) {
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
