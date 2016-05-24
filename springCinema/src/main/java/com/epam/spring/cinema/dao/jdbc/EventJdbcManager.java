package com.epam.spring.cinema.dao.jdbc;

import com.epam.spring.cinema.dao.EventManager;
import com.epam.spring.cinema.domain.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Andrey_Vaganov on 5/24/2016.
 */
@Resource
public class EventJdbcManager implements EventManager {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void save(Event event) {

    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public Event getById(Long id) {
        return null;
    }

    @Override
    public Event getByName(String name) {
        return null;
    }

    @Override
    public List<Event> getAll() {
        return null;
    }
}
