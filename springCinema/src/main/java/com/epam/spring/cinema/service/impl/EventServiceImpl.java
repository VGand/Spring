package com.epam.spring.cinema.service.impl;

import com.epam.spring.cinema.domain.Event;
import com.epam.spring.cinema.service.EventService;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Andrey Vaganov on 09.05.2016.
 */
@Component
public class EventServiceImpl implements EventService {
    public void save(Event event) {

    }

    public void remove(Long id) {

    }

    public Event getById(Long id) {
        return null;
    }

    public Event getByName(String name) {
        return null;
    }

    public List<Event> getAll() {
        return null;
    }

    public List<Event> getForDateRange(LocalDateTime from, LocalDateTime to) {
        return null;
    }

    public Event getNextEvent(LocalDateTime to) {
        return null;
    }
}
