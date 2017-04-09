package com.epam.spring.cinema.domain.view;

import com.epam.spring.cinema.CinemaConstants;
import com.epam.spring.cinema.domain.Auditorium;
import com.epam.spring.cinema.domain.Event;
import com.epam.spring.cinema.domain.EventRating;
import com.epam.spring.cinema.domain.Ticket;
import com.epam.spring.cinema.util.CinemaDateUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Полина on 08.04.2017.
 */
public class EventView {

    private Long id;

    private String name;

    private String date;

    private double basePrice;

    private String ratingName;

    private String auditoriumLocation;

    private String description;

    private List<Ticket> ticketList = new ArrayList<>();

    public EventView(Event event) {
        id = event.getId();
        name = event.getName();
        date = CinemaDateUtils.dateToString(event.getDate(), CinemaConstants.DATE_TIME_FORMAT);
        basePrice = event.getBasePrice();
        ratingName = event.getRating().name();
        auditoriumLocation = event.getAuditorium().getName();
        description = event.getDescription();
    }

    public List<Ticket> getTicketList() {
        return ticketList;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public String getRatingName() {
        return ratingName;
    }

    public String getAuditoriumLocation() {
        return auditoriumLocation;
    }

    public String getDescription() {
        return description;
    }
}
