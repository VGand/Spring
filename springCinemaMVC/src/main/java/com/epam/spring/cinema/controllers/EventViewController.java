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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
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
    UserService userService;


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

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        User user = userService.getBuLogin(auth.getName());
        Ticket ticket = ticketManager.getTicketById(ticketId);

        bookingService.bookTicket(Arrays.asList(ticket), user);

        return "redirect:/view/" + ticket.getEventId();
    }

    @RequestMapping(value = "/soldTicketPrint/{eventId}", method = RequestMethod.GET)
    public String getSoldTicketsAsPdf(@ModelAttribute("model") ModelMap model,
                                      @PathVariable("eventId") Long eventId) {

        Event event = eventService.getById(eventId);
        List<Ticket> soldTicketList = getSoldTickets(event.getTicketIdList());
        model.put("ticketList", soldTicketList);

        return "soldTicketPdf";
    }

    private List<Ticket> getSoldTickets(List<Long> ticketIdList) {
        List<Ticket> soldTickets = new ArrayList<>();

        for(Long ticketId : ticketIdList) {
            Ticket ticket = ticketManager.getTicketById(ticketId);
            if (ticket.getUserLogin() != null) {
                soldTickets.add(ticket);
            }
        }

        return soldTickets;
    }

    private void fillTicketsByIdList(EventView eventView, List<Long> ticketIdList){
        for(Long ticketId : ticketIdList) {
            Ticket ticket = ticketManager.getTicketById(ticketId);
            eventView.getTicketList().add(ticket);
        }
    }
}
