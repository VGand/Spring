package com.epam.spring.cinema.controllers;

import com.epam.spring.cinema.domain.Event;
import com.epam.spring.cinema.domain.view.EventView;
import com.epam.spring.cinema.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Полина on 07.04.2017.
 */
@Controller
@RequestMapping("/")
public class EventListController {

    @Autowired
    EventService eventService;

    @RequestMapping(method = RequestMethod.GET)
    public String getAll(@ModelAttribute("model") ModelMap model) {
        List<Event> events =  eventService.getAll();
        model.addAttribute("events", getEventViewListByEventList(events));

        return "eventsList";
    }

    private List<EventView> getEventViewListByEventList(List<Event> events) {
        List<EventView> eventViewList = new ArrayList<>();
        for(Event event : events) {
            eventViewList.add(new EventView(event));
        }

        return eventViewList;
    }
}
