package com.epam.spring.cinema.aspects;

import com.epam.spring.cinema.dao.TicketManager;
import com.epam.spring.cinema.domain.Ticket;
import com.epam.spring.cinema.domain.User;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Andrey_Vaganov on 5/17/2016.
 */
@Aspect
@Component
public class LuckyWinnerAspect {

    @Autowired
    private TicketManager ticketManager;

    @Pointcut("execution(* com.epam.spring.cinema.service.BookingService.bookTicket(..))")
    private void bookTicket() {}

    @Around("bookTicket() && args(tickets,user)")
    public void bookTicketAround(ProceedingJoinPoint pjp, List<Ticket> tickets, User user) throws Throwable {
        if (checkLucky()) {
            //is lucky
            for(Ticket ticket : tickets) {
                ticket.setLucky(Boolean.TRUE);
                ticket.setTicketPrice(new Double(0));
                ticket.setUserLogin(user.getLogin());

                user.getPurchasedTickets().add(ticket);
                ticketManager.save(ticket);
            }
        } else {
            //isn't lucky
            pjp.proceed(new Object[]{tickets, user});
        }
    }

    private Boolean checkLucky() {
        return Math.random() < 0.5;
    }
}
