package com.epam.spring.cinema.controllers;

import com.epam.spring.cinema.dao.TicketManager;
import com.epam.spring.cinema.domain.Event;
import com.epam.spring.cinema.domain.Ticket;
import com.epam.spring.cinema.domain.User;
import com.epam.spring.cinema.domain.view.EventView;
import com.epam.spring.cinema.service.BookingService;
import com.epam.spring.cinema.service.EventService;
import com.epam.spring.cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Полина on 09.04.2017.
 */
@Controller
@RequestMapping("/view")
public class EventViewController {

    @Autowired
    EventService eventService;

    @Autowired
    TicketManager ticketManager;

    @Autowired
    BookingService bookingService;

    @Autowired
    @Qualifier("administrator")
    User administrator;

    @RequestMapping(value = "/{eventId}", method = RequestMethod.GET)
    public String getById(@ModelAttribute("model") ModelMap model,
                          @PathVariable("eventId") Long eventId) {

        Event event = eventService.getById(eventId);

        EventView eventView = new EventView(event);
        fillTicketsByIdList(eventView, event.getTicketIdList());
        model.addAttribute("event", eventView);

        return "eventView";
    }

    @RequestMapping(value = "/buy/{ticketId}", method = RequestMethod.GET)
    public String buy(@PathVariable("ticketId") Long ticketId) {
        Ticket ticket = ticketManager.getTicketById(ticketId);

        bookingService.bookTicket(Arrays.asList(ticket), administrator);

        return "redirect:/view/" + ticket.getEventId();
    }

    private void fillTicketsByIdList(EventView eventView, List<Long> ticketIdList){
        for(Long ticketId : ticketIdList) {
            Ticket ticket = ticketManager.getTicketById(ticketId);
            eventView.getTicketList().add(ticket);
        }
    }
}
