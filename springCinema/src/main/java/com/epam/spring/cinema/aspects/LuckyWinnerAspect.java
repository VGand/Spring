package com.epam.spring.cinema.aspects;

import com.epam.spring.cinema.domain.Ticket;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Andrey_Vaganov on 5/17/2016.
 */
@Aspect
@Component
public class LuckyWinnerAspect {

    @Pointcut("execution(* com.epam.spring.cinema.service.BookingService.bookTicket(..))")
    private void bookTicket() {}

    @Around("bookTicket() && args(tickets)")
    public void bookTicketAround(ProceedingJoinPoint pjp, List<Ticket> tickets) throws Throwable {
        if (checkLucky()) {
            //is lucky
            for(Ticket ticket : tickets) {
                ticket.setTicketPrice(new Double(0));
                ticket.getEvent().getPurchasedTickets().add(ticket);
                if (ticket.getUser() != null) {
                    ticket.getUser().getPurchasedTickets().add(ticket);
                    //save the lucky ticket
                    ticket.getUser().getLuckyTickets().add(ticket);
                }
            }
        } else {
            //isn't lucky
            pjp.proceed(new Object[]{tickets});
        }
    }

    private Boolean checkLucky() {
        return Math.random() < 0.5;
    }
}
