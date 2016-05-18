package com.epam.spring.cinema.aspects;

import com.epam.spring.cinema.domain.Event;
import com.epam.spring.cinema.domain.Ticket;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

/**
 * Created by Andrey_Vaganov on 5/17/2016.
 */
@Aspect
@Component
public class CounterAspect {

    private final String GET_EVENT_BY_NAME_PREFIX = "GET_EVENT_BY_NAME_";
    private final String GET_EVENT_BASE_PRICE_PREFIX = "GET_EVENT_BASE_PRICE_";
    private final String BOOK_TICKET_TO_EVENT_PREFIX = "BOOK_TICKET_TO_EVENT_";

    private Map<String, Long> countMap = new HashMap<>();

    @Pointcut("execution(* com.epam.spring.cinema.dao.EventManager.getByName(..))")
    private void getEventByName() {}

    @Pointcut("execution(* com.epam.spring.cinema.domain.Event.getBasePrice(..))")
    private void getEventPrice() {}

    @Pointcut("execution(* com.epam.spring.cinema.service.BookingService.bookTicket(..))")
    private void bookTickets() {}

    @After("getEventByName() && args(name)")
    public void getEventByNameAfter(String name) {
        String key = GET_EVENT_BY_NAME_PREFIX + name;
        AspectUtil.incMapItem(key, countMap);
    }

    @After("getEventPrice()")
    public void getEventBasePriceAfter(JoinPoint joinPoint) {
        String key = GET_EVENT_BASE_PRICE_PREFIX + ((Event)joinPoint.getTarget()).getName();
        AspectUtil.incMapItem(key, countMap);
    }

    @After("bookTickets() && args(tickets)")
    public void bookTickets(List<Ticket> tickets) {
        for(Ticket ticket : tickets) {
            String key = BOOK_TICKET_TO_EVENT_PREFIX + ticket.getEvent().getName();
            AspectUtil.incMapItem(key, countMap);
        }
    }
}
