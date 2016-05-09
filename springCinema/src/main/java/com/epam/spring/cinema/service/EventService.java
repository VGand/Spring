package com.epam.spring.cinema.service;

import com.epam.spring.cinema.domain.Event;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Andrey Vaganov on 09.05.2016.
 */
public interface EventService {

    void save(Event event);

    void remove(Long id);

    Event getById(Long id);

    Event getByName(String name);

    List<Event> getAll();

    List<Event> getForDateRange(LocalDateTime from, LocalDateTime to);

    Event getNextEvent(LocalDateTime to);
}
