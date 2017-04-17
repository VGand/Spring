package com.epam.spring.cinema.controllers;

import com.epam.spring.cinema.CinemaConstants;
import com.epam.spring.cinema.dao.AuditoriumManager;
import com.epam.spring.cinema.domain.Auditorium;
import com.epam.spring.cinema.domain.EventRating;
import com.epam.spring.cinema.service.EventService;
import com.epam.spring.cinema.util.CinemaDateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * Created by Полина on 08.04.2017.
 */
@Controller
@RequestMapping("/add")
public class EventCreateController {

    @Autowired
    EventService eventService;

    @Autowired
    AuditoriumManager auditoriumManager;

    @RequestMapping(method = RequestMethod.GET)
    public String get(@ModelAttribute("model") ModelMap model) {
        model.addAttribute("auditoriums", auditoriumManager.getAll());
        model.addAttribute("eventRating", Arrays.asList(EventRating.values()));
        return "eventCreate";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String crate(WebRequest request) {
        String name = request.getParameter("name");

        String basePrice = request.getParameter("basePrice");

        String auditoriumLocation = request.getParameter("auditoriumLocation");
        Auditorium auditorium = auditoriumManager.getByName(auditoriumLocation);

        String ratingName = request.getParameter("ratingName");
        EventRating eventRating = EventRating.getRatingBySysName(ratingName);

        String date = request.getParameter("date");
        LocalDateTime localDateTime = CinemaDateUtils.getDateTimeByFormat(
                date, CinemaConstants.VIEW_DATE_TIME_FORMAT);

        String description = request.getParameter("description");

        eventService.add(name, Double.parseDouble(basePrice), auditorium,
                eventRating, localDateTime, description);

        return "redirect:/";
    }
}
