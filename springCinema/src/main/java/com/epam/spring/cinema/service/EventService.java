package com.epam.spring.cinema.service;

import com.epam.spring.cinema.domain.Auditorium;
import com.epam.spring.cinema.domain.Event;
import com.epam.spring.cinema.domain.EventRating;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NavigableMap;

/**
 * Created by Andrey Vaganov on 09.05.2016.
 */
public interface EventService {

    void add(String name, double basePrice, NavigableMap<LocalDateTime, Auditorium> auditoriums, EventRating rating);

    void save(Event event);

    void remove(Long id);

    Event getById(Long id);

    Event getByName(String name);

    List<Event> getAll();

    List<Event> getForDateRange(LocalDateTime from, LocalDateTime to);

    List<Event> getNextEvent(LocalDateTime from);
}
