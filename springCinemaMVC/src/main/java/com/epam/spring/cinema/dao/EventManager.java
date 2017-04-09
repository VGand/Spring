package com.epam.spring.cinema.dao;

import com.epam.spring.cinema.domain.Event;

import java.util.List;

/**
 * Created by Andrey Vaganov on 09.05.2016.
 */
public interface EventManager {
    void save(Event event);

    void remove(Long id);

    Event getById(Long id);

    Event getByName(String name);

    List<Event> getAll();
}
